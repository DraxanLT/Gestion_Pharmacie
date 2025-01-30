import java.util.List;

public class ListProduct {

    public static Pharmacy printAllProducts() {
        JsonManager jsonManager = new JsonManager();

        Root root = jsonManager.readJson("stocks_pharma.json");

        if (root != null) {
            Pharmacy pharmacy = root.getPharmacy();

            List<Product> allProducts = pharmacy.getListProducts();

            Sorting.sortAlphabetically(allProducts);

            System.out.println("List of all products (A -> Z) :");
            System.out.print('\n');
            allProducts.forEach(product -> {
                System.out.println("Name : " + product.getName()
                        + " | Price : " + product.getPrice()
                        + " eur | Quantity : " + product.getStockQuantity()
                        + " | " + "Category : " + product.getCategory().getCategory());
            });
            System.out.println();
            return pharmacy;
        } else {
            System.out.println("Error");
        }
        return null;
    }
}
