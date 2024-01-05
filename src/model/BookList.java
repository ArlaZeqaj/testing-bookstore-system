package model;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.io.*;

public class BookList {
    private final List<Book> books;
    public static String filePath = "books.bin";

    public BookList() {
        books = new ArrayList<>();
        //initializeBooks();
        ArrayList<Book> readBooks = readBookListFromFile(filePath);
        printBooksFromFile(readBookListFromFile(filePath));
    }

    private void initializeBooks() {
        books.add(new Book("978-0-73829-2849720-5-9", "The Secret History", new Category("Mystery"), "Penguin Books", Year.of(2018), 12.5, 18.0, 16.5, new Author("Donna", "", "Tartt"),27));
        books.add(new Book("978-0-73829-2347321-6-1", "The Shadow of the Wind", new Category("Mystery"), "Dituria", Year.of(2000), 9.0, 12.0, 12.0, new Author("Carlos", "Ruiz. ", "Zafon"), 20));
        books.add(new Book("978-0-46721-5689001-2-9", "Ballad of the Songbirds and Snakes",  new Category("Fantasy"), "Macmilan", Year.of(2015), 15.0, 25.0, 18.0, new Author("Susan", "", "Collins"),22));
       // books.add(new Book("978-0-73829-2849720-5-9", "ygt", new Category("thriller"), ""))
        writeBookListToFile(books, filePath);
        System.out.println("ArrayList of objects has been written to " + filePath);
    }

    public List<Book> getBooks() {
        return books;
    }

    public static void writeBookListToFile(List<Book> books, String filePath) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.err.println("Error writing the BookList to the file: " + e.getMessage());
        }
    }
    public static ArrayList<Book> readBookListFromFile(String filePath) {
        ArrayList<Book> readBooks = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            readBooks = (ArrayList<Book>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error reading the BookList from the file: " + e.getMessage());
        }
        return readBooks;
    }
    public static void printBooksFromFile(ArrayList<Book> readBooks){
        System.out.println("ArrayList of books from file: " + filePath);
        for (Book book : readBooks) {
            System.out.println("Title: " + book.getTitle());
            System.out.println("Author: " + book.getAuthor().toString());
            System.out.println();
        }
    }
}
