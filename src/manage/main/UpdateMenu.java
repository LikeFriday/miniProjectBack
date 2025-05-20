package manage.main;

import manage.dao.AdminDAO;

import manage.dto.Admin;
import manage.method.AdminUpdate;
import manage.print.Menu;

import java.util.Scanner;

public class UpdateMenu {
    public static void updateMenu() {
        AdminUpdate adminUpdate = new AdminUpdate();
        Scanner sc = new Scanner(System.in);
        Menu.updateMenu();
        String choice = sc.nextLine();
        switch (choice) {
            case "1": ;adminUpdate.adminUpdateName(); break;
            case "2": adminUpdate.adminUpdatePassword(); break;
            case "3": adminUpdate.adminUpdateEmail(); break;
            case "4": adminUpdate.adminUpdateRole(); break;
            case "0":
                System.out.println("취소 하였습니다.");
                break;
                default:
                    System.out.println("입력 오류");

        }
    }
}
