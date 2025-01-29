import java.util.List;

public class Pharmacie {

    private String nom;
    private String adresse;
    private List<CategorieProduit> produits;

    public Pharmacie(String nom, String adresse, List<CategorieProduit> produits) {
        this.nom = nom;
        this.adresse = adresse;
        this.produits = produits;
    }

    public List<CategorieProduit> getProduits() {
        return produits;
    }

    public void setProduits(List<CategorieProduit> produits) {
        this.produits = produits;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
