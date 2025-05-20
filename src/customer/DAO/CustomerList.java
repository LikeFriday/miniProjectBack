package customer.DAO;//고객 목록 관리

import customer.DTO.CustomerInfo;
import dbconnection.MyDBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerList {
// DTO 객체를 타입으로 가지는 list  생성
   public List<CustomerInfo> findAll(){
       List<CustomerInfo> list = new ArrayList<CustomerInfo>();

       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       String sql = "SELECT * FROM customerInfo";

       try{
           con = MyDBConnection.getConnection();
           pstmt = con.prepareStatement(sql);
           rs = pstmt.executeQuery();

           while (rs.next()){
               CustomerInfo ci = new CustomerInfo(
                       rs.getString(1),
                       rs.getString(2),
                       rs.getString(3),
                       rs.getString(4),
                       rs.getString(5)
               );
               list.add(ci);
           }
       }catch (SQLException e){
           e.printStackTrace();
       }finally {
           MyDBConnection.close(rs, pstmt, con);
       }
    return list;
    }

    public void insert(CustomerInfo customer) {
       Connection con = null;
       PreparedStatement pstmt = null;
        String sql = "INSERT INTO customerInfo (name, userId, email, phone, membershipLevel) VALUES (?,?,?,?,?)";

        try{
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getUserId());
            pstmt.setString(3, customer.getEmail());
            pstmt.setString(4, customer.getPhone());
            pstmt.setString(5, customer.getMembershipLevel());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MyDBConnection.close(null, pstmt, con);
        }
    }

    public void update(CustomerInfo customer) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "update customerInfo set name = ?, email = ?, phone = ?, membershipLevel = ? where userId = ?";

        try{
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getEmail());
            pstmt.setString(3, customer.getPhone());
            pstmt.setString(4, customer.getMembershipLevel());
            pstmt.setString(5, customer.getUserId());  // WHERE 조건

            pstmt.executeUpdate(); // 성공하면 1 이상 반환


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MyDBConnection.close(null, pstmt, con);
        }
    }

    public void delete(CustomerInfo customer) {
        Connection con = null;
        PreparedStatement pstmt = null;
        String sql = "delete from customerInfo where userId = ?";

        try{
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, customer.getUserId());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            MyDBConnection.close(null, pstmt, con);
        }
    }

    public CustomerInfo findCustomerName(String customerName) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM customerInfo WHERE name = ?";

        try {
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, customerName);
            rs = pstmt.executeQuery();

            //아이디가 있으면 객체 생성
            if (rs.next()) {
                return new CustomerInfo(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public CustomerInfo findUserId(String userId) {
       Connection con = null;
       PreparedStatement pstmt = null;
       ResultSet rs = null;

       String sql = "SELECT * FROM customerInfo WHERE userId = ?";

       try{
           con = MyDBConnection.getConnection();
           pstmt = con.prepareStatement(sql);

           pstmt.setString(1, userId);
           rs = pstmt.executeQuery();

           //아이디가 있으면 객체 생성
           if(rs.next()){
               return new CustomerInfo(
                       rs.getString(1),
                       rs.getString(2),
                       rs.getString(3),
                       rs.getString(4),
                       rs.getString(5)
               );
           }
       }catch (SQLException e){
           e.printStackTrace();
       }
        return null;
    }

    public CustomerInfo findEmail(String email) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM customerInfo WHERE email = ?";

        try{
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, email);
            rs = pstmt.executeQuery();

            //아이디가 있으면 객체 생성
            if(rs.next()){
                return new CustomerInfo(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public CustomerInfo findPhone(String phone) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM customerInfo WHERE phone = ?";

        try{
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, phone);
            rs = pstmt.executeQuery();

            //아이디가 있으면 객체 생성
            if(rs.next()){
                return new CustomerInfo(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public CustomerInfo findMembershipLevel(String membershipLevel) {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM customerInfo WHERE membershipLevel = ?";

        try{
            con = MyDBConnection.getConnection();
            pstmt = con.prepareStatement(sql);

            pstmt.setString(1, membershipLevel);
            rs = pstmt.executeQuery();

            //아이디가 있으면 객체 생성
            if(rs.next()){
                return new CustomerInfo(
                        rs.getString(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

}