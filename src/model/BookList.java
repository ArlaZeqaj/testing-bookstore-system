package model;

import model.*;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class BookList {
    private final List<Book> books;

    public BookList() {
        books = new ArrayList<>();
        initializeBooks();
    }

    private void initializeBooks() {
        books.add(new Book("978-0-12345-0123456-7-0", "The Secret History", new Author("Donna", "", "Tartt"), new Category("Mystery"), "Penguin Books", Year.of(2018), 12.5, 18.0, 16.5, 27));
        books.add(new Book("978-0-54321-5432109-8-0", "Book", new Author("John", "K. ", "Doe"), new Category("Adventure"), "Publisher", Year.of(2000), 15.0, 20.0, 18.0, 20));
        books.add(new Book("978-0-73829-2849720-5-0", "The", new Author("John", "K. ", "Doe"), new Category("Adventure"), "Publisher", Year.of(2000), 15.0, 20.0, 18.0, 20));
       // books.add(new Book("978-0-73829-2849720-5-9", "ygt", new Category("thriller"), ""))
    }

    public List<Book> getBooks() {
        return books;
    }
}
