import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {

    // Liste statique pour stocker les commandes créées
    private static List<Order> orders = new ArrayList<>();

    /**
     * Affiche le menu principal pour gérer les commandes.
     * L'utilisateur peut créer une commande, afficher les commandes existantes ou quitter.
     */
    public static void orderMenu() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\n--- Gestion des Commandes ---");
            System.out.println("1. Créer une commande");
            System.out.println("2. Afficher les commandes");
            System.out.println("3. Quitter");
            System.out.print("Choix : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour de ligne

            switch (choice) {
                case 1 -> createOrder(scanner);
                case 2 -> displayOrders();
                case 3 -> {
                    System.out.println("Fermeture du gestionnaire de commandes.");
                    exit = true;
                }
                default -> System.out.println("Choix invalide.");
            }
        }
        scanner.close();
    }

    /**
     * Crée une nouvelle commande en demandant à l'utilisateur
     * si la commande est urgente ou standard, puis ajoute des produits.
     */
    private static void createOrder(Scanner scanner) {
        System.out.print("La commande est-elle urgente ? (oui/non) : ");
        boolean isUrgent = scanner.nextLine().equalsIgnoreCase("oui");

        Order order = isUrgent ? new UrgentOrder() : new StandardOrder();

        while (true) {
            System.out.println("\n--- Ajout de produits ---");
            System.out.print("Nom du produit (ou 'valider' pour terminer) : ");
            String productName = scanner.nextLine();

            if (productName.equalsIgnoreCase("valider")) {
                break;
            }

            Product product = SearchProduct.searchProductByName(productName);
            if (product == null) {
                System.out.println("Produit introuvable.");
                continue;
            }

            System.out.println("le prix du produit est de :" + product.getPrice());
            System.out.print("Quantité souhaitée : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            /**
             * Vérifie si le produit existe dans la liste et si la quantité est disponible.
             * Si oui, l'ajoute à la commande et met à jour le stock.*/
            if (product.getStockQuantity() >= quantity) {
                order.addProductToOrder(product, quantity);
                product.updateOrder(quantity);
                System.out.println("Produit ajouté à la commande.");
            } else {
                System.out.println("Stock insuffisant. Disponible : " + product.getStockQuantity());
            }
        }

        orders.add(order);
        System.out.println("Commande " + (isUrgent ? "urgente" : "standard") + " créée avec succès !");
    }

    /**
     * Affiche toutes les commandes enregistrées.
     * Si aucune commande n'existe, affiche un message correspondant.
     */
    private static void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("Aucune commande enregistrée.");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                System.out.println("Commande #" + (i + 1) + (order instanceof UrgentOrder ? " (Urgente)" : " (Standard)"));
                for (Order.OrderItem item : order.orderItems) {
                    System.out.println("- Produit : " + item.getProduct().getName() + ", Quantité : " + item.getQuantity());
                }
            }
        }
    }
}

