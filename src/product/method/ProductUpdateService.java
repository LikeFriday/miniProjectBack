package product.method;

import product.DAO.ProductDAO;
import product.ProductInputHandler;

import java.util.Scanner;

public class ProductUpdateService {
    public void updateStock() {
        Scanner sc = new Scanner(System.in);
        System.out.print("수정할 상품 ID: ");
        int id = Integer.parseInt(sc.nextLine());
        int stock = ProductInputHandler.inputStock();

        ProductDAO dao = new ProductDAO();
        dao.updateStock(id, stock);
    }
}
