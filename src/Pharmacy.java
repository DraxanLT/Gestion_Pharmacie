import java.util.List;
import java.util.ArrayList;

public class Pharmacy {

    private String name;
    private String adress;
    private List<CategoryProduct> products = new ArrayList<>();
    private static List<Product> ListAllProduct = new ArrayList<>();


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

    public List<Product> getListProducts() {
        return ListAllProduct;
    }

    public List<CategoryProduct> getProducts() {
        return products;
    }

    public void setProducts(List<CategoryProduct> products) {
        this.products = products;
    }

    public void setListProducts(List<Product> products) {
        this.ListAllProduct = products;
    }

    public void addProduct(Product product) {
        this.ListAllProduct.add(product);
    }


    public List<Product> LowStock() {
        /**
         * This function show the products that need to be restocked
         *
         */
        List<Product> lowStock = new ArrayList<>();
        int stock;
        for (int i = 0; i < ListAllProduct.size(); i++) {
            Product product = ListAllProduct.get(i);
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
        this.products.add(product);
    }


}
