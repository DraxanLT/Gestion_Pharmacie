import java.util.List;

public class Product implements Serializable {

    protected int id;
    protected String name;
    protected double price;
    protected int stockQuantity;
    protected String description;
    protected CategoryProduct category;


    public Product(int id, String name, double price, int stockQuantity, String description, CategoryProduct category, CategoryProduct subCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        /**
         * This function give the drug identifier
         *
         * @return the drug identifier
         */
        return id;
    }

    public void setId(int id) {
        /**
         * This function put a new drug identifier
         *
         * @param id : the new drug identifier
         */
        this.id = id;
    }

    public String getName() {
        /**
         * This function give the name of the product
         *
         * @return the name of the product
         */
        return name;
    }

    public void setName(String name) {
        /**
         * This function put a new product's name
         *
         * @param name : the new product's name
         */
        this.name = name;
    }

    public double getPrice() {
        /**
         * This function give the price product
         *
         * @return the price of the product
         */
        return price;
    }

    public void setPrice(double price) {
        /**
         * This function put a new product's price
         *
         * @param price : the new product's price
         */
        this.price = price;
    }

    public int getStockQuantity() {
        /**
         * This function give the stock of the product
         *
         * @return the name of the product's stock
         */
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        /**
         * This function put a new product's stock quantity
         *
         * @param stockQuantity : the new product's stock quantity
         */
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        /**
         * This function the product's description
         *
         * @return the product's description
         */
        return description;
    }

    public void setDescription(String description) {
        /**
         * This function put a new product's description
         *
         * @param description : the new product's description
         */
        this.description = description;
    }

    public void updateOrder(int nombre){
        /**
         * This function remove the order's quantity of the product's quantity
         *
         * @return the order's quantity without the product's quantity
         */
        stockQuantity -=nombre;
    }



    public CategoryProduct getCategory() {
        /**
         * This function give the product's category
         *
         * @return the product's category
         */
        return category;
    }

    public void setCategory(CategoryProduct category) {
        /**
         * This function put a new product's category
         *
         * @param category : the new product's category
         */
        this.category = category;
    }

    public boolean isStockSufficient(int requestedQuantity) {
        /**
         * Checks whether there is sufficient stock for a given product
         *
         * @param product The product to check
         * @param requestedQuantity Quantity requested
         * @return true if sufficient stock, false otherwise
         */
        return getStockQuantity() >= requestedQuantity;
    }
}
