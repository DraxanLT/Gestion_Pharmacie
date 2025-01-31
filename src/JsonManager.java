import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class serves as a reader of the stocks_pharma.json file in order to get the data that the user needs.
 */
public class JsonManager {

    private Gson gson;

    public JsonManager() {
        this.gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
    }

    /**
     * This method reads a JSON file and converts it to a Root object
     * @param filePath : the path of the json file
     * @return
     */
    public Root readJson(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            Root root = gson.fromJson(fileReader, Root.class);

            root.getPharmacy().getListProducts().clear();
            for (CategoryProduct categoryProduct : root.getPharmacy().getProducts()) {
                for (Product product : categoryProduct.getProducts()) {
                    root.getPharmacy().addProduct(product);
                }
            }
            return root;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}