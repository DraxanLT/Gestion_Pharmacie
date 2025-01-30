import User.UserGestion;

public class Main {

    public static void main(String[] args) {

        UserGestion userService = new UserGestion();
        MenuManager showMenu = new MenuManager(userService);
        showMenu.showMenuLogin();
    }
}