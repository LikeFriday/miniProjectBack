package manage.method;

import manage.dao.AdminDAO;
import manage.dto.Admin;

import java.util.Scanner;

public class AdminUpdate {

    public void adminUpdateName(){
        AdminDAO dao = new AdminDAO();
        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 관리자 번호 입력 : ");
        int uid = Integer.parseInt(sc.nextLine());
        if(!dao.selectAdmin(uid)){
            System.out.println("존재하지 않는 관리자 번호입니다.");
            return;
        }
        System.out.print("변경할 이름 : ");
        String name = sc.nextLine();
        admin.setUid(uid);
        admin.setAdminName(name);
        dao.adminUpdateName(admin);
        System.out.println("이름 변경 완료");
    }

    public void adminUpdateEmail(){
        AdminDAO dao = new AdminDAO();
        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        System.out.print("변경할 관리자 번호 : ");
        int uid = Integer.parseInt(sc.nextLine());
        if(!dao.selectAdmin(uid)){
            System.out.println("존재하지 않는 관리자 번호입니다.");
            return;
        }
        admin.setUid(uid);
        System.out.print("변경할 메일 : ");
        String email = sc.nextLine();
        if(Validation.checkEmail(email)){
            System.out.println("메일 형식이 아닙니다.");
            return;
        }
        admin.setAdminEmail(email);
        dao.adminUpdateEmail(admin);
        System.out.println("메일 변경 완료");
    }

    public void adminUpdateRole(){
        AdminDAO dao = new AdminDAO();
        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 관리자 번호 : ");
        int uid = Integer.parseInt(sc.nextLine());
        if(!dao.selectAdmin(uid)){
            System.out.println("존재하지 않는 관리자 번호입니다.");
            return;
        }
        admin.setUid(uid);
        System.out.print("변경할 등급 : ");
        String role = sc.nextLine();
        if(!Validation.checkRole(role)){
            System.out.println("존재하지 않는 등급을 입력했습니다.");
            return;
        }
        admin.setRole(role);
        dao.adminUpdateRole(admin);
        System.out.println("등급 변경 완료");
    }

    public void adminUpdatePassword(){
        AdminDAO dao = new AdminDAO();
        Admin admin = new Admin();
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 관리자 번호 : ");
        int uid = Integer.parseInt(sc.nextLine());
        if(!dao.selectAdmin(uid)){
            System.out.println("존재하지 않는 관리자 번호입니다.");
            return;
        }
        admin.setUid(uid);
        System.out.print("변경할 비밀번호 : ");
        String pw = sc.nextLine();
        if(!Validation.checkPwd(pw)){
            System.out.println("비밀번호에 특수문자가 포함되어있지 않습니다.");
            return;
        }
        String encryptPw = Encryption.encrypt(Encryption.FIXED_KEY, Encryption.FIXED_IV, pw);
        admin.setAdminPw(encryptPw);
        dao.adminUpdatePassword(admin);
        System.out.println("비밀번호 변경 완료 ");
    }
}
