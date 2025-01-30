import java.io.*;

public interface Serializable {


    public static void serializeToFile(Object object, File file) {
        try (ObjectOutputStream ps = new ObjectOutputStream(new FileOutputStream(file)))
        {
            ps.writeObject(object);
            System.out.println("L'objet a bien été enregistré " + file.getAbsolutePath());
        }
        catch (IOException e) {
            System.out.println("Erreur lors de la sérialisation : " + e.getMessage());
        }
    }

    public static Object deserializeFromFile(File file) {
        try (ObjectInputStream ps = new ObjectInputStream(new FileInputStream(file))) {
            return ps.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            System.out.println("Erreur lors de la désérialisation : " + e.getMessage());
            return null;
        }
    }
}
