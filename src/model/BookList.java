package model;

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
        books.add(new Book("978-0-73829-2849720-5-9", "The Secret History", new Category("Mystery"), "Penguin Books", Year.of(2018), 12.5, 18.0, 16.5, new Author("Donna", "", "Tartt"),27));
        books.add(new Book("978-0-73829-2347321-6-1", "The Shadow of the Wind", new Category("Mystery"), "Dituria", Year.of(2000), 9.0, 12.0, 12.0, new Author("Carlos", "Ruiz. ", "Zafon"), 20));
        books.add(new Book("978-0-46721-5689001-2-9", "Ballad of the Songbirds and Snakes",  new Category("Fantasy"), "Macmilan", Year.of(2015), 15.0, 25.0, 18.0, new Author("Susan", "", "Collins"),22));
       // books.add(new Book("978-0-73829-2849720-5-9", "ygt", new Category("thriller"), ""))
    }

    public List<Book> getBooks() {
        return books;
    }
}
