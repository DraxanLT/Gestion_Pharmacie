import java.util.InputMismatchException;
import java.util.Scanner;

public class ShowMenuLogin {
    private UserGestion userService;
    private Scanner scanner;


    public ShowMenuLogin(UserGestion userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenuLogin() {
        while (true) {

            System.out.print("Identifiant : ");
            String identifiant = scanner.nextLine();
            System.out.print("Mot de passe : ");
            String password = scanner.nextLine();

            // Vérification si l'utilisateur existe
            User user = userService.authenticate(identifiant, password);
            if (user != null) {
                System.out.println("Bienvenue, " + user.getRole() + " !\n");

                // Vérification si l'utilisateur est admin
                if (user.isAdmin()) {
                    int nbmenu = -1;

                    while (nbmenu != 1 && nbmenu != 2) {
                        System.out.println("1. Menu Admin");
                        System.out.println("2. Menu Employé");
                        System.out.println("3. Quitter");
                        System.out.print("Quel menu voulez-vous ? (1-3) : ");


                        if (scanner.hasNextInt()) {
                            nbmenu = scanner.nextInt();
                            scanner.nextLine();
                        } else {
                            System.out.println("Veuillez entrer un nombre valide !");
                            scanner.nextLine();
                            continue;
                        }

                        switch (nbmenu) {
                            case 1:
                                System.out.println("\nAccès au Menu Admin...\n");
                                showAdminMenu();
                                break;
                            case 2:
                                System.out.println("\nAccès au Menu Employé...\n");
                                ListProduct.printAllProducts();
                                break;
                            case 3:
                                System.out.println("Déconnexion...");
                                return;
                            default:
                                System.out.println("Option invalide, veuillez choisir entre 1 et 3.");
                        }
                    }
                } else {
                    System.out.println("\nAccès au Menu Employé...\n");
                    ListProduct.printAllProducts();
                }
            } else {
                System.out.println("Identifiant ou mot de passe incorrect.\n");
                return;
            }
        }
    }



    private void showAdminMenu() {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Supprimer un utilisateur");
            System.out.println("3. Lister les utilisateurs");
            System.out.println("4. Gérer les utilisateurs");
            System.out.println("5. Déconnexion");

            System.out.print("Choisissez une option : (1,2,3,4,5) ");

            try {
                int choix = scanner.nextInt();
                scanner.nextLine();

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
                        System.out.println("Déconnexion...");
                        return;
                    default:
                        System.out.println("Option invalide. Veuillez entrer un nombre entre 1 et 5.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Entrée invalide ! Veuillez entrer un nombre entre 1 et 5.");
                scanner.nextLine();
            }
        }
    }

}
