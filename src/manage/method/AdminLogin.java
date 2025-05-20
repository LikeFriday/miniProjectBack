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
        admin = dao.login(id, pw);
        if(admin == null){
            System.out.println("로그인 실패");
            return null;
        }
        System.out.println("로그인 성공");
        return admin;

    }
}
