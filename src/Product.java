public class Product {

    private int id;
    private String name;
    private double price;
    private int stockQuantity;
    private String description;
    private String categoryName;

    public Product(int id, String name, double price, int stockQuantity, String description, String categoryName) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.categoryName = categoryName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            this.stockQuantity = 0;
        } else {
            this.stockQuantity = stockQuantity;
        }
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryName() {
        return categoryName;
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
