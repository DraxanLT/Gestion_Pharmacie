package User;

import java.util.Scanner;

public class ShowMenuLogin {
    private UserGestion userService;
    private Scanner scanner;


    public ShowMenuLogin(UserGestion userService) {
        this.userService = userService;
        this.scanner = new Scanner(System.in);
    }

    public void showMenuLogin() {
        System.out.print("Identifiant : ");
        String identifiant = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String password = scanner.nextLine();

        User user = userService.authenticate(identifiant, password);
        if (user != null) {
            System.out.println("Bienvenue, " + user.getRole() + " !");
            if (user.isAdmin()) {
                showAdminMenu();
            }
            // ici quand c'est l'employee
        } else {
            System.out.println("Identifiant ou mot de passe incorrect.");
        }
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println("\n=== Menu Admin ===");
            System.out.println("1. Ajouter un utilisateur");
            System.out.println("2. Supprimer un utilisateur");
            System.out.println("3. Lister les utilisateurs");
            System.out.println("4. Gerer les utilisateurs");
            System.out.println("5. Déconnexion");

            System.out.print("Choisissez une option : ");
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
                    System.out.println("Option invalide.");
            }
        }
    }
}
