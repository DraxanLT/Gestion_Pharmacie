import java.util.ArrayList;
import java.util.List;

public class Pharmacy {

    private String name;
    private String adress;
    private static List<CategoryProduct> products = new ArrayList<>();

    public Pharmacy(String name, String adress) {
        this.name = name;
        this.adress = adress;
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

    public List<CategoryProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CategoryProduct> products) {
        this.products = products;
    }

    public void addProduct(CategoryProduct product) {
        this.products.add(product);
    }

    public static List<Product> getAllProducts() {
        List<Product> allProducts = new ArrayList<>();
        if (products != null) {
            for (CategoryProduct category : products) {
                allProducts.addAll(category.getProducts());
            }
        }
        return allProducts;
    }

    public List<Product> LowStock() {
        /**
         * This function show the products that need to be restocked
         *
         */
        List<Product> lowStock = new ArrayList<>();
        int stock;
        List<Product> allProducts = getAllProducts();
        for (int i = 0; i < allProducts.size(); i++) {
            Product product = allProducts.get(i);
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
}
