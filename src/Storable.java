public interface Storable {

    void addProduct(String name, String category, double price, int quantity, String description);

    void saveToJson(String filePath);
}
