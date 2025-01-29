import java.util.ArrayList;
import java.util.List;

public class Pharmacy {

    private String name;
    private String adress;
    private List<CategoryProduct> products = new ArrayList<>();

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


}
