package customer.main;

import customer.DAO.CustomerList;
import customer.DTO.CustomerInfo;
import customer.method.DeleteService;
import customer.method.InsertService;
import customer.method.ModifyService;
import customer.method.SearchService;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {

    // 고객 관리 메뉴 실행 메서드
    public static void Menu(Scanner scanner){

        while(true){
            // 메뉴 출력
            System.out.println("===================================");
            System.out.println("1. 고객 목록");
            System.out.println("2. 고객 등록");
            System.out.println("3. 고객 검색");
            System.out.println("4. 고객 정보 수정");
            System.out.println("5. 고객 정보 삭제");
            System.out.println("6. 종료");
            System.out.println("===================================");

            System.out.print("번호를 입력하세요 >> ");
            int choice = Integer.parseInt(scanner.nextLine());

            if(choice == 6){
                System.out.println("종료합니다.");
                return; // 프로그램 종료
            }

            if (choice < 1 || choice > 6) {
                System.out.println("❌ 잘못된 선택입니다. 다시 입력해주세요.");
                continue;
            }


            switch (choice) {
                // 고객 목록 출력
                case 1:
                    System.out.println("\n고객 리스트");
                    List<CustomerInfo> list = new CustomerList().findAll();

                    if(list.isEmpty()){
                        System.out.println("등록된 고객이 없습니다.");
                    }else{
                        System.out.println("======================================================================================================================================================");
                        System.out.println("    No.   |       이름       |          아이디          |             이메일             |           전화번호           |            등급            ");
                        System.out.println("======================================================================================================================================================");
                        int index = 1;
                        for(CustomerInfo c : list){
                            System.out.printf("     %-4s        %-10s         %-15s           %-20s              %-30s   %-40s\n",
                                    index++,c.getCustomerName(), c.getUserId(), c.getEmail(), c.getPhone(), c.getMembershipLevel());
                        }
                        System.out.println();
                    }
                    break;

                // 고객 등록
                case 2:
                    while (true) {
                        System.out.print("고객 등록을 실행하시겠습니까? (1. 실행 | 2. 종료) >> ");
                        int insertChoice = Integer.parseInt(scanner.nextLine());

                        if (insertChoice != 1 && insertChoice != 2) {
                            System.out.println("\n1 또는 2 중에 선택해주세요.\n");
                            continue;
                        }

                        if(insertChoice == 1){
                            InsertService insertService = new InsertService(new CustomerList());
                            insertService.insertService(scanner);
                        }else{
                            System.out.println("고객 등록이 취소되었습니다.");
                            break;
                        }
                    }
                    break;

                // 고객 검색
                case 3:
                    while(true){
                        System.out.print("고객 검색을 실행하시겠습니까? (1. 실행 | 2. 종료) >> ");
                        int searchChoice = Integer.parseInt(scanner.nextLine());

                        if(searchChoice != 1 && searchChoice != 2) {
                            System.out.println("\n1 또는 2 중에 선택해주세요.\n");
                            continue;
                        }
                        if(searchChoice == 1){
                            SearchService search = new SearchService(new CustomerList());
                            search.searchService();
                        }else{
                            break;
                        }
                    }
                    break;

                // 고객 정보 수정
                case 4:
                    while(true){
                        System.out.print("고객 수정을 실행하시겠습니까? (1. 실행 | 2. 종료) >> ");
                        int modifyChoice = Integer.parseInt(scanner.nextLine());

                        if(modifyChoice != 1 && modifyChoice != 2) {
                            System.out.println("\n1 또는 2 중에 선택해주세요.\n");
                            continue;
                        }
                        if(modifyChoice == 1){
                            ModifyService modify = new ModifyService(new CustomerList());
                            modify.modifyService();
                        }else{
                            break;
                        }
                    }
                    break;

                // 고객 정보 삭제
                case 5:
                    while(true){
                        System.out.print("고객 삭제를 실행하시겠습니까? (1. 실행 | 2. 종료) >> ");
                        int deleteChoice = Integer.parseInt(scanner.nextLine());

                        if(deleteChoice != 1 && deleteChoice != 2) {
                            System.out.println("\n1 또는 2 중에 선택해주세요.\n");
                            continue;
                        }
                        if(deleteChoice == 1){
                            DeleteService delete = new DeleteService(new CustomerList());
                            delete.deleteService();
                        }else{
                            break;
                        }
                    }
                    break;

                // 잘못된 메뉴 번호 입력 처리
                default:
                    System.out.println("❌ 잘못된 선택입니다. 다시 선택해주세요.");
                    continue;
            }
        }
    }
}
