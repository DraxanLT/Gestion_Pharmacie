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

    public void setName(String name) {
        /**
         * This function put a new name for the pharmacy
         *
         * @param name : the new name for the pharmacy
         */
        this.name = name;
    }

    public String getAdress() {
        /**
         * This function give the adress of the Pharmacy
         *
         * @return the adress of the Pharmacy
         */
        return adress;
    }

    public void setAdress(String adress) {
        /**
         * This function put a new adress for the pharmacy
         *
         * @param adress : the new adress for the pharmacy
         */
        this.adress = adress;
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

    public void setProducts(List<CategoryProduct> products) {
        /**
         * This function put a new list of product's category for the pharmacy
         *
         * @param products : the new list of product's category
         */
        this.products = products;
    }

    public void setListProducts(List<Product> products) {
        /**
         * This function put a new list of products for the pharmacy
         *
         * @param products : the new list of products
         */
        this.ListAllProduct = products;
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

    public void addProduct(CategoryProduct product) {
        /**
         * This function add a category of product in the list of all the category product
         *
         * @param product : the new gategory to add
         */
        this.products.add(product);
    }


}
