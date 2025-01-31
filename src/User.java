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
        /**
         * This function give the user's role
         *
         * @return the user's role
         */
        return this.role;
    }

    public String getIdentifiant() {
        /**
         * This function give the user's identifier
         *
         * @return the user's identifier
         */
        return this.identifiant;
    }

    public boolean authenticate(String identifiant, String password) {
        return this.identifiant.equals(identifiant) && this.password.equals(password);
    }

    public boolean isAdmin() {
        return this instanceof Admin;
    }

    public boolean isClient() { return this instanceof Client;}

    @Override
    public String toString() {
        return "Id : " + this.identifiant + ", Rôle: " + this.role;
    }


    // methode pour ajouter un role
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
