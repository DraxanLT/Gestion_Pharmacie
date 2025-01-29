import java.util.List;

public class Sorting {

    static void sortAlphabetically(List<Product> products) {
        for (int i = 1; i < products.size(); i++) {
            Product key = products.get(i);
            int j = i - 1;

            int getName = products.get(j).getName().compareToIgnoreCase((key.getName()));

            while (j >= 0 && getName > 0) {
                products.set(j + 1, products.get(j));
                j--;
            }
            products.set(j + 1, key);
        }
    }
}
