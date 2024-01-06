package model;

import model.Utility.FileReaderUtil;
import model.Utility.FileWriterUtil;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AuthorList implements Serializable {
    @Serial
    private static final long serialVersionUID = 538258161104464549L;
    private static List<Author> authors;
    private static ArrayList<Author> readAuthors;
    public static String filePath = "data/binaryFiles/authors.bin";
    public AuthorList(){
        authors = new ArrayList<>();
        //initializeAuthors();
        readAuthors = FileReaderUtil.readArrayListFromFile(filePath);
        //printAuthors(readAuthors);
    }
    private void initializeAuthors() {
        authors.add(new Author("Fyodor", "","Dostoyevski"));
        authors.add(new Author("Jane", "","Austin"));
        FileWriterUtil.writeArrayListToFile(authors,filePath);
        System.out.println("ArrayList of authors has been written to " + filePath);
    }
    public void addAuthor(Author author) {
        readAuthors.add(author);
        FileWriterUtil.writeArrayListToFile(readAuthors, filePath);
    }
    public boolean hasDuplicate(Author newAuthor) {
        for (Author author : authors) {
            if (author.equals(newAuthor)) {
                return true;
            }
        }
        return false;
    }

    public static List<Author> getAuthors() {
        return authors;
    }

    public static void setAuthors(List<Author> authors) {
        AuthorList.authors = authors;
    }

    public static ArrayList<Author> getReadAuthors() {
        return readAuthors;
    }

    public static void setReadAuthors(ArrayList<Author> readAuthors) {
        AuthorList.readAuthors = readAuthors;
    }

    public static void printAuthors(ArrayList<Author> authors){
        for (Author author : authors) {
            System.out.println(author.toString());
            System.out.println();
        }
    }
}
