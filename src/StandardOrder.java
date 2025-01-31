import java.util.ArrayList;

public class StandardOrder extends Order {
    public StandardOrder() {
        this.orderItems = new ArrayList<>();
    }

    /**
     * Standard order with strict stock check (margin of 5 units).
     */
    @Override
    public boolean validateOrder() {
        for (OrderItem item : orderItems) {
            if (!item.getProduct().isStockSufficient(item.getQuantity() + 5)) {

                return false;  // The order is invalid if a product is out of stock
            }
        }
        return true;  // The order is valid if all products have sufficient stock
    }
}