import java.util.ArrayList;
import java.util.List;

public class CategoryProduct implements Serializable {

    private String category;
    private String subCategory;
    private List<Product> products;


    public CategoryProduct(String category, String subCategory) {
        this.category = category;
        this.subCategory = subCategory;
        this.products = new ArrayList<>();
    }

    public String getCategory() {
        /**
         * This function give the category's name
         *
         * @return the category's name
         */
        return category;
    }

    public void setCategory(String category) {
        /**
         * This function put a new category's name
         *
         * @param category : the new category's name
         */
        this.category = category;
    }

    public String getSubCategory() {
        /**
         * This function give the sub category's name
         *
         * @return the sub category's name
         */
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        /**
         * This function put a new sub category's name
         *
         * @param subCategory : the new sub category's name
         */
        this.subCategory = subCategory;
    }

    public List<Product> getProducts() {
        /**
         * This function give the list of product with this category
         *
         * @return the list of product with this category
         */
        return products;
    }

    public void setProducts(List<Product> products) {
        /**
         * This function put a new list of product with this category
         *
         * @param products : the new list of product with this category
         */
        this.products = products;
    }

    public void addProduct(Product product) {
        /**
         * This function add a product in the list of all the product
         *
         * @param product : the new product to add
         */
        this.products.add(product);
    }
}
