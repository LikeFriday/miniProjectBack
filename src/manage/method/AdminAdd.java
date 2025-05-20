package manage.method;

import manage.dao.AdminDAO;
import manage.dto.Admin;

import java.util.Scanner;

public class AdminAdd {
    public void adminAdd(){
        AdminDAO dao = new AdminDAO();
        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        System.out.print("아이디 : ");
        String id = sc.nextLine();
        System.out.print("비밀번호 : ");
        String pw = sc.nextLine();
        if(!Validation.checkPwd(pw)){
            return;
        }
        System.out.print("이름 : ");
        String name = sc.nextLine();
        System.out.print("이메일 : ");
        String email = sc.nextLine();
        if(!Validation.checkEmail(email)){
            return;
        }
        System.out.print("등급 : ");
        String role = sc.nextLine();
        if(!Validation.checkRole(role)){
            return;
        }
        admin.setAdminId(id);
        admin.setAdminPw(pw);
        admin.setAdminName(name);
        admin.setAdminEmail(email);
        admin.setRole(role);

        dao.adminAdd(admin);
    }
}
