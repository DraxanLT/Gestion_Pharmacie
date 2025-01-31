/**
 * La classe Employe représente un utilisateur ayant un rôle d'employé.
 * Elle hérite de la classe User et utilise son constructeur pour initialiser
 * les attributs communs aux utilisateurs.
 */
public class Employe extends User {

    /**
     * Constructeur de la classe Employe.
     *
     * @param role        Le rôle de l'utilisateur (ex: "employe").
     * @param password    Le mot de passe de l'employé.
     * @param identifiant L'identifiant unique de l'employé.
     */
    public Employe(String role, String password, String identifiant) {
        super(role, password, identifiant);
    }
}
