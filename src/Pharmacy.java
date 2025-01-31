import java.util.ArrayList;
import java.util.List;

public class Pharmacy{

    private String name;
    private String adress;
    private List<CategoryProduct> products = new ArrayList<>();
    private static List<Product> ListAllProduct = new ArrayList<>();


    public Pharmacy(String name, String adress) {
        this.name = name;
        this.adress = adress;
    }

    public String getName() {
        /**
         * This function give the name of the Pharmacy
         *
         * @return the name of the Pharmacy
         */
        return name;
    }

    public static List<Product> getListProducts() {
        /**
         * This function give the list of all the product of the Pharmacy
         *
         * @return the list of all the product of the Pharmacy
         */
        return ListAllProduct;
    }

    public List<CategoryProduct> getProducts() {
        /**
         * This function give the list of all the category of the product in the Pharmacy
         *
         * @return the list of all the category of the product in  of the Pharmacy
         */
        return products;
    }

    public void addProduct(Product product) {
        /**
         * This function add a product in the list of all the product
         *
         * @param product : the new product to add
         */
        this.ListAllProduct.add(product);
    }


    public List<Product> LowStock() {
        /**
         * This function create a list of products that need to be restocked
         *
         * @return a list of all the products that have low stock
         */
        List<Product> lowStock = new ArrayList<>();
        int stock;

        //Browse the product list
        for (int i = 0; i < ListAllProduct.size(); i++) {
            Product product = ListAllProduct.get(i);
            stock = product.getStockQuantity();

            //If stock is below 5
            if (stock <= 5) {
                lowStock.add(product); //Add it to the list
            }
        }
        return lowStock;
    }

    public void showlowstock() {
        /**
         * This function show the products that need to be restocked
         *
         */
        List<Product> lowStock = LowStock();
        List<Product> lowStocktrie = Sorting.SortQuantity(lowStock,1);

        //Browse the product list
        for(int i = 0; i<lowStocktrie.size();i++) {
            Product product = lowStocktrie.get(i);
            //If stock is 0
            if(product.getStockQuantity() == 0) {
                System.out.println(product.getName() + " is out of stock");
            }
            else {
                System.out.println(product.getName() + " has a stock of " + product.getStockQuantity() + " and must be replenished");
            }
        }
    }
}
