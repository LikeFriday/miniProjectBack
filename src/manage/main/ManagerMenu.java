package manage.main;

import manage.dao.AdminDAO;
import manage.dto.Admin;
import manage.print.Menu;

import java.util.Scanner;

public class ManagerMenu {
    static public void adminMenu(Admin admin) {
        AdminDAO dao = new AdminDAO();
        Scanner sc = new Scanner(System.in);
        while (true) {
            Menu.menuAdmin();
            int choice = Integer.parseInt(sc.nextLine());
            if(choice == 0){
                System.out.println("뒤로가기");
                break;
            }
            switch (choice) {
                case 1: dao.adminFindAll(); break;
                case 2:
                    if(admin.getRole().equals("A")){ dao.adminAdd(); break; }
                    System.out.println("권한이 없습니다.");
                    break;
                case 3:
                    if(admin.getRole().equals("A")){ dao.adminDelete(); break; }
                    System.out.println("권한이 없습니다.");
                    break;
                case 4:
                    if(admin.getRole().equals("A") || admin.getRole().equals("B")){
                        UpdateMenu.updateMenu();
                        break;
                    }
                    System.out.println("권한이 없습니다. ");
                    break;
            }
        }
    }
}
