import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class CSV {
    private List<List<String>> statsDoc = new ArrayList<>();

    public void readCSVFile() {
        try (Scanner scanner = new Scanner(new File("test.csv"));) {
            while (scanner.hasNextLine()) {
                statsDoc.add(getRecordFromLine(scanner.nextLine()));
            }
            if(statsDoc.get(0) == null){
                try (PrintWriter writer = new PrintWriter(new File("test.csv"))) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("Name of product");
                    sb.append(',');
                    sb.append("Number of orders");
                    sb.append(',');
                    sb.append("Quantity");
                    sb.append('\n');
                } catch (FileNotFoundException e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(statsDoc.toString());

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

    public static void main(String args[]) {
        CSV csv = new CSV();
        csv.readCSVFile();
    }
}
