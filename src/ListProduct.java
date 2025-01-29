import java.util.ArrayList;
import java.util.List;

public class ListProduct {

    public static List<Product> listAllProducts(Pharmacy pharmacy) {
        List<Product> allProducts = new ArrayList<>();
        pharmacy.getProducts().forEach(categoryProduct -> allProducts.addAll(categoryProduct.getProducts()));
        return allProducts;
    }

    public static void printAllProducts() {
        JsonManager jsonManager = new JsonManager();

        Root root = jsonManager.readJson("stocks_pharma.json");

        if (root != null) {
            Pharmacy pharmacy = root.getPharmacy();

            List<Product> allProducts = listAllProducts(pharmacy);

            Sorting.sortAlphabetically(allProducts);

            System.out.println("List of all products (A -> Z) :");
            System.out.print('\n');
            allProducts.forEach(product -> {
                System.out.println("Name : " + product.getName()
                        + " | Price : " + product.getPrice()
                        + " eur | Quantity : " + product.getStockQuantity()
                        + " | " + "Category : " + product.getCategory().getCategory());
            });
        } else {
            System.out.println("Error");
        }
    }
}
