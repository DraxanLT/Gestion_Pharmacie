import User.User;

import java.util.InputMismatchException;
import java.util.Scanner;
import User.*;

public class MenuManager {
    private UserGestion userService;
    private Scanner scanner;


    public MenuManager(UserGestion userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenuLogin() {
        while (true) {

            System.out.println("\n---------------------- PharmaPlus ----------------------");
            System.out.print(" login : ");
            String login = scanner.nextLine();
            System.out.print(" Password : ");
            String password = scanner.nextLine();
            System.out.println("--------------------------------------------------------");

            // Vérification si l'utilisateur existe
            User user = userService.authenticate(login, password);
            if (user != null) {

                System.out.println(" Welcome, " + user.getRole() + " !");

                // Vérification si l'utilisateur est admin
                if (user.isAdmin()) {
                    int nbmenu = -1;

                    while (nbmenu != 1 && nbmenu != 2) {
                        System.out.println("-------------------- Control Panel ---------------------");
                        System.out.println(" 1. Admin Menu");
                        System.out.println(" 2. Employee Menu");
                        System.out.println(" 3. Leave");
                        System.out.println(" 4. Shutdown");
                        System.out.println("--------------------------------------------------------");

                        System.out.println(" Choose an option (1, 2, 3, 4) : ");

                        if (scanner.hasNextInt()) {
                            nbmenu = scanner.nextInt();
                            scanner.nextLine();
                            System.out.println("--------------------------------------------------------");
                        } else {
                            System.out.println("--------------------------------------------------------");
                            System.out.println("Invalid option. Please enter a number between 1 and 4.");
                            scanner.nextLine();
                            continue;
                        }

                        switch (nbmenu) {
                            case 1:
                                System.out.println("Access granted to Admin Menu...");
                                showAdminMenu();
                                break;
                            case 2:
                                System.out.println("Access granted to Employee Menu...");
                                showEmployeeMenu();
                                break;
                            case 3:
                                System.out.println("Disconnecting...");
                                return;
                            case 4:
                                System.out.println("Program Closure...");
                                System.out.println("--------------------------------------------------------");
                                System.exit(0);
                            default:
                                System.out.println("Invalid option. Please enter a number between 1 and 4.");
                        }
                    }
                } else {
                    System.out.println("Access granted to Employee Menu...");
                    showEmployeeMenu();
                }
            } else {
                System.out.println("--------------------------------------------------------");
                System.out.println("Login or password incorrect.");
            }
        }
    }



    private void showAdminMenu() {
        while (true) {
            System.out.println("\n---------------------- Admin Menu ----------------------");
            System.out.println(" 1. Add a user");
            System.out.println(" 2. Delete a user");
            System.out.println(" 3. List users");
            System.out.println(" 4. Manage users");
            System.out.println(" 5. Logout");
            System.out.println(" 6. Shutdown");
            System.out.println("--------------------------------------------------------");

            System.out.println(" Choose an option : (1, 2, 3, 4, 5, 6)");


            try {
                int choix = scanner.nextInt();
                scanner.nextLine();
                System.out.println("--------------------------------------------------------");

                switch (choix) {
                    case 1:
                        userService.addUser();
                        break;
                    case 2:
                        userService.removeUser();
                        break;
                    case 3:
                        userService.listUsers();
                        break;
                    case 4:
                        userService.gererUser();
                        break;
                    case 5:
                        System.out.println("Disconnecting...");
                        System.out.println("--------------------------------------------------------");
                        return;
                    case 6:
                        System.out.println("Program Closure...");
                        System.out.println("--------------------------------------------------------");
                        System.exit(0);
                    default:
                        System.out.println("---------------------------------------------------------");
                        System.out.println("Invalid option. Please enter a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println("---------------------------------------------------------");
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
                scanner.nextLine();
            }
        }
    }

    private void showEmployeeMenu() {
        while (true) {

            System.out.println("-------------------- Employee Menu ---------------------");
            System.out.println(" 1. List products");
            System.out.println(" 2. Add a product");
            System.out.println(" 3. Delete a product");
            System.out.println(" 4. Manage storage");
            System.out.println(" 5. Order products");
            System.out.println(" 6. List orders");
            System.out.println(" 7. Logout");
            System.out.println(" 8. Shutdown");
            System.out.println("--------------------------------------------------------");

            System.out.println("Choose an option : (1, 2, 3, 4, 5, 6, 7, 8)");
            System.out.println("--------------------------------------------------------");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        ListProduct.printAllProducts();
                        break;
                    case 2:
                        // Add product
                        break;
                    case 3:
                        // Delete product
                        break;
                    case 4:
                        // Stock
                        break;
                    case 5:
                        OrderManager.orderMenu();
                        break;
                    case 6:
                        // Historique commandes
                        break;
                    case 7:
                        System.out.println("Disconnecting...");
                        System.out.println("--------------------------------------------------------");
                        return;
                    case 8:
                        System.out.println("Program Closure...");
                        System.out.println("--------------------------------------------------------");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 8.");
                        System.out.println("--------------------------------------------------------");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                System.out.println("--------------------------------------------------------");
                scanner.nextLine();
            }
        }
    }

}
