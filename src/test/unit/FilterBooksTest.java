package test;

import controller.UserDashboardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Author;
import model.Book;
import model.Category;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class FilterBooksTest {

    @Test
    void filterBooksTest_noSearchText() {
        ObservableList<Book> books = FXCollections.observableArrayList(
                new Book("978-0-73829-2849720-5-9", "White Nights", new Category("Classics"), "Penguin", Year.of(2015), 9.0, 16.0, 16.0, new Author("Fyodor", "", "Dostoyevsky"), 10),
                new Book("978-0-73829-2849721-5-9", "The Idiot", new Category("Classics"), "Penguin", Year.of(2014), 8.5, 15.0, 15.0, new Author("Fyodor", "", "Dostoyevsky"), 9)
        );
        ObservableList<Book> result = UserDashboardController.filterBooks(books, null);
        assertEquals(books, result);
    }

    @Test
    void filterBooksTest_searchTextMatch() {

        ObservableList<Book> books = FXCollections.observableArrayList(
                new Book("978-0-73829-2849720-5-9", "White Nights", new Category("Classics"), "Penguin", Year.of(2015), 9.0, 16.0, 16.0, new Author("Fyodor", "", "Dostoyevsky"), 10),
                new Book("978-0-73829-2849721-5-9", "The Idiot", new Category("Classics"), "Penguin", Year.of(2014), 8.5, 15.0, 15.0, new Author("Fyodor", "", "Dostoyevsky"), 9)
        );
        String searchText = "Fyodor";
        ObservableList<Book> result = UserDashboardController.filterBooks(books, searchText);
        assertEquals(2, result.size());
    }

    @Test
    void testFilterBooks_searchTextNoMatch() {
        ObservableList<Book> books = FXCollections.observableArrayList(
                new Book("978-0-73829-2849720-5-9", "White Nights", new Category("Classics"), "Penguin", Year.of(2015), 9.0, 16.0, 16.0, new Author("Fyodor", "", "Dostoyevsky"), 10),
                new Book("978-0-73829-2849721-5-9", "The Idiot", new Category("Classics"), "Penguin", Year.of(2014), 8.5, 15.0, 15.0, new Author("Fyodor", "", "Dostoyevsky"), 9)
        );
        String searchText = "Nonexistent";
        ObservableList<Book> result = UserDashboardController.filterBooks(books, searchText);
        assertTrue(result.isEmpty());
    }

}

