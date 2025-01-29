import java.util.List;

public abstract class Produit {

    protected int id;
    protected String nom;
    protected double prix;
    protected int quantiteStock;
    protected String description;

    public Produit(int id, String nom, double prix, int quantiteStock, String description) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.quantiteStock = quantiteStock;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getQuantiteStock() {
        return quantiteStock;
    }

    public void setQuantiteStock(int quantiteStock) {
        this.quantiteStock = quantiteStock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void TriProduits(List<String> produits) {
        for (int i = 1; i < produits.size(); i++) {
            String selectionne = produits.get(i);
            int j = i - 1;

            while (j >= 0 && selectionne.compareTo(produits.get(j)) < 0) {
                produits.set(j + 1, produits.get(j));
                j--;
            }
            produits.set(j + 1, selectionne);
        }
    }
}
