package manage.dao;

import dbconnection.MyDBConnection;
import manage.dto.Admin;

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

    public Admin login(){
        System.out.print("아이디 입력 : ");
        String id = sc.nextLine();
        System.out.print("비밀번호 입력 : ");
        String pw = sc.nextLine();

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
    public void adminFindAll(){
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

        for(Admin a : list){
            System.out.println(a);
        }
        System.out.println();
    }
    // 관리자 추가
    public void adminAdd()  {
        con = MyDBConnection.getConnection();
        System.out.print("아이디 : ");
        String id = sc.nextLine();
        System.out.print("비밀번호 : ");
        String pw = sc.nextLine();
        if(!checkPwd(pw)){
            return;
    }

        System.out.print("이름 : ");
        String name = sc.nextLine();
        System.out.print("이메일 : ");
        String email = sc.nextLine();
        if(!checkEmail(email)){
            return;
        }

        System.out.print("등급 : ");
        String role = sc.nextLine();
        if(!checkRole(role)){
            return;
        }
        sql = "insert into admin(uid, admin_id, admin_pwd, admin_name, admin_email, role, create_date) values(null,?,?,?,?,?,now())";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.setString(5, role);
            pstmt.executeUpdate();
            System.out.println("등록되었습니다. ");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }
    }

    public void adminDelete()  {
        con = MyDBConnection.getConnection();
        System.out.print("삭제할 id : ");
        String id = sc.nextLine();
        System.out.print("삭제할 pw : ");
        String pw = sc.nextLine();
        sql = "delete from admin where admin_id like ? and admin_pwd like ?";
        try {
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, id);
            pstmt.setString(2, pw);
            pstmt.executeUpdate();
            System.out.println("삭제되었습니다.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MyDBConnection.close(rs, pstmt, con);
        }

    }

    public void adminUpdateName() {
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 관리자 번호 : ");
        int uid = Integer.parseInt(sc.nextLine());
        if(!selectAdmin(uid)){
            return;
        }
        sql = "update admin set admin_name = ? where uid = ?";
        Connection con = MyDBConnection.getConnection();
        try {
            System.out.print("변경할 이름 : ");
            String name = sc.nextLine();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, name);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            System.out.println("변경완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adminUpdateEmail() {
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 관리자 번호 : ");
        int uid = Integer.parseInt(sc.nextLine());
        if(!selectAdmin(uid)){
            return;
        }
        sql = "update admin set admin_email = ? where uid = ?";
        Connection con = MyDBConnection.getConnection();
        try {
            System.out.print("변경할 메일 : ");
            String email = sc.nextLine();
            if(!checkEmail(email)){
                return;
            }
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, email);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            System.out.println("변경 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adminUpdatePassword() {
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 관리자 번호 : ");
        int uid = Integer.parseInt(sc.nextLine());
        if(!selectAdmin(uid)){
            return;
        }
        sql = "update admin set admin_pwd = ? where uid = ?";
        Connection con = MyDBConnection.getConnection();
        try {
            System.out.print("변경할 비밀번호 : ");
            String pwd = sc.nextLine();
            if(!checkPwd(pwd)){
                return;
            }
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, pwd);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            System.out.println("변경 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void adminUpdateRole() {
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 관리자 번호 : ");
        int uid = 0;
        try {
           uid = Integer.parseInt(sc.nextLine());
        }catch (NumberFormatException e){
            System.out.println("숫자를 입력하세요");
            return;
        }

        if(!selectAdmin(uid)){
            return;
        }
        sql = "update admin set role = ? where uid = ?";
        Connection con = MyDBConnection.getConnection();
        try {
            System.out.print("변경할 등급 : ");
            String role = sc.nextLine();
            if(!checkRole(role)){
                return;
            }
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, role);
            pstmt.setInt(2, uid);
            pstmt.executeUpdate();
            System.out.println("변경 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean selectAdmin(int uid) {
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
        System.out.println("입력한 관리자 번호가 없습습니다.");
        return false;
    }

    private boolean checkEmail(String email) {
        if(email.indexOf("@") == -1){
            System.out.println("이메일 형식이 아닙니다.");
            return false;
        }else{
            return true;
        }
    }

    private boolean checkPwd(String pw) {
        String regex = "[!@#$%^&*]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(pw);
        if (matcher.find()) {
           return true;
        } else {
            System.out.println("비밀번호에 특수문자가 없습니다. ");
            return false;
        }
    }

    private boolean checkRole(String role) {
        if(role.equals("A") || role.equals("B") || role.equals("C")){
            return true;
        }else{
            System.out.println("등급 입력 오류 A/B/C 입력가능");
            return false;
        }
    }

}
