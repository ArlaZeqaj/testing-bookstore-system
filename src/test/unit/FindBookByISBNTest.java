package test;

import model.Author;
import model.Book;
import model.Category;
import model.Utility.ValidationUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FindBookByISBNTest {

    private List<Book> bookList;

    @BeforeEach
    public void setUp() {
        bookList = new ArrayList<>();
        bookList.add(new Book("978-0-73829-2347329-6-1", "Angel's Game", new Category("Mystery"), "Dituria", Year.of(2002), 10.0, 15.0, 15.0, new Author("Carlos", "Ruiz", "Zafon"), 20));
        bookList.add(new Book("978-0-73829-2347335-6-1", "Persuasion", new Category("Classics"), "Penguin", Year.of(2004), 10.0, 15.0, 15.0, new Author("Jane", "", "Austen"), 4));
    }

    @Test
    public void testFindBookByValidISBN() {
        String searchISBN = "978-0-73829-2347329-6-1";

        // Directly validate ISBN using ValidationUtil
        assertTrue(ValidationUtil.isValid(searchISBN, ValidationUtil.ISBN_13_REGEX));

        // Find book by ISBN
        List<Book> result = findBookByISBN(searchISBN, bookList);

        // Assertions
        assertEquals(1, result.size(), "The result should contain one book.");
        assertEquals(searchISBN, result.get(0).getISBN(), "The ISBN of the found book should match.");
    }

    @Test
    public void testFindBookByInvalidISBN() {
        String invalidISBN = "123-4-567890-12-3";

        // ValidationUtil should throw an exception for invalid ISBN
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
                ValidationUtil.isValid(invalidISBN, ValidationUtil.ISBN_13_REGEX)
        );
        assertEquals("The format of this field is incorrect!", exception.getMessage());
    }

    @Test
    public void testFindBookWithNoMatchingISBN() {
        String searchISBN = "978-0-73829-2347340-6-1";

        // Validate the ISBN
        assertTrue(ValidationUtil.isValid(searchISBN, ValidationUtil.ISBN_13_REGEX));

        // Find book by ISBN
        List<Book> result = findBookByISBN(searchISBN, bookList);

        // Assertions
        assertTrue(result.isEmpty(), "The result should be empty if no books match the ISBN.");
    }

    // Method to simulate the actual `findBookByISBN` behavior
    private List<Book> findBookByISBN(String searchISBN, List<Book> books) {
        List<Book> matchingBooks = new ArrayList<>();
        if (ValidationUtil.isValid(searchISBN, ValidationUtil.ISBN_13_REGEX)) {
            for (Book book : books) {
                if (book.getISBN().equals(searchISBN)) {
                    matchingBooks.add(book);
                }
            }
        }
        return matchingBooks;
    }
}
