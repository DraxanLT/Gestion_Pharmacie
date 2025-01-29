import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class SearchProduct {


    public static Product searchProductByName(Pharmacy pharmacy, String productName) {

        List<Product> allProducts = pharmacy.getProducts();

        // Trier les produits par nom (ignore la casse)
        allProducts.sort(Comparator.comparing(p -> p.getName().toLowerCase()));

        // Recherche binaire
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
