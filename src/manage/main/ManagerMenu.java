package manage.main;

import manage.dao.AdminDAO;
import manage.dto.Admin;
import manage.method.AdminAdd;
import manage.method.AdminDelete;
import manage.method.AdminList;
import manage.print.Menu;

import java.util.Scanner;

public class ManagerMenu {
    static public void adminMenu(Admin admin) {
        AdminList adminList = new AdminList();
        AdminAdd adminAdd = new AdminAdd();
        AdminDelete adminDelete = new AdminDelete();
        Scanner sc = new Scanner(System.in);
        while (true) {
            Menu.menuAdmin();
            int choice = Integer.parseInt(sc.nextLine());
            if(choice == 0){
                System.out.println("뒤로가기");
                break;
            }
            switch (choice) {
                case 1: adminList.adminList(); break;
                case 2:
                    if(admin.getRole().equals("A")){ adminAdd.adminAdd(); break; }
                    System.out.println("권한이 없습니다.");
                    break;
                case 3:
                    if(admin.getRole().equals("A")){ adminDelete.adminDelete(); break; }
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
