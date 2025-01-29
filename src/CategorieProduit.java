import java.util.List;

public class CategorieProduit {

    private String categorie;
    private String sousCategorie;
    private List<Produit> produits;

    public CategorieProduit(String categorie, String sousCategorie, List<Produit> produits) {
        this.categorie = categorie;
        this.sousCategorie = sousCategorie;
        this.produits = produits;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getSousCategorie() {
        return sousCategorie;
    }

    public void setSousCategorie(String sousCategorie) {
        this.sousCategorie = sousCategorie;
    }

    public List<Produit> getProduits() {
        return produits;
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
    }
}
