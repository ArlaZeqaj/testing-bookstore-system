package test;

import javafx.application.Platform;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import view.UserDashboardView;

import java.io.File;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

public class CheckLowStockBooksTest extends ApplicationTest {

    private Stage testStage;
    private TextArea lowStockTextArea;
    private Employee testEmployee;

    @BeforeEach
    public void setUp() throws Exception {
        Platform.runLater(() -> {
            testStage = new Stage();
            lowStockTextArea = new TextArea();
        });

        // Allow JavaFX Application Thread to process the setup
        waitForFxEvents();

        // Create a test employee
        testEmployee = new Librarian("Toni", "Saliu", LocalDate.of(1999, 1, 17), "+355691045581", 62500.0, "1111");

        // Mock file data for books
        List<Book> testBooks = new ArrayList<>();
        testBooks.add(new Book("978-0-73829-2347329-6-1", "Angel's Game", new Category("Mystery"), "Dituria", Year.of(2002), 10.0, 15.0, 15.0, new Author("Carlos", "Ruiz", "Zafon"), 20));
        testBooks.add(new Book("978-0-73829-2347330-6-1", "Marina", new Category("Mystery"), "Dituria", Year.of(2012), 10.0, 15.0, 15.0, new Author("Carlos", "Ruiz", "Zafon"), 2));
        testBooks.add(new Book("978-0-73829-2347335-6-1", "Persuasion", new Category("Classics"), "Penguin", Year.of(2004), 10.0, 15.0, 15.0, new Author("Jane", "", "Austen"), 4));

        // Write test data to the file
        File tempFile = new File("data/binaryFiles/books.bin");
        tempFile.getParentFile().mkdirs(); // Ensure parent directories exist
        model.Utility.FileWriterUtil.writeArrayListToFile(testBooks, tempFile.getAbsolutePath());
    }

    @Test
    public void testCheckLowStockBooks() {
        Platform.runLater(() -> {
            try {
                UserDashboardView.checkLowStockBooks(testStage, testEmployee, lowStockTextArea);
            } catch (Exception e) {
                fail("An exception occurred: " + e.getMessage());
            }
        });

        // Allow JavaFX Application Thread to process events
        waitForFxEvents();

        // Log the actual output for debugging
        System.out.println("Actual TextArea content: " + lowStockTextArea.getText());

        // Assert
        String expectedOutput = "Book Title: Marina\nAuthor: Carlos Ruiz Zafon\nStock No: 2\n\n" +
                "Book Title: Persuasion\nAuthor: Jane Austen\nStock No: 5\n\n";

        assertEquals(expectedOutput, lowStockTextArea.getText(),
                "Low stock books should be displayed in the TextArea with correct information.");
    }

}
