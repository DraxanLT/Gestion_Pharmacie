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

    public void deleteProductNameId(String filePath) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Do you want to delete by (1) Name or (2) ID? ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        Product productToRemove;

        if (choice == 1) {
            System.out.print("Enter the product name: ");
            String productName = scanner.nextLine();
            productToRemove = root.getPharmacy().getListProducts().stream()
                    .filter(p -> p.getName().equalsIgnoreCase(productName))
                    .findFirst()
                    .orElse(null);

        } else if (choice == 2) {
            System.out.print("Enter the product ID: ");
            int productId = scanner.nextInt();
            productToRemove = root.getPharmacy().getListProducts().stream()
                    .filter(p -> p.getId() == productId)
                    .findFirst()
                    .orElse(null);

        } else {
            productToRemove = null;
            System.out.println("Invalid choice.");
            System.out.println("--------------------------------------------------------");
            return;
        }

        if (productToRemove == null) {
            System.out.println("Product not found.");
            System.out.println("--------------------------------------------------------");
            return;
        }

        System.out.println(" Are you sure you want to delete ''" + productToRemove.getName() + "'' : ");
        System.out.println(" 1. Yes");
        System.out.println(" 2. No");
        System.out.println("--------------------------------------------------------");
        String confirm = scanner.next().toLowerCase();
        if (!confirm.equals("1")) {
            System.out.println("Deletion cancelled.");
            return;
        }

        root.getPharmacy().getListProducts().remove(productToRemove);

        for (CategoryProduct category : root.getPharmacy().getProducts()) {
            category.getProducts().removeIf(p -> p.getId() == productToRemove.getId());
        }

        saveToJson(filePath);

        System.out.println("Product '" + productToRemove.getName() + "' has been deleted.");
    }

    public static void DeleteProduct(String filePath) {
        JsonManager jsonManager = new JsonManager();
        Root root = jsonManager.readJson(filePath);

        if (root != null) {
            ProductManager manager = new ProductManager(root);
            manager.deleteProductNameId(filePath);
        } else {
            System.out.println("Error");
        }
    }
}