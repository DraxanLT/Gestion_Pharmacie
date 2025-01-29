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
}
