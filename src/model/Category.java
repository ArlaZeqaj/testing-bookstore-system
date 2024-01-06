package model;

import model.Utility.ValidationUtil;

import java.io.*;

public class Category implements Serializable {
    @Serial
    private static final long serialVersionUID = 7762027686636636721L;
    private String name;
    public static String filename = "data/categories.txt";

    public Category(String name) {
        if(ValidationUtil.isValid(name, ValidationUtil.BOOK_TITLE_REGEX))
            this.name = name;
        saveCategoryToFile();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(ValidationUtil.isValid(name, ValidationUtil.STRING_REGEX))
            this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    private void saveCategoryToFile() {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                writer.write(name);
                writer.newLine();
                System.out.println("Category saved successfully.");
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}