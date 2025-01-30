import java.util.ArrayList;

public class StandardOrder extends Order {
    public StandardOrder() {
        this.orderItems = new ArrayList<>();
    }

    /**
     * Commande standard avec une vérification de stock stricte (marge de 5 unités).
     */
    @Override
    public boolean validateOrder() {
        for (OrderItem item : orderItems) {
            if (!item.getProduct().isStockSufficient(item.getQuantity() + 5)) {

                return false;  // La commande est invalide si un produit est en rupture de stock
            }
        }
        return true;  // La commande est valide si tous les produits ont suffisamment de stock
    }
}