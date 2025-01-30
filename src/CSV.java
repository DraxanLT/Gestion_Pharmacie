import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CSV {
    private String filename;
    private List<List<String>> statsDoc = new ArrayList<>();

    public CSV(String filename) {
        this.filename = filename;
    }

    public void readCSVFile() {
        statsDoc.clear();
        File file = new File(filename);

        if (!file.exists()) {
            try {
                writeCSVFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            try {
                    ArrayList<String> temp = new ArrayList<String>();
                    temp.add("Name product");
                    temp.add("Number of orders");
                    temp.add("Number of product buy");
                    statsDoc.add(temp);

                    writeCSVFile();


            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void writeCSVFile() {

        try(PrintWriter writer = new PrintWriter(new FileOutputStream(filename, false))) {
            for (List<String> row : statsDoc) {
                writer.write(String.join(",", row ));
                writer.print('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public void addNewProduct(Product product) {
        int number = 0;
        ArrayList<String> temp = new ArrayList<String>();
        temp.add(product.getName());
        temp.add(String.valueOf(number));
        temp.add(String.valueOf(number));
        statsDoc.add(temp);
        writeCSVFile();
    }

    public void removeProduct(Product searchProduct) {
        String updateProduct = searchProduct.getName();
        String productName;
        for (List<String> row : statsDoc) {
            productName = row.get(0);
            if(productName.equals(updateProduct)) {
                statsDoc.remove(row);
            }
        }
    }


    public void addNewOrder(Product searchProduct, int quantity) {
        String updateProduct = searchProduct.getName();
        String productName;
        int order;
        int newQuantity;
        for (List<String> row : statsDoc) {
            productName = row.get(0);
            if(productName.equals(updateProduct)) {
                order = Integer.parseInt(row.get(1)) + 1;
                newQuantity = Integer.parseInt(row.get(2)) + quantity;
                row.set(0, productName);
                row.set(1, String.valueOf(order));
                row.set(2, String.valueOf(newQuantity));
            }
        }
        writeCSVFile();
    }

    public void afficherStats() {
        for (List<String> row : statsDoc) {
            for (String s : row) {
                System.out.print(s);
            }
            System.out.print('\n');
        }
    }

    public static void main(String args[]) {



    }
}
