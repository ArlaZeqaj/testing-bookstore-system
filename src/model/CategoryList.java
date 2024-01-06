package model;

import model.Utility.FileReaderUtil;
import model.Utility.FileWriterUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CategoryList implements Serializable {
    @Serial
    private static final long serialVersionUID = 2212749183394620980L;
    private static List<Category> categories;
    private static ArrayList<Category> readCategories;
    public static String filePath = "data/binaryFiles/categories.bin";
    public CategoryList(){
        categories = new ArrayList<>();
        readCategories = FileReaderUtil.readArrayListFromFile(filePath);
    }
    public void addCategory(Category category) {
        readCategories.add(category);
        FileWriterUtil.writeArrayListToFile(readCategories, filePath);
    }
    public boolean hasDuplicate(Category newCategory) {
        for (Category category : categories) {
            if (category.equals(newCategory)) {
                return true;
            }
        }
        return false;
    }

    public static List<Category> getCategories() {
        return categories;
    }

    public static void setCategories(List<Category> categories) {
        CategoryList.categories = categories;
    }

    public static ArrayList<Category> getReadCategories() {
        return readCategories;
    }

    public static void setReadCategories(ArrayList<Category> readCategories) {
        CategoryList.readCategories = readCategories;
    }

    public static void printCategories(ArrayList<Category> categories){
        for (Category category : categories) {
            System.out.println(category.toString());
            System.out.println();
        }
    }
}
