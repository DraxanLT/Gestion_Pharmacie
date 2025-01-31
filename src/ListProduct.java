import java.util.List;

public class ListProduct {

    /**
     * This method shows all products that are inside a Json file (using readJson method) & sort them alphabetically.
     * @return
     */
    public static Pharmacy printAllProducts() {
        JsonManager jsonManager = new JsonManager();
        Root root = jsonManager.readJson("stocks_pharma.json");

        if (root != null) {
            Pharmacy pharmacy = root.getPharmacy();
            List<Product> allProducts = pharmacy.getListProducts();

            if (allProducts.isEmpty()) {
                System.out.println("No products available.");
                return null;
            }

            Sorting.sortAlphabetically(allProducts);

            System.out.println("List of all products (A -> Z) :\n");
            allProducts.forEach(product -> System.out.println("Name: " + product.getName()
                    + " | Price: " + product.getPrice()
                    + " EUR | Quantity: " + product.getStockQuantity()
                    + " | Category: " + product.getCategoryName()));

            return pharmacy;
        } else {
            System.out.println("Error loading products.");
            return null;
        }
    }

    public static Pharmacy createNewPharmacy() {
        JsonManager jsonManager = new JsonManager();
        Root root = jsonManager.readJson("stocks_pharma.json");

            if (root != null) {
                Pharmacy pharmacy = root.getPharmacy();
                List<Product> allProducts = pharmacy.getListProducts();

                if (allProducts.isEmpty()) {
                    System.out.println("No products available.");
                    return null;
                }

                return pharmacy;
            } else {
                System.out.println("Error loading products.");
                return null;
            }
        }
    }

