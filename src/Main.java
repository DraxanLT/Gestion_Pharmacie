import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean loggedAdmin = false;
        boolean loggedEmploye = false;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter your identifiant :");
        String identifiant = scanner.nextLine();
        System.out.println("Please enter your password :");
        String password = scanner.nextLine();


        User admin = new Admin("admin", "1234", "admin");
        User employe = new Employe("employee", "0000", "employe");

        if (admin.authenticate(identifiant, password)) {
            System.out.println("Bienvenue, Admin !");
            loggedAdmin = true;
        } else if (employe.authenticate(identifiant, password)) {
            System.out.println("Bienvenue, Employé !");
            loggedEmploye = true;
        } else {
            System.out.println("Identifiant ou mot de passe incorrect.");
        }

    }


}