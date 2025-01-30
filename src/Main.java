import java.awt.*;
import java.io.File;

public class Main {

    public static void main(String[] args) {
        UserGestion userService = new UserGestion();
        ShowMenuLogin showMenu = new ShowMenuLogin(userService);
        showMenu.showMenuLogin(); // Lancement du menu
        Pharmacy pharmacy = ListProduct.printAllProducts();

        File file = new File("products.txt");

       Serializable.serializeToFile(pharmacy.getListProducts(), file);

    }
}