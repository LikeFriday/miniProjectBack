package product.DAO;

import dbconnection.MyDBConnection;
import product.DTO.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDAO {
    public void insertProduct(Product p) {
        String sql = "INSERT INTO Products (pdname, price, stock, category_id, description, created_by) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            Connection con = MyDBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);

            pstmt.setString(1, p.getPdname());
            pstmt.setInt(2, p.getPrice());
            pstmt.setInt(3, p.getStock());
            pstmt.setInt(4, p.getCategoryId());
            pstmt.setString(5, p.getDescription());
            pstmt.setInt(6, p.getCreatedBy());
            pstmt.executeUpdate();
            System.out.println("✅ 상품 등록 완료!");
        } catch (SQLException e) {
            System.out.println("❌ 등록 실패: " + e.getMessage());
        }
    }

    public void updateStock(int id, int newStock) {
        String sql = "UPDATE Products SET stock = ? WHERE product_id = ?";

        try{
            Connection conn = MyDBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, newStock);
            pstmt.setInt(2, id);
            int updated = pstmt.executeUpdate();
            System.out.println(updated > 0 ? "✅ 재고 수정 완료!" : "❌ 해당 상품 없음");
        } catch (SQLException e) {
            System.out.println("❌ 수정 실패: " + e.getMessage());
        }
    }

    public void deleteProduct(int id) {
        String sql = "DELETE FROM Products WHERE product_id = ?";
        try {
            Connection conn = MyDBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);
            int deleted = pstmt.executeUpdate();
            System.out.println(deleted > 0 ? "✅ 상품 삭제 완료!" : "❌ 해당 상품 없음");
        } catch (SQLException e) {
            System.out.println("❌ 삭제 실패: " + e.getMessage());
        }
    }

    public void searchByNameOrCategory(String keyword) {
        String sql = """
            SELECT p.product_id, p.pdname, c.cname, p.price, p.stock
            FROM Products p
            JOIN Categories c ON p.category_id = c.category_id
            WHERE p.pdname LIKE ? OR c.cname LIKE ?
            """;
        try {
            Connection conn = MyDBConnection.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, "%" + keyword + "%");
            pstmt.setString(2, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                System.out.printf("ID: %d | 이름: %s | 카테고리: %s | 가격: %d | 재고: %d\n",
                        rs.getInt("product_id"),
                        rs.getString("pdname"),
                        rs.getString("cname"),
                        rs.getInt("price"),
                        rs.getInt("stock"));
            }
        } catch (SQLException e) {
            System.out.println("❌ 검색 실패: " + e.getMessage());
        }
    }
}
