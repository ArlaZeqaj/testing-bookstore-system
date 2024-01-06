package model.Utility;

import java.util.ArrayList;

public class FilePrinterUtil {
    public static <T> void printArrayList(ArrayList<T> list) {
        for (T item : list) {
            System.out.println(item);
            System.out.println();
        }
    }
}
