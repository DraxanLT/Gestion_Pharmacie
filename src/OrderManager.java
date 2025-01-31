import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManager {

    // Liste statique pour stocker les commandes créées
    private static CSV csv = new CSV("statsDoc.csv");

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
            System.out.println("\n--- Order Management ---");
            System.out.println("1. Create an order");
            System.out.println("2. Display orders");
            System.out.println("3. Quit");
            System.out.print("Choice : ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour de ligne

            switch (choice) {
                case 1 -> createOrder(scanner);
                case 2 -> displayOrders();
                case 3 -> {
                    System.out.println("Close order manager.");
                    exit = true;
                }
                default -> System.out.println("Invalid choice.");
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
            System.out.println("Successful orders !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new order by asking the user
     * if the order is urgent or standard, then add products.
     */
    private static void createOrder(Scanner scanner) {
        System.out.print("Is the order urgent (yes/no) : ");
        boolean isUrgent = scanner.nextLine().equalsIgnoreCase("yes");

        Order order = isUrgent ? new UrgentOrder() : new StandardOrder();

        while (true) {
            System.out.println("\n--- Add products ---");
            System.out.print("Product name (or 'validate' to finish) : ");
            String productName = scanner.nextLine();

            if (productName.equalsIgnoreCase("validate")) {
                break;
            }

            Product product = SearchProduct.searchProductByName(productName);
            if (product == null) {
                System.out.println("Product not found.");
                continue;
            }

            System.out.println("the price of the product is :" + product.getPrice());
            System.out.print("Quantity required : ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            /**
             * Checks if the product exists in the list and if the quantity is available.
             * If yes, add it to the order and update the stock.
             */
            if (product.getStockQuantity() >= quantity) {
                order.addProductToOrder(product, quantity);
                product.updateOrder(quantity);
                csv.readCSVFile();
                csv.addNewOrder(product, quantity);
                System.out.println("Product added to order.");
            } else {
                System.out.println("Insufficient stock. Available in : " + product.getStockQuantity());
            }
        }

        orders.add(order);
        System.out.println("Order " + (isUrgent ? "urgent" : "standard") + " created successfully !");
    }

    /**
     * Shows all saved commands.
     * If no command exists, displays a corresponding message.
     */
    public static void displayOrders() {
        if (orders.isEmpty()) {
            System.out.println("No orders recorded.");
        } else {
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                System.out.println("\nOrder #" + (i + 1) + (order instanceof UrgentOrder ? " (Urgent)" : " (Standard)"));
                System.out.println("Order date : " + order.getFormattedDate());
                for (Order.OrderItem item : order.orderItems) {
                    System.out.println("- Product : " + item.getProduct().getName() + ", Quantity : " + item.getQuantity());
                }
            }
        }
    }
}

