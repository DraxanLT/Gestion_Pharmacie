import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {

    // Static list to store created orders
    public static List<Order> orders = new ArrayList<>();


    /**
     * Displays the main menu to manage commands.
     * User can create an order, view existing orders or exit.
     */
    public static void orderMenu() {
        Runtime.getRuntime().addShutdownHook(new Thread(OrderManager::saveOrdersToJsonFile));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;
        OrderJson.loadOrdersFromJsonFile();

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

    public static void saveOrdersToJsonFile() {
        JSONArray ordersArray = new JSONArray();

        for (Order order : OrderManager.orders) {
            JSONObject orderObject = new JSONObject();
            orderObject.put("date", order.getFormattedDate());
            orderObject.put("type", order instanceof UrgentOrder ? "Urgente" : "Standard");

            JSONArray itemsArray = new JSONArray();
            for (Order.OrderItem item : order.orderItems) {
                JSONObject itemObject = new JSONObject();
                itemObject.put("productName", item.getProduct().getName());
                itemObject.put("quantity", item.getQuantity());
                itemObject.put("price", item.getProduct().getPrice());
                itemsArray.add(itemObject);
            }

            orderObject.put("items", itemsArray);
            ordersArray.add(orderObject);
        }

        try (FileWriter file = new FileWriter("orders.json")) {
            file.write(ordersArray.toJSONString());
            file.flush();
            System.out.println("Commandes enregistrées avec succès !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new order by asking the user
     * if the order is urgent or standard, then add products.
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
             * Checks if the product exists in the list and if the quantity is available.
             * If yes, add it to the order and update the stock.
             */
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
     * Shows all saved commands.
     * If no command exists, displays a corresponding message.
     */
    private static void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("Aucune commande enregistrée.");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                System.out.println("\nCommande #" + (i + 1) + (order instanceof UrgentOrder ? " (Urgente)" : " (Standard)"));
                System.out.println("Date de commande : " + order.getFormattedDate());
                for (Order.OrderItem item : order.orderItems) {
                    System.out.println("- Produit : " + item.getProduct().getName() + ", Quantité : " + item.getQuantity());
                }
            }
        }
    }
}

