import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {

    private static List<Order> orders = new ArrayList<>();
    private static Pharmacy pharmacy;

    public void CommandManager(Pharmacy pharmacy) {
        this.pharmacy = pharmacy;
    }

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

            Product product = SearchProduct.searchProductByName(pharmacy, productName);
            if (product == null) {
                System.out.println("Produit introuvable.");
                continue;
            }

            System.out.print("Quantité souhaitée : ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour de ligne

            if (product.getStockQuantity() >= quantity) {
                order.addProductToOrder(product, quantity);
                product.setStockQuantity(product.getStockQuantity() - quantity);
                System.out.println("Produit ajouté à la commande.");
            } else {
                System.out.println("Stock insuffisant. Disponible : " + product.getStockQuantity());
            }
        }

        orders.add(order);
        System.out.println("Commande " + (isUrgent ? "urgente" : "standard") + " créée avec succès !");
    }

    private static void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("Aucune commande enregistrée.");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                System.out.println("Commande #" + (i + 1) + " : " + orders.get(i));
            }
        }
    }
}

