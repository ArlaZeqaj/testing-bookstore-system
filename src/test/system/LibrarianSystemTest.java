package test.system;

import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Librarian;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import view.LibrariansView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(org.testfx.framework.junit5.ApplicationExtension.class)
public class LibrarianSystemTest extends ApplicationTest {

    private TableView<Librarian> tableView;
    private TextField nameField;
    private TextField surnameField;
    private TextField phoneNumberField;
    private TextField salaryField;
    private TextField passwordField;
    private Button addEmployeeButton;
    private List<Librarian> librarians;

    @Override
    public void start(Stage stage) {
        librarians = new ArrayList<>();
        LibrariansView.showLibrariansTable(stage, librarians);
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        tableView = lookup("#tableView").query();
        nameField = lookup(".text-field").nth(0).query();
        surnameField = lookup(".text-field").nth(1).query();
        phoneNumberField = lookup(".text-field").nth(2).query();
        salaryField = lookup(".text-field").nth(3).query();
        passwordField = lookup(".text-field").nth(4).query();
        addEmployeeButton = lookup("Add Employee").queryButton();
    }

    @Test
    public void testAddLibrarian(FxRobot robot) {
        robot.clickOn(nameField).write("John");
        robot.clickOn(surnameField).write("Doe");
        robot.clickOn(phoneNumberField).write("+355682345678");
        robot.clickOn(salaryField).write("50000");
        robot.clickOn(passwordField).write("securepass");

        robot.clickOn(addEmployeeButton);

        robot.sleep(500);

        assertFalse(tableView.getItems().isEmpty(), "Table should contain the new librarian");
        assertEquals("John", tableView.getItems().get(0).getName(), "Librarian's name should match");
    }
}

