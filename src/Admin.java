/**
 * La classe Admin représente un utilisateur ayant un rôle d'administrateur.
 * Elle hérite de la classe User et utilise son constructeur pour initialiser
 * les attributs communs aux utilisateurs.
 */
public class Admin extends User {

    /**
     * Constructeur de la classe Admin.
     *
     * @param role        Le rôle de l'utilisateur (ex: "admin").
     * @param password    Le mot de passe de l'administrateur.
     * @param identifiant L'identifiant unique de l'administrateur.
     */
    public Admin(String role, String password, String identifiant) {
        super(role, password, identifiant);
    }
}
