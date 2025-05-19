package product.main;

import product.method.ProductDeleteService;
import product.method.ProductRegisterService;
import product.method.ProductSearchService;
import product.method.ProductUpdateService;

import java.util.Scanner;

public class ProductApp {
    public static void run() {
        Scanner sc = new Scanner(System.in);

        ProductRegisterService registerService = new ProductRegisterService();
        ProductUpdateService updateService = new ProductUpdateService();
        ProductDeleteService deleteService = new ProductDeleteService();
        ProductSearchService searchService = new ProductSearchService();

        while (true) {
            System.out.println("\n=== 상품 관리 메뉴 ===");
            System.out.println("1. 상품 등록");
            System.out.println("2. 재고 수정");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 상품 검색");
            System.out.println("5. 종료");

            System.out.print("👉 메뉴 선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> registerService.registerProduct();
                case "2" -> updateService.updateStock();
                case "3" -> deleteService.deleteProduct();
                case "4" -> searchService.search();
                case "5" -> {
                    System.out.println("🚪 프로그램을 종료합니다.");
                    return;
                    
                }
                default -> System.out.println("❌ 잘못된 입력입니다. 1~5 사이의 숫자를 입력하세요.");
            }
        }
    }
}
