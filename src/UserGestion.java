import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserGestion  implements Serializable{
    private List<User> usersList;
    Scanner scanner = new Scanner(System.in);
    public UserGestion() {
        this.usersList = new ArrayList<>();


        usersList.add(new Admin("admin", "1234", "admin"));
        usersList.add(new Employe("employee", "0000", "employee"));
        usersList.add(new Employe("employee", "0000", "SuperEmployee"));
    }

    public User authenticate(String identifiant, String password) {
        for (User user : usersList) {
            if (user.authenticate(identifiant, password)) {
                return user;
            }
        }
        return null;
    }

    public void addUser() {
        System.out.println("Insérer un nouvel utilisateur");
        String pass;
        String idf;
        String role;

        do {
            System.out.println("Renseignez le rôle (ex: Admin, User) :");
            role = scanner.nextLine().trim();
        } while (role.isEmpty() || !role.matches("[a-zA-Z]+"));

        do {
            System.out.println("Renseignez un mot de passe :");
            pass = scanner.nextLine().trim();
        } while (pass.isEmpty());

        do {
            System.out.println("Renseignez un identifiant (lettres uniquement) :");
            idf = scanner.nextLine().trim();
        } while (idf.isEmpty() || !idf.matches("[a-zA-Z]+"));

        usersList.add(new Admin(role, pass, idf));
        System.out.println("\nL'utilisateur a bien été ajouté :)");
    }

    public void removeUser() {
//        System.out.println("Voici la Liste des utilisateurs :");
//        System.out.println(" ");
//        for (User user : usersList) {
//            System.out.println(user);
//        }
//        System.out.println(" ");
//        System.out.println("Quel utilisateur souhaiter vous supprimer ? ( 1, 2,3,etc) ");
//        int userRemoved = scanner.nextInt();
//        // scanner.nextLine();
//
//        if (userRemoved == 1) {
//            System.out.println(" ");
//            System.out.println("Vous ne pouvez pas vous supprimer vous meme");
//        }else if (userRemoved > 1) {
//            for (int i = 0; i < usersList.size(); i++) {
//                usersList.remove(usersList.get(i));
//                System.out.println("L'utilisateur : " + i + " A ete supprimer");
//            }
//            System.out.println(" ");
//        }else {
//            System.out.println("Donner un chiffre correct !");
//        }
    }

    public void gererUser() {
        System.out.println("Gérer les utilisateurs\n");

        if (usersList.isEmpty()) {
            System.out.println("Aucun utilisateur dans la liste !");
            return;
        }

        System.out.println("Liste des utilisateurs :\n");

        for (int i = 0; i < usersList.size(); i++) {
            System.out.println((i + 1) + ". " + usersList.get(i).getIdentifiant() + " (" + usersList.get(i).getRole() + ")");
        }

        System.out.println("\nRenseignez le numéro de l'utilisateur à gérer (1-" + usersList.size() + ") :");

        int userIndex = -1;
        while (true) {
            try {
                userIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                if (userIndex >= 0 && userIndex < usersList.size()) break;
                System.out.println("Numéro invalide veuillez réessayer :");
            } catch (Exception e) {
                System.out.println("Entrée invalide ! Veuillez entrer un nombre.");
                scanner.nextLine();
            }
        }

        User selectedUser = usersList.get(userIndex);
        System.out.println("Utilisateur sélectionné : " + selectedUser.getIdentifiant() + " (" + selectedUser.getRole() + ")\n");

        while (true) {
            System.out.println("1. Retirer un rôle");
            System.out.println("2. Ajouter un rôle");
            System.out.println("3. Quitter");

            System.out.print("Choisissez une option : ");
            int choix;
            try {
                choix = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Entrée invalide ! Veuillez entrer un nombre.");
                scanner.nextLine();
                continue;
            }

            switch (choix) {
                case 1:
                    selectedUser.setRole(""); // Suppression du rôle
                    System.out.println("Rôle retiré avec succès !");
                    break;
                case 2:
                    System.out.print("Entrez le nouveau rôle : ");
                    String newRole = scanner.nextLine();
                    selectedUser.setRole(newRole);
                    System.out.println("Rôle ajouté avec succès !");
                    break;
                case 3:
                    System.out.println("Retour au menu principal...");
                    return;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }
        }
    }

    public void listUsers() {
        System.out.println("Liste des utilisateurs :");
        System.out.println(" ");
        for (User user : usersList) {
            System.out.println(user);
        }
        System.out.println(" ");
    }
}
