package manage.method;

import manage.dao.AdminDAO;

import java.util.Scanner;

public class AdminDelete {
    public void adminDelete() {
        AdminDAO dao = new AdminDAO();
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 id : ");
        String id = sc.nextLine();
        System.out.print("삭제할 pw : ");
        String pw = sc.nextLine();
        String encryptPw = Encryption.encrypt(Encryption.FIXED_KEY, Encryption.FIXED_IV, pw);
        dao.adminDelete(id,encryptPw);
        System.out.println("삭제되었습니다.");
    }
}
