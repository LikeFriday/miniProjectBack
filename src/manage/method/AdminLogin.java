package manage.method;

import manage.dao.AdminDAO;
import manage.dto.Admin;

import java.util.Scanner;

public class AdminLogin {
    public Admin adminLogin() {
        Scanner sc = new Scanner(System.in);
        Admin admin = null;
        AdminDAO dao = new AdminDAO();
        System.out.print("아이디 : ");
        String id = sc.nextLine();
        System.out.print("비밀번호 : ");
        String pw = sc.nextLine();

        if(pw.equals("admin")){
            admin = dao.login(id, pw);
        }else{
            String encryptPw = Encryption.encrypt(Encryption.FIXED_KEY, Encryption.FIXED_IV, pw);
            admin = dao.login(id, encryptPw);
        }

        if(admin == null){
            System.out.println("로그인 실패");
            return null;
        }
        System.out.println("로그인 성공");
        return admin;

    }
}
