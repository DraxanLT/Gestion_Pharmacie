import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.*;

public class ProductManager implements Storable {
    private Root root;

    public ProductManager(Root root) {
        this.root = root;
    }

    @Override
    public void addProduct(String name, String category, double price, int quantity, String description) {
        if (price <= 0 || quantity < 0) {
            throw new IllegalArgumentException("The price must be positive and the quantity must be >= 0.");
        }

        CategoryProduct categoryProduct = root.getPharmacy().getProducts().stream().filter(cat ->
                        cat.getCategory().equalsIgnoreCase(category)).findFirst().orElse(null);

        if (categoryProduct == null) {
            categoryProduct = new CategoryProduct(category, "");
            root.getPharmacy().getProducts().add(categoryProduct);
        }

        int newId = root.getPharmacy().getListProducts().size() + 1;

        Product newProduct = new Product(newId, name, price, quantity, description, category);

        categoryProduct.addProduct(newProduct);
        root.getPharmacy().addProduct(newProduct);
    }

    @Override
    public void saveToJson(String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(root, writer);
            System.out.println("Inventory updated and saved in the .json file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNewProduct(String filePath) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the new product's name: ");
        String name = scanner.nextLine();

        System.out.print("Enter the category of the product: ");
        String category = scanner.nextLine();

        double price = -1;
        while (price <= 0) {
            System.out.print("Enter the price : ");
            String priceInput = scanner.nextLine().replace(",", ".");

            try {
                price = Double.parseDouble(priceInput);
                if (price <= 0) {
                    System.out.println("Price must be positive.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid price.");
            }
        }

        int quantity = -1;
        while (quantity < 0) {
            System.out.print("Enter the quantity of the stock: ");
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                if (quantity < 0) {
                    System.out.println("Quantity cannot be negative.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid quantity.");
                scanner.nextLine();
            }
        }

        scanner.nextLine();

        System.out.print("Enter a description for the product: ");
        String description = scanner.nextLine();

        try {
            addProduct(name, category, price, quantity, description);
            saveToJson(filePath);
        } catch (IllegalArgumentException e) {
            System.out.println("Error : " + e.getMessage());
        }
    }

    public static void NewProduct(String filePath) {
        JsonManager jsonManager = new JsonManager();
        Root root = jsonManager.readJson(filePath);

        if (root != null) {
            ProductManager manager = new ProductManager(root);
            manager.addNewProduct(filePath);
        } else {
            System.out.println("Error");
        }
    }
}