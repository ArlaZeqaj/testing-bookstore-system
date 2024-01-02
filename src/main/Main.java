package main;
import model.Book;
import model.Author;
import model.Category;

import java.time.Year;

public class Main {
    public static void main(String[] args) {
        //testing the creation of Book objects
        Book book1 = new Book("978-0-12345-0123456-7-0", "The Secret History", new Category("Mystery"), "Penguin Books", Year.of(2018), 12.5, 18.0, 16.5, new Author("Donna", "", "Tartt"), 27);
        System.out.println(book1);

    }
}