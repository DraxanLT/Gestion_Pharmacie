import java.util.List;

/**
 * Classe abstraite représentant une commande.
 * Elle contient une liste d'articles commandés.
 */
public abstract class Order {

    protected List<OrderItem> orderItems;  // Liste des produits commandés avec leur quantité

    // Méthode pour valider la commande
    public abstract boolean validateOrder();

    /**
     * Ajoute un produit avec une quantité spécifique à la commande.
     * @param product Produit à ajouter.
     * @param quantity Quantité du produit.
     */
    public void addProductToOrder(Product product, int quantity) {
        orderItems.add(new OrderItem(product, quantity));
    }

    // Classe interne représentant un article de commande
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
}
