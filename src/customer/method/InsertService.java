package customer.method;

import customer.DTO.CustomerInfo;
import customer.DAO.CustomerList;
import customer.validator.InputValidator;
import java.util.Scanner;

public class InsertService {
    //참조 변수 자체가 다른 객체를 참조하지 않도록, 한 번 초기화된 후 다른 객체로 변경될 수 없게
    private final CustomerList list;

    // 생성자를 통해 고객 리스트 초기화
    public InsertService(CustomerList list) {
        this.list = list;
    }

    // 고객 등록 메서드
    public void insertService(Scanner scanner) {
        System.out.println("**** 고객 등록 페이지 ****");

        // 이름 입력 및 유효성 검사
        String customerName;
        while (true) {
            try {
                System.out.print("이름 : ");
                customerName = scanner.nextLine();
                InputValidator.isValidName(customerName);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 아이디 입력 및 유효성 + 중복 검사
        String userId;
        while (true) {
            try {
                System.out.print("아이디 : ");
                userId = scanner.nextLine();
                InputValidator.isValidUserId(userId);
                //중복 방지
                if(list.findUserId(userId) != null) {
                    System.out.println("이미 사용중인 아이디입니다.");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 이메일 입력 및 유효성 검사
        String email;
        while (true) {
            try {
                System.out.print("이메일 : ");
                email = scanner.nextLine();
                InputValidator.isValidEmail(email);
                //중복 방지
                if(list.findEmail(email) != null) {
                    System.out.println("이미 사용중인 이메일입니다.");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 전화번호 입력 및 유효성 검사
        String phone;
        while (true) {
            try {
                System.out.print("전화번호 (예: 010-1234-5678): ");
                phone = scanner.nextLine();
                InputValidator.isValidPhone(phone);
                //중복 방지
                if(list.findPhone(phone) != null) {
                    System.out.println("이미 사용중인 전화번호입니다.");
                    continue;
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        // 회원등급 선택
        String membershipLevel = "";
        while (true) {
            try {
                System.out.print("회원등급 1.FAMILY 2.BRONZE 3.SILVER 4.GOLD 5.VIP >> ");
                int level = Integer.parseInt(scanner.nextLine());

                switch (level) {
                    case 1: membershipLevel = "FAMILY"; break;
                    case 2: membershipLevel = "BRONZE"; break;
                    case 3: membershipLevel = "SILVER"; break;
                    case 4: membershipLevel = "GOLD"; break;
                    case 5: membershipLevel = "VIP"; break;
                    default:
                        System.out.println("잘못 입력하셨습니다. 다시 선택해주세요.");
                        continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("숫자로 입력해주세요.");
            }
        }

        // 고객 정보 객체 생성 후 리스트에 추가
        CustomerInfo customer = new CustomerInfo(customerName, userId, email, phone, membershipLevel);
        list.insert(customer);
        System.out.println("✅ 고객 등록이 완료되었습니다.");
    }
}
