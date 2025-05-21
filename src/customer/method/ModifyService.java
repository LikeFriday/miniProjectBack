package customer.method;

import customer.DAO.CustomerList;
import customer.DTO.CustomerInfo;
import customer.validator.InputValidator;
import java.util.Scanner;

public class ModifyService {
    private final CustomerList list;

    // 생성자: 고객 목록 DAO 객체 주입
    public ModifyService(CustomerList list) {
        this.list = list;
    }

    // 고객 정보 수정 서비스 실행
    public void modifyService() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n**************************** 고객 수정 페이지 ***************************");

        // 사용자가 계속 원할 때까지 반복
        do {
            int modifyOption;

            // 수정할 항목 번호 입력 및 유효성 검사
            while (true) {
                System.out.print("\n수정할 항목을 입력해주세요 (1. 이름  2. 이메일  3. 전화번호  4. 회원등급  5. 종료) >> ");
                try {
                    modifyOption = Integer.parseInt(scanner.nextLine());
                    if (modifyOption >= 1 && modifyOption <= 5) break;
                    else System.out.println("❌ 1~5 사이의 숫자를 입력해주세요.\n");
                } catch (NumberFormatException e) {
                    System.out.println("❌ 숫자로 입력해주세요.\n");
                }
            }

            // 종료 선택 시 반복 종료
            if (modifyOption == 5) break;

            CustomerInfo customer = null;

            // 수정 대상 고객 아이디 입력 및 유효성 검사
            while (true) {
                System.out.print("수정할 회원의 아이디를 입력해주세요 >> ");
                String userId = scanner.nextLine();

                try {
                    InputValidator.isValidUserId(userId); // 형식 검사
                    customer = list.findUserId(userId);   // 아이디로 고객 검색
                    if (customer == null) {
                        System.out.println("❌ 해당 아이디의 회원을 찾을 수 없습니다. 다시 시도해주세요.\n");
                        continue;
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                    continue;
                }
            }

            boolean updated = false; // 수정 여부 플래그

            // 선택된 항목에 따라 정보 수정 처리
            while (true) {
                switch (modifyOption) {
                    case 1: // 이름 수정
                        System.out.print("수정할 이름을 입력해주세요 >> ");
                        String modifyName = scanner.nextLine();
                        try {
                            InputValidator.isValidName(modifyName);
                            if (modifyName.equals(customer.getCustomerName())) {
                                System.out.println("⚠️ 기존 정보와 동일하여 수정하지 않습니다.\n");
                            } else {
                                customer.setCustomerName(modifyName);
                                updated = true;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;

                    case 2: // 이메일 수정
                        System.out.print("수정할 이메일을 입력해주세요 >> ");
                        String modifyEmail = scanner.nextLine();
                        try {
                            InputValidator.isValidEmail(modifyEmail);
                            if (modifyEmail.equals(customer.getEmail())) {
                                System.out.println("⚠️ 기존 정보와 동일하여 수정하지 않습니다.\n");
                            } else {
                                customer.setEmail(modifyEmail);
                                updated = true;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;

                    case 3: // 전화번호 수정
                        System.out.print("수정할 전화번호를 입력해주세요 >> ");
                        String modifyPhone = scanner.nextLine();
                        try {
                            InputValidator.isValidPhone(modifyPhone);
                            if (modifyPhone.equals(customer.getPhone())) {
                                System.out.println("⚠️ 기존 정보와 동일하여 수정하지 않습니다.\n");
                            } else {
                                customer.setPhone(modifyPhone);
                                updated = true;
                            }
                        } catch (IllegalArgumentException e) {
                            System.out.println(e.getMessage());
                            continue;
                        }
                        break;

                    case 4: // 회원등급 수정
                        System.out.print("수정할 등급을 선택해주세요 1.FAMILY 2.BRONZE 3.SILVER 4.GOLD 5.VIP >> ");
                        int modifyLevel;
                        try {
                            modifyLevel = Integer.parseInt(scanner.nextLine());
                            if (modifyLevel < 1 || modifyLevel > 5) {
                                System.out.println("❌ 잘못 입력하셨습니다. 다시 입력해주세요\n");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("❌ 숫자로 입력해주세요.\n");
                            continue;
                        }

                        String[] levels = {"FAMILY", "BRONZE", "SILVER", "GOLD", "VIP"};
                        String selectedLevel = levels[modifyLevel - 1];

                        if (selectedLevel.equals(customer.getMembershipLevel())) {
                            System.out.println("⚠️ 기존 정보와 동일하여 수정하지 않습니다.\n");
                        } else {
                            customer.setMembershipLevel(selectedLevel);
                            updated = true;
                        }
                        break;

                    default:
                        System.out.println("❌ 잘못된 선택입니다.\n");
                        break;
                }
                break; // switch 처리 완료 후 루프 탈출
            }

            // 수정이 발생했을 경우 저장
            if (updated) {
                list.update(customer);
                System.out.println("✅ 정보가 성공적으로 수정되었습니다.\n");
            }

        } while (askContinue(scanner)); // 수정 계속 여부 확인
    }

    // 수정 계속 여부 확인 메서드
    public boolean askContinue(Scanner scanner) {
        while (true) {
            try {
                System.out.print("수정을 계속 하시겠습니까? (1. 네 2. 아니오) >> ");
                int input = Integer.parseInt(scanner.nextLine());
                if (input == 1) return true;
                if (input == 2) return false;
                System.out.println("❌ 1 또는 2를 입력해주세요.\n");
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자로 입력해주세요.\n");
            }
        }
    }
}
