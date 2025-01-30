import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class OrderJson {

    private static void saveOrdersToJsonFile() {
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

    static void loadOrdersFromJsonFile() {
        JSONParser parser = new JSONParser();

        try (FileReader reader = new FileReader("orders.json")) {
            JSONArray ordersArray = (JSONArray) parser.parse(reader);

            for (Object obj : ordersArray) {
                JSONObject orderObject = (JSONObject) obj;
                String type = (String) orderObject.get("type");
                Order order = "Urgente".equals(type) ? new UrgentOrder() : new StandardOrder();

                JSONArray itemsArray = (JSONArray) orderObject.get("items");
                for (Object itemObj : itemsArray) {
                    JSONObject itemObject = (JSONObject) itemObj;
                    String productName = (String) itemObject.get("productName");
                    int quantity = ((Long) itemObject.get("quantity")).intValue();
                    double price = ((Double) itemObject.get("price"));

                    Product product = new Product(Order.OrderItem); // Remplacer selon votre constructeur
                    order.addProductToOrder(product, quantity);
                }

                OrderManager.orders.add(order);
            }
            System.out.println("Commandes chargées avec succès !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
