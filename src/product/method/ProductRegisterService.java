package product.method;

import product.DAO.ProductDAO;
import product.DTO.Product;
import product.ProductInputHandler;

public class ProductRegisterService {
    public void registerProduct() {
        String name = ProductInputHandler.inputProductName();
        int price = ProductInputHandler.inputPrice();
        int stock = ProductInputHandler.inputStock();
        int category = ProductInputHandler.inputCategoryId();
        String description = ProductInputHandler.inputDescription();

        Product p = new Product(name, price, stock, category, description, 20250225); // 관리자 ID 예시
        ProductDAO dao = new ProductDAO();
        dao.insertProduct(p);
    }
}
