package product;

import java.util.Scanner;

public class ProductInputHandler {
    private static final Scanner sc = new Scanner(System.in);

    // 상품명 입력: 1~50자, 한글/영문/숫자/공백만 허용, 특수문자 및 공백-only 금지
    public static String inputProductName() {
        String name;
        while (true) {
            System.out.print("상품명을 입력하세요 (1~50자, 특수문자 제외): ");
            name = sc.nextLine().trim();

            if (name.isEmpty() || name.isBlank()) {
                System.out.println("❌ 공백만 입력할 수 없습니다.");
                continue;
            }

            if (!name.matches("^[가-힣a-zA-Z0-9 ]{1,50}$")) {
                System.out.println("❌ 한글, 영문, 숫자, 공백만 사용 가능하며 특수문자는 제외해야 합니다.");
                continue;
            }

            break;
        }
        return name;
    }

    // 판매가 입력: 정수, 100원 이상 9,999,999원 이하, 소수점/음수 불가
    public static int inputPrice() {
        int price;
        while (true) {
            System.out.print("판매가를 입력하세요 (100 ~ 9,999,999): ");
            try {
                price = Integer.parseInt(sc.nextLine());
                if (price >= 100 && price <= 9999999) {
                    break;
                } else {
                    System.out.println("❌ 가격은 100원 이상 9,999,999원 이하만 가능합니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자만 입력하세요.");
            }
        }
        return price;
    }

    // 재고 수량 입력: 정수, 0 이상 9999 이하
    public static int inputStock() {
        int stock;
        while (true) {
            System.out.print("재고 수량을 입력하세요 (0 ~ 9999): ");
            try {
                stock = Integer.parseInt(sc.nextLine());
                if (stock >= 0 && stock <= 9999) {
                    break;
                } else {
                    System.out.println("❌ 재고 수량은 0 이상 9,999 이하만 가능합니다.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자만 입력하세요.");
            }
        }
        return stock;
    }

    // 카테고리 선택 입력: 1~3 중 하나, 상의/하의/아우터
    public static int inputCategoryId() {
        int choice;
        while (true) {
            System.out.println("카테고리를 선택하세요: 1. 상의 / 2. 하의 / 3. 아우터");
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= 1 && choice <= 3) {
                    break;
                } else {
                    System.out.println("❌ 1~3 사이의 번호를 선택하세요.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ 숫자만 입력하세요.");
            }
        }
        return choice;
    }

    // 설명 입력: 최대 2000자
    public static String inputDescription() {
        String desc;
        while (true) {
            System.out.print("상품 설명을 입력하세요 (최대 2000자): ");
            desc = sc.nextLine().trim();
            if (desc.length() > 2000) {
                System.out.println("❌ 설명은 2000자 이하로 입력하세요.");
            } else {
                break;
            }
        }
        return desc;
    }
}
