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
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean categoryExists = false;
            while ((line = reader.readLine()) != null) {
                if (line.equals(name)) {
                    categoryExists = true;
                    break;
                }
            }

            if (!categoryExists) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
                    writer.write(name);
                    writer.newLine();
                    System.out.println("Category saved successfully.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Category already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}