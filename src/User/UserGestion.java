package User;

import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserGestion {
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

        System.out.println("Inserer un nouvel user");
        System.out.println("rensignez nom role :");
        String role = scanner.nextLine();
        System.out.println("rensignez password :");
        String pass = scanner.nextLine();
        System.out.println("rensignez identifiant :");
        String idf = scanner.nextLine();
        usersList.add(new Admin(role, pass, idf));
        System.out.println(" ");
        System.out.println(" L'utilisateur a bien ete Ajouter :) ");
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

    public void gererUser(){
        System.out.println("Gerer les utilisateur");
        System.out.println("");
        System.out.println("Liste des utilisateurs :");
        System.out.println(" ");
        for (User user : usersList) {
            System.out.println(user);
        }
        System.out.println("");
        System.out.println("renseignez l'utilisateur a gerer ! (1,2,3,etc...) ");
        int userGerer = scanner.nextInt();
        System.out.println(" ");

        while (true) {

            System.out.println("1. Retirer un role");
            System.out.println("2. Ajouter un role");
            System.out.println("3. Quitter");

            System.out.print("Choisissez une option : ");
            int choix = scanner.nextInt();
            scanner.nextLine();

            switch (choix) {
                case 1:
                    usersList.get(1);

            }
        }


    }

    public void listUsers() {
        System.out.println("Liste des utilisateurs :");
        System.out.println(" ");
        for (User user : usersList) {
            System.out.println(user);
        }

    }
}
