import java.io.*;
import java.util.ArrayList;

public class filesHandler {

    // Function to save any object to a file
    public static <T> void saveToFile(String fileName, T object) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(object);
            System.out.println("Object saved successfully.");
        } catch (IOException e) {
            System.err.println("Error saving object: " + e.getMessage());
        }
    }

    // Function to read any object from a file
    public static <T> T readFromFile(String fileName) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading object: " + e.getMessage());
            return null; // Return null in case of error
        }

    }
}
