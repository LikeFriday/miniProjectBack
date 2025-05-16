package product.DTO;

public class Product {
    private String pdname;
    private int price;
    private int stock;
    private int categoryId;
    private String description;
    private int createdBy;

    // Constructor
    public Product(String pdname, int price, int stock, int categoryId, String description, int createdBy) {
        this.pdname = pdname;
        this.price = price;
        this.stock = stock;
        this.categoryId = categoryId;
        this.description = description;
        this.createdBy = createdBy;
    }

    // Getters
    public String getPdname() { return pdname; }
    public int getPrice() { return price; }
    public int getStock() { return stock; }
    public int getCategoryId() { return categoryId; }
    public String getDescription() { return description; }
    public int getCreatedBy() { return createdBy; }
}
