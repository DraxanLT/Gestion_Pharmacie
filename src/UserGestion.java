import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserGestion {

    // Liste Contenant les Utilisateur en objet
    private List<User> usersList;
    Scanner scanner = new Scanner(System.in);
    public UserGestion() {
        this.usersList = new ArrayList<>();


    // List des Utilisateurs  Admin Employee
        usersList.add(new Admin("admin", "1234", "admin"));
        usersList.add(new Client("client", "0000", "client"));
        usersList.add(new Employe("employee", "0000", "employee"));
        usersList.add(new Employe("employee", "0000", "SuperEmployee"));

    }


    // Function D'autentification vefification
    public User authenticate(String identifiant, String password) {
        for (User user : usersList) {
            if (user.authenticate(identifiant, password)) {
                return user;
            }
        }
        return null;
    }



    // Methode addUser Permet au Admin de prouvoir Ajouter des Utilisateur au programme
    public void addUser() {
        System.out.println("Insérer un nouvel utilisateur");
        String pass;
        String idf;
        String role;

        // boucle qui va verifier si ce qui est demander est bon
        // donner le nom sans espace tant que c'est pas bon il redemande
        do {
            System.out.println("Renseignez le rôle (ex: Admin, Client, Employee) :");
            role = scanner.nextLine().trim();
        } while (role.isEmpty() || !role.matches("[a-zA-Z]+"));

        // boucle pour le mdp
        do {
            System.out.println("Renseignez un mot de passe :");
            pass = scanner.nextLine().trim();
        } while (pass.isEmpty());
        // boucle pour l'identifiant
        do {
            System.out.println("Renseignez un identifiant (lettres uniquement) :");
            idf = scanner.nextLine().trim();
        } while (idf.isEmpty() || !idf.matches("[a-zA-Z]+"));

        // ajout des renseignement dans la liste users
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


    // methode qui va venir gerer les Role des Utilisateurs grace au profil d'admin
    public void gererUser() {
        System.out.println("Gérer les utilisateurs\n");

        //Verification si la liste est vide ou pas
        if (usersList.isEmpty()) {
            System.out.println("Aucun utilisateur dans la liste !");
            return;
        }

        System.out.println("Liste des utilisateurs :\n");

        // boucle qui va print chaque User de la liste pour afficher la liste complete
        for (int i = 0; i < usersList.size(); i++) {
            System.out.println((i + 1) + ". " + usersList.get(i).getIdentifiant() + " (" + usersList.get(i).getRole() + ")");
        }

        System.out.println("\nRenseignez le numéro de l'utilisateur à gérer (1-" + usersList.size() + ") :");


        // boucle qui va venir verifier l'entrer de l'utilisateur et le save dans la variable userIndex
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

        // avec userIndex on va le selectionner dans la liste
        User selectedUser = usersList.get(userIndex);
        System.out.println("Utilisateur sélectionné : " + selectedUser.getIdentifiant() + " (" + selectedUser.getRole() + ")\n");


        //boucle qui va demander qu'es ce que l'admin veux faire avec cette user
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

            // switch pour en case 1 : suppprimer le role
            // case 2 : pour ajouter le role avec .setRole
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


    // Affichier la liste des Utilisateur dans le programme
    public void listUsers() {
        System.out.println("Liste des utilisateurs :");
        System.out.println(" ");
        for (User user : usersList) {
            System.out.println(user);
        }
        System.out.println(" ");
    }
}
