package model.Utility;

import java.io.*;
import java.util.ArrayList;

public class FileReaderUtil {
    //generic method to read an arraylist from a binary file
    public static <T> ArrayList<T> readArrayListFromFile(String filePath) {
        ArrayList<T> readList = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Object obj = ois.readObject();

            if (obj instanceof ArrayList<?>) {
                readList = (ArrayList<T>) obj;
            } else {
                System.err.println("Error: Unexpected object type found in the file.");
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading the ArrayList from the file: " + e.getMessage());
            e.printStackTrace();
        }
        return readList;
    }
}
