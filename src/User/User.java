package User;

public abstract class User {
    protected String identifiant;
    protected String password;
    protected String role;

    public User(String role, String password, String identifiant) {
        this.role = role;
        this.password = password;
        this.identifiant = identifiant;
    }

    public String getRole() {
        return this.role;
    }

    public String getIdentifiant() {
        return this.identifiant;
    }

    public boolean authenticate(String identifiant, String password) {
        return this.identifiant.equals(identifiant) && this.password.equals(password);
    }

    public boolean isAdmin() {
        return this instanceof Admin;
    }

    @Override
    public String toString() {
        return "Id : " + this.identifiant + ", Rôle: " + this.role;
    }

    public void setRole(String newRole) {
        if (newRole.isEmpty()) {
            System.out.println("Le rôle ne peut pas être vide !");
        } else if (!newRole.matches("[a-zA-Z]+")) {
            System.out.println("Le rôle doit contenir uniquement des lettres !");
        } else {
            this.role = newRole;
            System.out.println("Nouveau rôle attribué : " + newRole);
        }
    }

}
