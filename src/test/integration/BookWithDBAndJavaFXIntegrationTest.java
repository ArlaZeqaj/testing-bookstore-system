package test;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Author;
import model.Book;
import model.Category;
import model.Utility.FileReaderUtil;
import model.Utility.FileWriterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.time.Year;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BookWithDBAndJavaFXIntegrationTest extends ApplicationTest {

    private static final String FILE_NAME = "data/binaryFiles/books.bin";
    private TableView<Book> tableView;
    private ObservableList<Book> bookData;

    @Override
    public void start(Stage stage) {
        tableView = new TableView<>();
        stage.show();
    }

    @BeforeEach
    void setup() {
        //delete previous test data
        File file = new File(FILE_NAME);
        if (file.exists()) file.delete();
    }

    @Test
    void testDatabaseAndJavaFXIntegration() {
        //mock book object
        Book book = new Book("978-0-46721-5689001-2-9", "A little life", new Category("Fiction"), "Macmilan",
                Year.of(2014), 15.0, 21.0, 18.0, new Author("Hanya", "", "Yanaghiara"), 22);

        //adding book to an ArrayList
        ArrayList<Book> books = new ArrayList<>();
        books.add(book);

        //storing book list in the binary file
        FileWriterUtil.writeArrayListToFile(books, FILE_NAME);

        //reading book list from binary file
        ArrayList<Book> loadedBooks = FileReaderUtil.readArrayListFromFile(FILE_NAME);

        //validating that book is stored and retrieved correctly
        assertEquals(1, loadedBooks.size(), "Book count should be 1");
        assertEquals("A little life", loadedBooks.get(0).getTitle(), "Title should match");
        assertEquals("Hanya Yanaghiara", loadedBooks.get(0).getAuthor(), "Author should match");
        assertEquals(22, loadedBooks.get(0).getStockNo(), "Stock should match");

        //loading retrieved books into TableView
        Platform.runLater(() -> {
            bookData = FXCollections.observableArrayList(loadedBooks);
            tableView.setItems(bookData);

            //select first book in TableView
            tableView.getSelectionModel().select(0);
            Book selectedBook = tableView.getSelectionModel().getSelectedItem();

            //validate TableView selection
            assertNotNull(selectedBook);
            assertEquals("A little life", selectedBook.getTitle(), "TableView title should match");
            assertEquals("Hanya Yanaghiara", selectedBook.getAuthor(), "TableView author should match");
            assertEquals(22, selectedBook.getStockNo(), "TableView stock should match");
        });
    }
}
