import java.util.ArrayList;
import java.util.List;

public class CategoryProduct {

    private String category;
    private String subCategory;
    private List<Product> products = new ArrayList<Product>();

    public CategoryProduct(String category, String subCategory) {
        this.category = category;
        this.subCategory = subCategory;

    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
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
}
