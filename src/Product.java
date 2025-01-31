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

    /**
     * This function give the drug identifier
     *
     * @return the drug identifier
     */
    public int getId() {

        return id;
    }

    /**
     * This function put a new drug identifier
     *
     * @param id : the new drug identifier
     */
    public void setId(int id) {

        this.id = id;
    }

    /**
     * This function give the name of the product
     *
     * @return the name of the product
     */
    public String getName() {

        return name;
    }

    /**
     * This function put a new product's name
     *
     * @param name : the new product's name
     */
    public void setName(String name) {

        this.name = name;
    }

    /**
     * This function give the price product
     *
     * @return the price of the product
     */
    public double getPrice() {

        return price;
    }

    /**
     * This function put a new product's price
     *
     * @param price : the new product's price
     */
    public void setPrice(double price) {

        this.price = price;
    }

    /**
     * This function give the stock of the product
     *
     * @return the name of the product's stock
     */
    public int getStockQuantity() {

        return stockQuantity;
    }

    /**
     * This function put a new product's stock quantity
     *
     * @param stockQuantity : the new product's stock quantity
     */
    public void setStockQuantity(int stockQuantity) {

        if (stockQuantity < 0) {
            this.stockQuantity = 0;
        } else {
            this.stockQuantity = stockQuantity;
        }
    }

    /**
     * This function the product's description
     *
     * @return the product's description
     */
    public String getDescription() {

        return description;
    }

    /**
     * This function put a new product's description
     *
     * @param description : the new product's description
     */
    public void setDescription(String description) {

        this.description = description;
    }

    /**
     * This function remove the order's quantity of the product's quantity
     *
     * @return the order's quantity without the product's quantity
     */
    public void updateOrder(int nombre){

        stockQuantity -=nombre;
    }


    /**
     * This function give the product's category
     *
     * @return the product's category
     */
    public String getCategoryName() {

        return categoryName;
    }

    /**
     * This function put a new product's category
     *
     * @param category : the new product's category
     */
    public void setCategoryName(String category) {

        this.categoryName = category;
    }

    /**
     * Checks whether there is sufficient stock for a given product
     *
     * @param requestedQuantity Quantity requested
     * @return true if sufficient stock, false otherwise
     */
    public boolean isStockSufficient(int requestedQuantity) {

        return getStockQuantity() >= requestedQuantity;
    }
}
