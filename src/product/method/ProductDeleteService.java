package product.method;

import product.DAO.ProductDAO;

import java.util.Scanner;

public class ProductDeleteService {
    public void deleteProduct() {
        Scanner sc = new Scanner(System.in);
        System.out.print("삭제할 상품 ID: ");
        int id = Integer.parseInt(sc.nextLine());

        ProductDAO dao = new ProductDAO();
        dao.deleteProduct(id);
    }
}
