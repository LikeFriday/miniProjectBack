package customer.method;

import customer.DAO.CustomerList;
import customer.DTO.CustomerInfo;
import customer.validator.InputValidator;

import java.util.Scanner;

public class DeleteService {

    private final CustomerList list;

    // 생성자에서 고객 목록 초기화
    public DeleteService(CustomerList list) {
        this.list = list;
    }

    // 고객 삭제 기능 실행
    public void deleteService() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("**************************** 고객 삭제 페이지 ***************************");

        while (true) {
            System.out.print("\n삭제할 회원 아이디를 입력해주세요 >> ");
            String userId = scanner.nextLine();

            // 아이디 유효성 검사
            try {
                InputValidator.isValidUserId(userId);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                continue;
            }

            // 아이디로 고객 검색
            CustomerInfo customer = list.findUserId(userId);

            if (customer == null) {
                System.out.println("❌ 해당 아이디의 회원을 찾을 수 없습니다. 다시 시도해주세요.\n");
                continue;
            }

            // 아이디가 일치하면 삭제 여부 확인
            System.out.print("정말 삭제하시겠습니까? (1. 네  2. 아니오) >> ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자로 입력해주세요.\n");
                continue;
            }

            if (choice == 1) {
                list.delete(customer);
                System.out.println("✅ 정보가 성공적으로 삭제되었습니다.\n");
            } else if (choice == 2) {
                System.out.println("⚠️ 삭제가 취소되었습니다.\n");
            } else {
                System.out.println("❌ 잘못 입력하셨습니다. 다시 입력해주세요.\n");
                continue;
            }

            // 삭제 계속 여부 확인
            if (!askContinue(scanner)) {
                System.out.println( "고객 삭제를 종료합니다.\n");
                return;
            }
        }
    }

    // 삭제 계속 여부 묻는 메서드
    public boolean askContinue(Scanner scanner) {
        while (true) {
            System.out.print("삭제를 계속 하시겠습니까? (1. 네  2. 아니오) >> ");
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 1) return true;
                else if (input == 2) return false;
                else System.out.println("❌ 잘못 입력하셨습니다. 1 또는 2를 입력해주세요.\n");
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자로 입력해주세요.\n");
            }
        }
    }
}
