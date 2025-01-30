public class Main {

    public static void main(String[] args) {
        ListProduct.printAllProducts();
        UserGestion userService = new UserGestion();
        ShowMenuLogin showMenu = new ShowMenuLogin(userService);
        showMenu.showMenuLogin(); // Lancement du men
    }
}