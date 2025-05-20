package manage.dao;

import dbconnection.MyDBConnection;
import manage.dto.Admin;
import manage.method.Validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AdminDAO {
    Connection con = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Scanner sc = new Scanner(System.in);
    String sql = "";

    public Admin login(String id, String pw){
        Admin admin = null;
        con = MyDBConnection.getConnection();
        sql = "select * from admin where admin_id like ? and admin_pwd like ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            rs = pstmt.executeQuery();
            if(rs.next()){
                admin = new Admin();
                admin.setUid(rs.getInt("uid"));
                admin.setAdminId(rs.getString("admin_id"));
                admin.setAdminPw(rs.getString("admin_pwd"));
                admin.setAdminName(rs.getString("admin_name"));
                admin.setAdminEmail(rs.getString("admin_email"));
                admin.setRole(rs.getString("role"));
                admin.setCreateDate(rs.getString("create_date"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return admin;
    }
    // 관리자 리스트 표
    public List<Admin> adminFindAll(){
        List<Admin> list = new ArrayList<Admin>();
        Admin admin = null;
        con = MyDBConnection.getConnection();
        sql = "select * from admin";
        try {
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                admin = new Admin();
                admin.setUid(rs.getInt("uid"));
                admin.setAdminId(rs.getString("admin_id"));
                admin.setAdminPw(rs.getString("admin_pwd"));
                admin.setAdminName(rs.getString("admin_name"));
                admin.setAdminEmail(rs.getString("admin_email"));
                admin.setRole(rs.getString("role"));
                admin.setCreateDate(rs.getString("create_date"));
                list.add(admin);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    // 관리자 추가
    public void adminAdd(Admin admin)  {
        con = MyDBConnection.getConnection();
        sql = "insert into admin(uid, admin_id, admin_pwd, admin_name, admin_email, role, create_date) values(null,?,?,?,?,?,now())";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, admin.getAdminId());
            pstmt.setString(2, admin.getAdminPw());
            pstmt.setString(3, admin.getAdminName());
            pstmt.setString(4, admin.getAdminEmail());
            pstmt.setString(5, admin.getRole());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }

    public void adminDelete(String id, String pw)  {
        con = MyDBConnection.getConnection();
        sql = "delete from admin where admin_id like ? and admin_pwd like ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }

    }

    public void adminUpdateName(Admin admin) {
        sql = "update admin set admin_name = ? where uid = ?";
        Connection con = MyDBConnection.getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, admin.getAdminName());
            pstmt.setInt(2, admin.getUid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }

        public void adminUpdateEmail(Admin admin) {
        sql = "update admin set admin_email = ? where uid = ?";
        Connection con = MyDBConnection.getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, admin.getAdminEmail());
            pstmt.setInt(2, admin.getUid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }

    public void adminUpdatePassword(Admin admin) {

        sql = "update admin set admin_pwd = ? where uid = ?";
        Connection con = MyDBConnection.getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,admin.getAdminPw());
            pstmt.setInt(2, admin.getUid());
            pstmt.executeUpdate();
            System.out.println("변경 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }

    public void adminUpdateRole(Admin admin) {
        sql = "update admin set role = ? where uid = ?";
        Connection con = MyDBConnection.getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, admin.getRole());
            pstmt.setInt(2, admin.getUid());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }

    public boolean selectAdmin(int uid) {
        sql = "select * from admin where uid = ?";
        con = MyDBConnection.getConnection();
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, uid);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            MyDBConnection.close(rs, pstmt, con);
        }
        return false;
    }
}
