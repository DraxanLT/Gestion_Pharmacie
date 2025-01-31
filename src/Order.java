import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing an order.
 * It contains a list of ordered items.
 */
public abstract class Order {

    protected List<OrderItem> orderItems; // List of products ordered with quantity
    protected LocalDateTime orderDateTime;

    public Order() {
        this.orderItems = new ArrayList<>();
        this.orderDateTime = LocalDateTime.now(); // Initializes with current date and time
    }

    // Order validation method
    public abstract boolean validateOrder();

    /**
     * Adds a product with a specific quantity to the order.
     *@param product Product to add.
     *@param quantity Product quantity.
     */
    public void addProductToOrder(Product product, int quantity) {
        orderItems.add(new OrderItem(product, quantity));
    }

    // Internal class representing an order item
    public static class OrderItem {
        private Product product;
        private int quantity;

        public OrderItem(Product product, int quantity) {
            this.product = product;
            this.quantity = quantity;
        }

        public Product getProduct() {
            return product;
        }

        public int getQuantity() {
            return quantity;
        }

    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    // Custom format for date display
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return orderDateTime.format(formatter);
    }
}
