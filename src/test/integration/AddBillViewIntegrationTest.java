package test.integration;

import javafx.stage.Stage;
import model.*;
import model.Utility.FileReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import view.*;

import static org.junit.jupiter.api.Assertions.*;

public class AddBillViewIntegrationTest extends ApplicationTest {

    private Stage primaryStage;
    private List<Book> books;
    private Employee employee;
    private Bill bill;

    @BeforeEach
    public void setUp() throws Exception {
        // Set up necessary data for the test
        books = FileReaderUtil.readArrayListFromFile("data/binaryFiles/books.bin");
        employee = new Librarian("John", "Doe", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0, "1234"); // Sample employee
        bill = new Bill(new BillUnit[0]); // Initialize an empty bill with an empty array of BillUnits
    }

    @Override
    public void start(Stage stage) {
        // Launch the application UI
        primaryStage = stage;
        AddBillView.createBillTable(primaryStage, books, employee);
    }

    @Test
    public void testAddBookToBill() {
        // Simulate selecting a book from the table and adding it to the bill
        Book selectedBook = books.get(0); // Select the first book in the list
        int quantity = 2; // Adding 2 books

        // Trigger the add to bill functionality
        AddBillView.addToBill(selectedBook, quantity, employee);

        // Check if the bill has been updated correctly
        assertNotNull(AddBillView.bill);
        assertEquals(1, AddBillView.bill.getBillUnits().length); // Only one book should be added
        BillUnit addedUnit = AddBillView.bill.getBillUnits()[0];
        assertEquals(selectedBook, addedUnit.getBook());
        assertEquals(quantity, addedUnit.getAmount());
    }

    @Test
    public void testFinishBill() {
        // Simulate finishing the bill
        AddBillView.addToBill(books.get(0), 2, employee); // Add a book to the bill
        AddBillView.finishBill(); // Finish the bill

        // Check if the bill file has been saved correctly
        String billFilePath = "data/bills/" + AddBillView.bill.getBillNo() + ".txt";
        Path path = Paths.get(billFilePath);
        assertTrue(Files.exists(path)); // Ensure the bill file was created

        // Optionally, verify the content of the bill file (e.g., check total cost, book details)
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String content = reader.readLine(); // Read first line of the bill file
            assertNotNull(content);
            assertTrue(content.contains("Bill No: " + AddBillView.bill.getBillNo())); // Verify Bill Number in the content
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to read the bill file");
        }
    }

    @Test
    public void testInvalidQuantity() {
        // Simulate selecting a book and providing an invalid quantity
        Book selectedBook = books.get(0);
        int invalidQuantity = -5; // Invalid quantity

        // Try adding the book with invalid quantity
        AddBillView.addToBill(selectedBook, invalidQuantity, employee);

        // Instead of alert, we can check a flag or state indicating the error
        assertTrue(AddBillView.hasErrorOccurred()); // Verify that an error has occurred
    }

    @Test
    public void testNoBookSelected() {
        // Simulate attempting to add a book without selecting one
        AddBillView.addToBill(null, 2, employee);

        // Instead of alert, we can check a flag or state indicating the error
        assertTrue(AddBillView.hasErrorOccurred()); // Verify that an error has occurred
    }
}
