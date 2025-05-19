package manage.main;

import manage.dao.AdminDAO;
import manage.dto.Admin;
import manage.print.Menu;
import product.main.ProductApp;

import java.util.Scanner;

public class Main {
    AdminDAO dao = new AdminDAO();
    Admin admin = null;
    Scanner sc = new Scanner(System.in);
    public void run() {
        while(true){
            System.out.println("1. 로그인 2. 종료");
            System.out.print("입력 : ");
            int choice = Integer.parseInt(sc.nextLine());
            if(choice == 2){
                System.out.println("프로그램 종료"); break; }

            admin = dao.login();
            if(admin == null){System.out.println("로그인 실패"); continue;}
            while(true){
                Menu.mainMenu();
                choice = Integer.parseInt(sc.nextLine());
                if(choice == 0){ break; }
                switch (choice){
                    case 1:  ManagerMenu.adminMenu(admin); break;
                    case 2:  ProductApp.run();
                    default:
                        System.out.println("입력 오류"); break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Main main = new Main();
        main.run();
    }
}
