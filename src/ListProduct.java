import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.util.Scanner;

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
    public static void main(String[] args) {
        Pharmacy pharmacy = new Pharmacy("PharmaPlus", "123 Rue de la Santé, Paris");
        Product findProduct = SearchProduct.searchProductByName(pharmacy);
        Scanner sc = new Scanner(System.in);
        int nombre = 0;
        System.out.print("Entrez lgdgdsfd: ");
        nombre = sc.nextInt();


        pharmacy.showlowstock();

    }


    public void urgentOrder(Product product, int quantity) {
        if(product.getStockQuantity() < quantity) {
            System.out.println("Le produit est en rupture de stock");
        }
        else{
            product.updateOrder(quantity);
        }

    }

    public void standardOrder(Product product, int quantity) {
        if(product.getStockQuantity()-5 < quantity) {
            System.out.println("Le produit n'est plus disponible");
        }
        else{
            product.updateOrder(quantity);
        }
    }
}
