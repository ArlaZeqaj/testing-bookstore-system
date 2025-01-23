package model.Utility;

import java.io.*;
import java.util.List;

public class FileWriterUtil {
    //generic method to write an arraylist to a binary file
    public static <T> void writeArrayListToFile(List<T> list, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(list);
        } catch (IOException e) {
            System.err.println("Error writing the ArrayList to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
