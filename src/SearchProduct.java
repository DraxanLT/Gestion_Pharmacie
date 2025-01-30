import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;

public class SearchProduct {


    public static Product searchProductByName(Pharmacy pharmacy) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Entrez le nom du produit à rechercher : ");
        String productName = scanner.nextLine();

        // Récupérer tous les produits
        List<Product> allProducts = pharmacy.getListProducts();

        // Trier les produits par nom (ignore la casse)
        allProducts.sort(Comparator.comparing(p -> p.getName().toLowerCase()));

        // Recherche binaire
        int left = 0, right = allProducts.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            Product midProduct = allProducts.get(mid);

            int comparison = productName.compareToIgnoreCase(midProduct.getName());
            if (comparison == 0) {
                System.out.println("Produit trouvé : " + midProduct.getName() +
                        ", Quantité : " + midProduct.getStockQuantity());
                return midProduct;
            }
            if (comparison < 0) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println("Le produit '" + productName + "' n'est pas disponible.");
        return null;
    }
}
