import com.google.gson.*;
import java.io.FileReader;
import java.io.IOException;

/**
 * This class serves as a reader, writer of .json files in order to get the datas that the admin needs.
 */
public class JsonManager {

    private Gson gson;

    public JsonManager() {
        this.gson = new Gson();
    }

    public Root readJson(String filePath) {
        try (FileReader fileReader = new FileReader(filePath)) {
            Root root = gson.fromJson(fileReader, Root.class);

            if (filePath.equals("stocks_pharma.json")) {
                for (CategoryProduct categoryProduct : root.getPharmacy().getProducts()) {

                    for (Product product : categoryProduct.getProducts()) {
                        root.getPharmacy().addProduct(product);
                        product.setCategory(categoryProduct);
                    }
                }
                return root;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }
}

