package test;

import javafx.scene.control.Spinner;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.*;
import model.Utility.FileReaderUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import view.AddBillView;

import java.io.File;
import java.time.LocalDate;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.testfx.framework.junit5.ApplicationExtension.class)
public class CreateBillSystemTest extends ApplicationTest {

    private TableView<Book> tableView;
    private Spinner<Integer> quantitySpinner;
    private Employee testEmployee;
    private List<Book> testBooks;
    private Book testBook;

    @Override
    public void start(Stage stage) {
        testEmployee = new Librarian("Riku", "Dedaj", LocalDate.of(1999, 1, 17), "+355691045581", 62500.0, "1111");
        testBooks = new ArrayList<>();
        testBook = new Book("978-0-46721-5689001-2-9", "Ballad of the Songbirds and Snakes", new Category("Fantasy"), "Macmilan", Year.of(2015), 15.0, 25.0, 18.0, new Author("Susan", "", "Collins"), 22);
        testBooks.add(testBook);
        AddBillView.createBillTable(stage, testBooks, testEmployee);
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        tableView = lookup("#tableView").query();
        quantitySpinner = lookup(".spinner").query();
    }

    @Test
    public void testAddBookToBill(FxRobot robot) {
        //selecting the first book of the tableview
        robot.interact(() -> tableView.getSelectionModel().selectFirst());

        //setting quantity to add to bill to 2
        robot.interact(() -> quantitySpinner.getValueFactory().setValue(2));

        //clicking Add to Bill
        robot.clickOn("#add-to-bill");
        robot.sleep(500);

        //printing the bill as .txt file
        robot.clickOn("Print");
        robot.sleep(500);

        //finishing the bill
        robot.clickOn("#finish-bill");
        robot.sleep(500);

        //checking stock quantity updated correctly
        List<Book> updatedBooks = FileReaderUtil.readArrayListFromFile("data/binaryFiles/books.bin");
        Book updatedBook = updatedBooks.stream()
                .filter(b -> b.getISBN().equals(testBook.getISBN()))
                .findFirst()
                .orElse(null);

        assertNotNull(updatedBook, "The book should still exist in the inventory.");
        assertEquals(20, updatedBook.getStockNo(), "Stock should be reduced correctly.");

        //verifying the bill file exists
        BillUnit billUnit = new BillUnit(testBook, 2);
        Bill bill = new Bill(new BillUnit[]{billUnit});
        String expectedBillFileName = "data/bills/" + (bill.getBillNo()) + ".txt";
        File billFile = new File(expectedBillFileName);
        assertTrue(billFile.exists(), "Bill file should be created.");
    }
}

