import java.util.ArrayList;
import java.util.List;

import java.util.Random;
import java.util.Scanner;

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

    public static void main(String[] args) {

        Pharmacy pharmacy = printAllProducts();
        OrderManager.orderMenu();

        /*Product findProduct = SearchProduct.searchProductByName(pharmacy, "vitamine c");
        Scanner sc = new Scanner(System.in);
        int nombre = 0;
        System.out.print("Entrez lgdgdsfd: ");
        nombre = sc.nextInt();



        System.out.println("il faut : "+ nombre+ " "+  findProduct.getName());

        pharmacy.showlowstock();*/

    }


}
