package product.dao;

import dbconnection.MyDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class CategoryDAO {
    // 카테고리 목록을 (카테고리명 → ID) Map으로 반환
    public Map<String, Integer> getCategoryMap() {
        Map<String, Integer> map = new HashMap<>();
        String sql = "SELECT category_id, cname FROM Categories";
        try {
            Connection con = MyDBConnection.getConnection();
            PreparedStatement pstmt = con.prepareStatement(sql);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                map.put(rs.getString("cname"), rs.getInt("category_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return map;
    }

    public void printAllCategories() {
        System.out.println("\n📚 사용 가능한 카테고리:");
        Map<String, Integer> map = getCategoryMap();
        map.forEach((name, id) -> System.out.println("- " + name + " (ID: " + id + ")"));
    }
}
