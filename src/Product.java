import java.util.List;

public class Product {

    protected int id;
    protected String name;
    protected double price;
    protected int stockQuantity;
    protected String description;
    protected CategoryProduct category;
    protected CategoryProduct subCategory;


    public Product(int id, String name, double price, int stockQuantity, String description, CategoryProduct category, CategoryProduct subCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.category = category;
        this.subCategory = subCategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStockSufficient(int requestedQuantity) {
        /**
         * Vérifie si le stock est suffisant pour un produit donné
         *
         * @param product Le produit à vérifier
         * @param requestedQuantity La quantité demandée
         * @return true si le stock est suffisant, false sinon
         */
        return getStockQuantity() >= requestedQuantity;
    }
}
