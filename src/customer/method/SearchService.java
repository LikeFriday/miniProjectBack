package customer.method; // 고객 검색 기능 클래스

import customer.DAO.CustomerList;
import customer.DTO.CustomerInfo;
import customer.validator.InputValidator;

import java.util.Scanner;

public class SearchService {
    //참조 변수 자체가 다른 객체를 참조하지 않도록, 한 번 초기화된 후 다른 객체로 변경될 수 없게
    private final CustomerList list; // 고객 정보 목록 (DAO)

    // 생성자에서 고객 목록을 받아 초기화
    public SearchService(CustomerList list) {
        this.list = list;
    }

    // 고객 검색 서비스 실행
    public void searchService() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("**************************** 고객 검색 페이지 ***************************");
            System.out.print("\n검색할 항목을 입력해주세요 (1. 아이디 2. 이메일 3. 전화번호 4. exit) >> ");
            int searchOption = Integer.parseInt(scanner.nextLine()); // 검색 항목 선택

            if (searchOption < 1 || searchOption > 4) {
                System.out.println("❌ 잘못된 선택입니다. 다시 입력해주세요.");
                continue;
            }

            CustomerInfo customer = null; // 검색 결과 저장용

            while(true) {
                // 아이디로 검색
                if (searchOption == 1) {
                    while (true) {
                        try {
                            System.out.print("검색할 아이디 입력 >> ");
                            String userId = scanner.nextLine();
                            InputValidator.isValidUserId(userId); // 유효성 검사
                            customer = list.findUserId(userId); // 아이디로 고객 찾기
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                // 이메일로 검색
                else if (searchOption == 2) {
                    while (true) {
                        try {
                            System.out.print("검색할 이메일 입력 >> ");
                            String email = scanner.nextLine();
                            InputValidator.isValidEmail(email); // 유효성 검사
                            customer = list.findEmail(email); // 이메일로 고객 찾기
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                // 전화번호로 검색
                else if (searchOption == 3) {
                    while (true) {
                        try {
                            System.out.print("검색할 전화번호 입력 >> ");
                            String phone = scanner.nextLine();
                            InputValidator.isValidPhone(phone); // 유효성 검사
                            customer = list.findPhone(phone); // 전화번호로 고객 찾기
                            break;
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
                // 종료 선택
                else if (searchOption == 4) {
                    return;
                }
                // 잘못된 입력 처리
                else {
                    System.out.println("❌ 잘못된 선택입니다. 1~3 중에서 선택해주세요.\n");
                    continue;
                }

                // 검색 결과 처리
                if (customer == null) {
                    System.out.println("❌ 해당 정보를 찾을 수 없습니다. 다시 시도해주세요.\n");
                    continue;
                } else {
                    printSearchResult(customer); // 결과 출력
                    break;
                }
            }
        }
    }

    // 검색된 고객 정보 출력
    public void printSearchResult(CustomerInfo customer) {
        System.out.println("=================================================================================================================================================");
        System.out.println("       이름       |          아이디          |             이메일             |           전화번호           |            등급            ");
        System.out.println("=================================================================================================================================================");
        System.out.printf("      %-10s         %-10s                %-15s               %-25s         %-40s\n",
                customer.getCustomerName(), customer.getUserId(), customer.getEmail(), customer.getPhone(), customer.getMembershipLevel());
        System.out.println();
    }
}
