import java.util.List;

public class Sorting {

    /**
     * This function order the product's list by the stock quantity's product
     *
     * @param products : list of product
     * @param j : the index
     *
     * @return a sorted list
     */
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

    static void sortAlphabetically(List<Product> products) {

        for (int i = 1; i < products.size(); i++) {
            Product key = products.get(i);
            int j = i - 1;

            while (j >= 0 && products.get(j).getName().compareToIgnoreCase(key.getName()) > 0) {
                products.set(j + 1, products.get(j));
                j--;
            }
            products.set(j + 1, key);
        }
    }


}
