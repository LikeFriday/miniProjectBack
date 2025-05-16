package product.dao;

import dbconnection.MyDBConnection;
import product.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    // ✅ 상품 등록
    public void insertProduct(Product product) {
        String sql = "INSERT INTO Products (pdname, price, stock, description, category_id) VALUES (?, ?, ?, ?, ?)";

        try{
            Connection con = MyDBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, product.getName());
            pstmt.setInt(2, product.getPrice());
            pstmt.setInt(3, product.getStock());
            pstmt.setString(4, product.getDescription());
            pstmt.setInt(5, product.getCategoryId());
            pstmt.executeUpdate();

            System.out.println("✅ 상품 등록 성공!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ 전체 상품 조회
    public List<Product> selectAll() {
        List<Product> list = new ArrayList<>();
        String sql = "SELECT * FROM Products";
        try {
            Connection con = MyDBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product p = new Product(
                        rs.getString("pdname"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getString("description"),
                        rs.getInt("category_id")
                );
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // ✅ 상품 삭제 (상품명 기준)
    public void deleteByName(String name) {
        String sql = "DELETE FROM Products WHERE pdname = ?";
        try {
            Connection con = MyDBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, name);
            int result = pstmt.executeUpdate();
            if (result > 0) System.out.println("🗑️ 삭제 성공");
            else System.out.println("❌ 해당 상품 없음");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ 상품 재고 수정 (상품명 기준)
    public void updateProductStockByName(String name, int newStock) {
        String sql = "UPDATE Products SET stock = ? WHERE pdname = ?";
        try{
            Connection con = MyDBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setInt(1, newStock);
            pstmt.setString(2, name);

            int result = pstmt.executeUpdate();

            if (result > 0) System.out.println("🔁 재고 수정 완료");
            else System.out.println("❌ 해당 상품 없음");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // ✅ 키워드 검색 (상품명, 설명, 카테고리명)
    public void searchProductsByKeyword(String keyword) {
        String sql = """
            SELECT p.pdname, p.price, p.stock, p.description, c.cname
            FROM Products p
            JOIN Categories c ON p.category_id = c.category_id
            WHERE p.pdname LIKE ? OR p.description LIKE ? OR c.cname LIKE ?
        """;

        try{
            Connection con = MyDBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            String likeKeyword = "%" + keyword + "%";
            pstmt.setString(1, likeKeyword);
            pstmt.setString(2, likeKeyword);
            pstmt.setString(3, likeKeyword);

            ResultSet rs = pstmt.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.printf("\n📦 상품명: %s\n가격: %,d원 | 재고: %d개\n카테고리: %s\n설명: %s\n",
                        rs.getString("pdname"),
                        rs.getInt("price"),
                        rs.getInt("stock"),
                        rs.getString("cname"),
                        rs.getString("description"));
            }
            if (!found) {
                System.out.println("🔍 일치하는 상품이 없습니다.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
