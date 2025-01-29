package User;

import java.util.ArrayList;
import java.util.List;

public class UserGestion {
    private List<User> users;

    public UserGestion() {
        this.users = new ArrayList<>();


        users.add(new Admin("admin", "1234", "admin"));
        users.add(new Employe("employee", "0000", "employee"));
    }

    public User authenticate(String identifiant, String password) {
        for (User user : users) {
            if (user.authenticate(identifiant, password)) {
                return user;
            }
        }
        return null;
    }

    public void addUser() {
        // Code pour ajouter un utilisateur
    }

    public void removeUser() {
        // Code pour supprimer un utilisateur
    }

    public void listUsers() {
        // Code pour lister les utilisateurs
    }
}
