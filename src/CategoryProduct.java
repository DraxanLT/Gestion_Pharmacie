import java.util.ArrayList;
import java.util.List;

public class CategoryProduct implements Serializable {

    private String category;
    private String subCategory;
    private List<Product> products;


    public CategoryProduct(String category , String subCategory) {
        this.category = category;
        this.subCategory = subCategory;
        this.products = new ArrayList<>();
    }

    /**
     * This function give the category's name
     *
     * @return the category's name
     */
    public String getCategory() {

        return category;
    }

    /**
     * This function give the list of product with this category
     *
     * @return the list of product with this category
     */
    public List<Product> getProducts() {

        return products;
    }

    /**
     * This function put a new list of product with this category
     *
     * @param products : the new list of product with this category
     */
    public void setProducts(List<Product> products) {

        this.products = products;
    }

    /**
     * This function add a product in the list of all the product
     *
     * @param product : the new product to add
     */
    public void addProduct(Product product) {

        this.products.add(product);
    }
}
