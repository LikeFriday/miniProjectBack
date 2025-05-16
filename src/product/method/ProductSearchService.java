package product.method;

import product.DAO.ProductDAO;

import java.util.Scanner;

public class ProductSearchService {
    public void search() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색할 키워드 (상품명 또는 카테고리): ");
        String keyword = sc.nextLine();

        ProductDAO dao = new ProductDAO();
        dao.searchByNameOrCategory(keyword);
    }
}
