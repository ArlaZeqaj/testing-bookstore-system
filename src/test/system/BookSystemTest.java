package test.system;

import javafx.application.Platform;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Book;
import model.BookList;
import model.Employee;
import model.Librarian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import view.BooksView;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.assertions.api.Assertions.assertThat;

public class BookSystemTest extends ApplicationTest {
    private TableView<Book> tableView;
    private TextField searchField;
    private List<Book> books;

    @Override
    public void start(Stage stage) {
        Platform.runLater(() -> {
            Employee librarian = new Librarian("John", "Doe", LocalDate.of(1990, 5, 15), "+355690001122", 50000.0, "pass123");
            books = new BookList().getReadBooks();
            BooksView.showBooksTable(stage, books, librarian);
            stage.show();
        });
    }

    @BeforeEach
    void setUp() {
        interact(() -> {
            tableView = lookup("#tableView").query();
            searchField = lookup(".text-field").query();
        });
    }

    @Test
    void testBooksDisplayedInTable() {
        assertThat(tableView).isNotNull();
        assertEquals(books.size(), tableView.getItems().size());
    }

    @Test
    void testSearchFunctionality() {
        String searchText = books.get(0).getTitle();
        clickOn(searchField).write(searchText);
        assertThat(tableView.getItems()).isNotEmpty();
        assertEquals(1, tableView.getItems().size());
        assertEquals(searchText, tableView.getItems().get(0).getTitle());
    }

    @Test
    void testEmptySearchDisplaysAllBooks() {
        clickOn(searchField).write("Random Search Text");
        eraseText(searchField.getText().length());  
        assertEquals(books.size(), tableView.getItems().size());
    }

    @Test
    void testSearchIsCaseInsensitive() {
        waitForFxEvents(); // Ensures the UI has fully loaded
        assertThat(tableView).isNotNull();
        assertThat(searchField).isNotNull();

        String searchText = books.get(0).getTitle().toLowerCase();
        clickOn(searchField).write(searchText);

        assertEquals(1, tableView.getItems().size());
        assertEquals(books.get(0).getTitle(), tableView.getItems().get(0).getTitle());
    }
    void waitForFxEvents() {
        interact(() -> {}); // Ensures JavaFX events are processed before proceeding.
    }


    @Test
    void testSearchByISBN() {
        String searchISBN = books.get(0).getISBN();
        clickOn(searchField).write(searchISBN);
        assertEquals(1, tableView.getItems().size());
        assertEquals(searchISBN, tableView.getItems().get(0).getISBN());
    }

    @Test
    void testInvalidSearchShowsNoResults() {
        clickOn(searchField).write("NonexistentBook123");
        assertThat(tableView.getItems()).isEmpty();
    }
}

