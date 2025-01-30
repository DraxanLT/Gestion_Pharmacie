import java.util.List;
import java.util.ArrayList;

public class Pharmacy {

    private String name;
    private String adress;
    private static List<Product> products = new ArrayList<>();
    private List<CategoryProduct> products0 = new ArrayList<>();

    public Pharmacy(String name, String adress) {
        this.name = name;
        this.adress = adress;
        this.products = products;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }


    public List<Product> LowStock() {
        /**
         * This function show the products that need to be restocked
         *
         */
        List<Product> lowStock = new ArrayList<>();
        int stock;
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            stock = product.getStockQuantity();
            if (stock <= 5 && stock >= 0) {
                lowStock.add(product);
            }
        }
        return lowStock;
    }

    public void showlowstock() {
        List<Product> lowStock = LowStock();
        List<Product> lowStocktrie = Sorting.SortQuantity(lowStock,1);
        for(int i = 0; i<lowStocktrie.size();i++) {
            Product product = lowStocktrie.get(i);
            if(product.getStockQuantity() == 0) {
                System.out.println(product.getName() + " est en rupture de stock");
            }
            else {
                System.out.println(product.getName() + " a un stock de " + product.getStockQuantity() + " et doit être réapprovisionné");
            }
        }
    }

    public void addProduct(CategoryProduct product) {
        this.products0.add(product);
    }


}
