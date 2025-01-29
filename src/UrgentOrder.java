import java.util.ArrayList;

public class UrgentOrder extends Order {
    public UrgentOrder() {
        this.orderItems = new ArrayList<>();
    }

    @Override
    public boolean validateOrder() {
        for (OrderItem item : orderItems) {
            if (!item.getProduct().isStockSufficient(item.getQuantity())) {
                System.out.println("Le produit " + item.getProduct().getName() + " est en rupture de stock pour cette commande urgente.");
                return false;
            }
        }
        return true;
    }
}