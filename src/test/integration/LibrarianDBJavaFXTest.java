package test.integration;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Librarian;
import model.Utility.FileReaderUtil;
import model.Utility.FileWriterUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianDBJavaFXTest extends ApplicationTest {

    private static final String FILE_NAME = "data/binaryFiles/librarians.bin";
    private TableView<Librarian> tableView;
    private ObservableList<Librarian> librarianData;

    @Override
    public void start(Stage stage) {
        tableView = new TableView<>();
        stage.show();
    }

    @BeforeEach
    void setup() {
        File file = new File(FILE_NAME);
        if (file.exists()) file.delete();
    }

    @Test
    void testDatabaseAndJavaFXIntegration() {
        Librarian librarian = new Librarian("John", "Doe", LocalDate.of(1985, 3, 15), "+355682345678", 50000.0, "secure123");
        ArrayList<Librarian> librarians = new ArrayList<>();
        librarians.add(librarian);
        FileWriterUtil.writeArrayListToFile(librarians, FILE_NAME);
        ArrayList<Librarian> loadedLibrarians = FileReaderUtil.readArrayListFromFile(FILE_NAME);

        assertEquals(1, loadedLibrarians.size());
        assertEquals("John", loadedLibrarians.get(0).getName());

        Platform.runLater(() -> {
            librarianData = FXCollections.observableArrayList(loadedLibrarians);
            tableView.setItems(librarianData);
            tableView.getSelectionModel().select(0);
            Librarian selectedLibrarian = tableView.getSelectionModel().getSelectedItem();
            assertNotNull(selectedLibrarian);
            assertEquals("John", selectedLibrarian.getName());
        });
    }

    @Test
    void testEmptyFileHandling() {
        FileWriterUtil.writeArrayListToFile(new ArrayList<>(), FILE_NAME);
        ArrayList<Librarian> loadedLibrarians = FileReaderUtil.readArrayListFromFile(FILE_NAME);
        assertTrue(loadedLibrarians.isEmpty(), "List should be empty when loading from an empty file");
    }

    @Test
    void testMultipleLibrariansHandling() {
        ArrayList<Librarian> librarians = new ArrayList<>();
        librarians.add(new Librarian("Alice", "Smith", LocalDate.of(1990, 5, 10), "+355682555678", 40000.0, "pass1"));
        librarians.add(new Librarian("Bob", "Brown", LocalDate.of(1988, 8, 20), "+355682345555", 45000.0, "pass2"));
        FileWriterUtil.writeArrayListToFile(librarians, FILE_NAME);
        ArrayList<Librarian> loadedLibrarians = FileReaderUtil.readArrayListFromFile(FILE_NAME);
        assertEquals(2, loadedLibrarians.size(), "There should be two librarians in the file");
    }

    @Test
    void testIncorrectDataHandling() {
        File file = new File(FILE_NAME);
        try {
            file.createNewFile();
        } catch (Exception e) {
            fail("Failed to create an empty file for testing");
        }
        ArrayList<Librarian> loadedLibrarians = FileReaderUtil.readArrayListFromFile(FILE_NAME);
        assertTrue(loadedLibrarians.isEmpty(), "Should return an empty list for an invalid file");
    }

    @Test
    void testTableViewUpdatesWithNewData() {
        ArrayList<Librarian> librarians = new ArrayList<>();
        librarians.add(new Librarian("Emma", "Johnson", LocalDate.of(1992, 11, 2), "+355682345999", 52000.0, "mypassword"));
        FileWriterUtil.writeArrayListToFile(librarians, FILE_NAME);
        ArrayList<Librarian> loadedLibrarians = FileReaderUtil.readArrayListFromFile(FILE_NAME);

        Platform.runLater(() -> {
            librarianData = FXCollections.observableArrayList(loadedLibrarians);
            tableView.setItems(librarianData);
            assertEquals(1, tableView.getItems().size(), "TableView should have one item");
            assertEquals("Emma", tableView.getItems().get(0).getName(), "Name should match Emma");
        });
    }
}
