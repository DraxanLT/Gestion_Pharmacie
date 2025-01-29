import java.util.ArrayList;
import java.util.List;

public class Sorting {

    public static List<Product> SortQuantity(List<Product> products, int j) {
        if(products.size() > j) {
            if(j > 0) {
                int i = j-1;
                Product temp = products.get(i);
                if(temp.getStockQuantity() > products.get(j).getStockQuantity()) {
                    products.set(i, products.get(j));
                    products.set(j, temp);
                    j--;
                    return SortQuantity(products, j);
                }
                else{
                    j++;
                    return SortQuantity(products, j);
                }
            }
            j++;
            return SortQuantity(products, j);
        }
        else{
            return products;
        }
    }

}
