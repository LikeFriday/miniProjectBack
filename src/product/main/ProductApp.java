package product.main;

import product.dao.CategoryDAO;
import product.dao.ProductDAO;
import product.model.Product;

import java.util.Map;
import java.util.Scanner;

public class ProductApp {
    private final ProductDAO productDao = new ProductDAO();
    private final CategoryDAO categoryDao = new CategoryDAO();
    private final Scanner sc = new Scanner(System.in);

    public void run() {
        while (true) {
            System.out.println("\n=== 상품 메뉴 ===");
            System.out.println("1. 상품 등록");
            System.out.println("2. 상품 전체 조회");
            System.out.println("3. 상품 삭제");
            System.out.println("4. 재고 수정");
            System.out.println("5. 키워드 검색");
            System.out.println("6. 종료");
            System.out.print("선택: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1" -> registerProduct();
                case "2" -> productDao.selectAll().forEach(System.out::println);
                case "3" -> {
                    System.out.print("삭제할 상품명: ");
                    productDao.deleteByName(sc.nextLine());
                }
                case "4" -> {
                    System.out.print("상품명 입력: ");
                    String pname = sc.nextLine();
                    System.out.print("새 재고 수량: ");
                    int newStock = Integer.parseInt(sc.nextLine());
                    productDao.updateProductStockByName(pname, newStock);
                }
                case "5" -> {
                    System.out.print("검색어 입력 (상품명/설명/카테고리): ");
                    productDao.searchProductsByKeyword(sc.nextLine());
                }
                case "6" -> {
                    System.out.println("프로그램을 종료합니다.");
                    return;
                }
                default -> System.out.println("❗ 잘못된 입력입니다.");
            }
        }
    }

    private void registerProduct() {
        System.out.print("상품명: ");
        String name = sc.nextLine();
        System.out.print("가격: ");
        int price = Integer.parseInt(sc.nextLine());
        System.out.print("재고 수량: ");
        int stock = Integer.parseInt(sc.nextLine());
        System.out.print("설명: ");
        String desc = sc.nextLine();

        categoryDao.printAllCategories();
        System.out.print("카테고리명 선택: ");
        String catName = sc.nextLine();
        Map<String, Integer> catMap = categoryDao.getCategoryMap();

        if (!catMap.containsKey(catName)) {
            System.out.println("❌ 존재하지 않는 카테고리입니다.");
            return;
        }
        int catId = catMap.get(catName);
        Product p = new Product(name, price, stock, desc, catId);
        productDao.insertProduct(p);
    }
}
