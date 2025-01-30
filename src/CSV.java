import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class CSV {
    private String filename;
    private List<List<String>> statsDoc = new ArrayList<>();
    private int j = 1;

    public CSV(String filename) {
        this.filename = filename;
    }

    public List<List<String>> getStatsDoc() {
        return statsDoc;
    }

    private void setStatsDoc(List<List<String>> statsDoc) {
        this.statsDoc = statsDoc;
    }

    public int getJ() {
        return j;
    }

    private void setJ(int j) {
        this.j = j;
    }

    public void readCSVFile() {
        statsDoc.clear();
        File file = new File(filename);

        if (!file.exists()) {
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
        } else {
            try (Scanner scanner = new Scanner(new File(filename))) {
                while (scanner.hasNextLine()) {
                    statsDoc.add(getRecordFromLine(scanner.nextLine()));
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(",");
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
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
                row.remove(1);
                row.remove(2);
                row.add(String.valueOf(order));
                row.add(String.valueOf(newQuantity));
            }
        }
        writeCSVFile();
    }

    public void afficherStats() {
        for (List<String> row : statsDoc) {
            for (String s : row) {
                System.out.print(s);
                System.out.print(", ");
            }
            System.out.print('\n');
        }
    }

    public void mostAsk(List<List<String>> liste, int j, CSV csv) {
        if(liste.size() > j) {
            if(j > 1) {
                int i = j-1;
                List<String> temp = liste.get(i);
                if(Integer.parseInt(temp.get(1)) < Integer.parseInt(liste.get(j).get(1))) {
                    liste.set(i, liste.get(j));
                    liste.set(j, temp);
                    j--;
                    mostAsk(liste, j, csv);
                }
                else if (temp.get(1).equals(liste.get(j).get(1))){
                    if(Integer.parseInt(temp.get(2)) < Integer.parseInt(liste.get(j).get(2))) {
                        liste.set(i, liste.get(j));
                        liste.set(j, temp);
                        j--;
                        mostAsk(liste, j, csv);
                    }
                    else{
                        j++;
                        mostAsk(liste, j, csv);
                    }
                }
                else {
                    j++;
                    mostAsk(liste, j, csv);
                }
            }
            else {
                j++;
                mostAsk(liste, j, csv);
            }

        }
        else{
            if(csv.getStatsDoc().size() == liste.size()) {
                csv.setStatsDoc(liste);
                csv.writeCSVFile();
            }
        }

    }

    public void LessAsk(List<List<String>> liste, int j, CSV csv) {
        if(liste.size() > j) {
            if(j > 1) {
                int i = j-1;
                List<String> temp = liste.get(i);
                if(Integer.parseInt(temp.get(1)) > Integer.parseInt(liste.get(j).get(1))) {
                    liste.set(i, liste.get(j));
                    liste.set(j, temp);
                    j--;
                    LessAsk(liste, j, csv);
                }
                else if (temp.get(1).equals(liste.get(j).get(1))){
                    if(Integer.parseInt(temp.get(2)) > Integer.parseInt(liste.get(j).get(2))) {
                        liste.set(i, liste.get(j));
                        liste.set(j, temp);
                        j--;
                        LessAsk(liste, j, csv);
                    }
                    else{
                        j++;
                        LessAsk(liste, j, csv);
                    }
                }
                else {
                    j++;
                    LessAsk(liste, j, csv);
                }
            }
            else {
                j++;
                LessAsk(liste, j, csv);
            }

        }
        else{
            if(csv.getStatsDoc().size() == liste.size()) {
                csv.setStatsDoc(liste);
                csv.writeCSVFile();
            }
        }

    }

    public void mostBuy(List<List<String>> liste, int j, CSV csv) {
        if(liste.size() > j) {
            if(j > 1) {
                int i = j-1;
                List<String> temp = liste.get(i);
                if(Integer.parseInt(temp.get(2)) < Integer.parseInt(liste.get(j).get(2))) {
                    liste.set(i, liste.get(j));
                    liste.set(j, temp);
                    j--;
                    mostBuy(liste, j, csv);
                }
                else if (temp.get(2).equals(liste.get(j).get(2))){
                    if(Integer.parseInt(temp.get(1)) < Integer.parseInt(liste.get(j).get(1))) {
                        liste.set(i, liste.get(j));
                        liste.set(j, temp);
                        j--;
                        mostBuy(liste, j, csv);
                    }
                    else{
                        j++;
                        mostBuy(liste, j, csv);
                    }
                }
                else {
                    j++;
                    mostBuy(liste, j, csv);
                }
            }
            else {
                j++;
                mostBuy(liste, j, csv);
            }

        }
        else{
            if(csv.getStatsDoc().size() == liste.size()) {
                csv.setStatsDoc(liste);
                csv.writeCSVFile();
            }
        }

    }

    public void LessBuy(List<List<String>> liste, int j, CSV csv) {
        if(liste.size() > j) {
            if(j > 1) {
                int i = j-1;
                List<String> temp = liste.get(i);
                if(Integer.parseInt(temp.get(2)) > Integer.parseInt(liste.get(j).get(2))) {
                    liste.set(i, liste.get(j));
                    liste.set(j, temp);
                    j--;
                    LessBuy(liste, j, csv);
                }
                else if (temp.get(2).equals(liste.get(j).get(2))){
                    if(Integer.parseInt(temp.get(1)) > Integer.parseInt(liste.get(j).get(1))) {
                        liste.set(i, liste.get(j));
                        liste.set(j, temp);
                        j--;
                        LessBuy(liste, j, csv);
                    }
                    else{
                        j++;
                        LessBuy(liste, j, csv);
                    }
                }
                else {
                    j++;
                    LessBuy(liste, j, csv);
                }
            }
            else {
                j++;
                LessBuy(liste, j, csv);
            }

        }
        else{
            if(csv.getStatsDoc().size() == liste.size()) {
                csv.setStatsDoc(liste);
                csv.writeCSVFile();
            }
        }

    }
}
