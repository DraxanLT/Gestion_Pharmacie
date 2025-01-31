import java.util.InputMismatchException;
import java.util.Scanner;

public class MenuManager {
    private UserGestion userService;
    private Scanner scanner;
    private static CSV csv = new CSV("statsDoc.csv");


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
            System.out.println(" 2. Search a product");
            System.out.println(" 3. Add a product");
            System.out.println(" 4. Delete a product");
            System.out.println(" 5. Manage storage");
            System.out.println(" 6. Order products");
            System.out.println(" 7. List orders");
            System.out.println(" 8. product statistics");
            System.out.println(" 9. Logout");
            System.out.println(" 10. Shutdown");
            System.out.println("--------------------------------------------------------");

            System.out.println("Choose an option : (1, 2, 3, 4, 5, 6, 7, 8, 9, 10)");
            System.out.println("--------------------------------------------------------");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1:
                        ListProduct.printAllProducts();
                        break;
                    case 2:
                        System.out.println("Searching for a product");
                        Product product = SearchProduct.searchProductByName(scanner.nextLine());
                        if (product == null) {
                            System.out.println("Product not found.");
                            break;
                        }
                        else {
                            System.out.println(product.getName()+ " is found, there are : "+product.getStockQuantity());
                            System.out.println("Price : " + product.getPrice());
                            break;
                        }
                    case 3:
                        ProductManager.NewProduct("stocks_pharma.json");
                        break;
                    case 4:
                        ProductManager.DeleteProduct("stocks_pharma.json");
                        break;
                    case 5:
                        Pharmacy pharmacy = ListProduct.createNewPharmacy();
                        pharmacy.showlowstock();
                        break;
                    case 6:
                        OrderManager.orderMenu();
                        break;
                    case 7:
                        OrderManager.displayOrders();
                        break;
                    case 8:
                        csv.readCSVFile();
                        csv.menuStats();
                        break;
                    case 9:
                        System.out.println("Disconnecting...");
                        System.out.println("--------------------------------------------------------");
                        return;
                    case 10:
                        System.out.println("Program Closure...");
                        System.out.println("--------------------------------------------------------");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option. Please enter a number between 1 and 10.");
                        System.out.println("--------------------------------------------------------");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 10.");
                System.out.println("--------------------------------------------------------");
                scanner.nextLine();
            }
        }
    }

}
