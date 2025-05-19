package manage.main;

import manage.dao.AdminDAO;

import manage.print.Menu;

import java.util.Scanner;

public class UpdateMenu {
    public static void updateMenu() {
        AdminDAO dao = new AdminDAO();
        Scanner sc = new Scanner(System.in);
        Menu.updateMenu();
        String choice = sc.nextLine();
        switch (choice) {
            case "1": dao.adminUpdateName(); break;
            case "2": dao.adminUpdatePassword(); break;
            case "3": dao.adminUpdateEmail(); break;
            case "4": dao.adminUpdateRole(); break;
            case "0":
                System.out.println("취소 하였습니다.");
                break;
                default:
                    System.out.println("입력 오류");

        }
    }
}
