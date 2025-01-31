import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class SearchProduct  implements Serializable {

    /**
     * Search for a product by name in the sorted product list.
     * Uses a binary search for better performance.
     * @param productName Name of product to search for.
     * @return Matching product, or null if not found.
     */
    public static Product searchProductByName(String productName) {

        Pharmacy pharmacy = ListProduct.createNewPharmacy();
        List<Product> allProducts = pharmacy.getListProducts();

        // Sort products by name (case-insensitive)
        allProducts.sort(Comparator.comparing(p -> p.getName().toLowerCase()));

        // Binary search
        int left = 0, right = allProducts.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = allProducts.get(mid);

            int comparison = productName.compareToIgnoreCase(midProduct.getName());
            if (comparison == 0) {
                return midProduct;
            }
            if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return null;
    }
}
