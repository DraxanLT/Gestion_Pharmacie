import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OrderJson {


    public static void loadOrdersFromJsonFile() {
        try (FileReader reader = new FileReader("C:/Users/eband/Documents/Pharmacie/Gestion_Pharmacie/orders.json")) {
            JSONParser jsonParser = new JSONParser();
            JSONArray ordersArray = (JSONArray) jsonParser.parse(reader);

            for (Object orderObj : ordersArray) {
                JSONObject orderJson = (JSONObject) orderObj;

                String date = (String) orderJson.get("date");
                String type = (String) orderJson.get("type");

                Order order = type.equals("Urgente") ? new UrgentOrder() : new StandardOrder();

                JSONArray itemsArray = (JSONArray) orderJson.get("items");

                for (Object itemObj : itemsArray) {
                    JSONObject itemJson = (JSONObject) itemObj;

                    String productName = (String) itemJson.get("productName");
                    Long quantity = (Long) itemJson.get("quantity"); // JSON retourne des Long
                    Double price = (Double) itemJson.get("price");

                    Product product = SearchProduct.searchProductByName(productName);
                    if (product != null) {
                        order.addProductToOrder(product, quantity.intValue());
                    }
                }
                OrderManager.orders.add(order);
            }
            System.out.println("Commandes chargées depuis orders.json");
        } catch (FileNotFoundException e) {
            System.out.println("Fichier orders.json introuvable. Aucun chargement effectué.");
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }
    }

}
