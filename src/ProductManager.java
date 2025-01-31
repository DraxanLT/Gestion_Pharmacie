import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import com.google.gson.*;

public class ProductManager implements Storable {
    private Root root;

    public ProductManager(Root root) {
        this.root = root;
    }

    /**
     * This function adds a new product to the product list and links it with an existing category or creates a new one
     *
     * @param name : name of the product
     * @param category category of the product
     * @param price : price of the product
     * @param quantity : quantity of the product
     * @param description : description of the product
     */
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

    /** This function saves product inventory to a JSON file.
     * Sauvegarde l'inventaire des produits dans un fichier JSON.
     *
     * @param filePath : the path of the json file
     */
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

    /**
     * This function adds a new product by asking the user for information, then saves the changes to the json file
     *
     * @param filePath : the path of the json file
     */
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

    /**
     * This function loads data from a JSON file and allows the user to add a product
     *
     * @param filePath : the path of the json file
     */
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

    /**
     * This function removes a product from inventory based on its name or ID
     *
     * @param filePath : the path of the json file
     */
    public void deleteProductNameId(String filePath) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to delete by Name or Id ? ");
        System.out.print(" 1. By Name ");
        System.out.print(" 1. By Id ");
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
            System.out.print("Enter the product Id: ");
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

    /**
     * This function laods datas from the json file and allows the user to delete a product
     *
     * @param filePath : the path of the json file
     */
    public static void deleteProduct(String filePath) {
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