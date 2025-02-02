package test;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import model.Librarian;
import model.Manager;
import model.Roles;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import view.ManageRolesView;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(org.testfx.framework.junit5.ApplicationExtension.class)
public class ManageRolesSystemTest extends ApplicationTest {

    private ComboBox<String> userComboBox;
    private ComboBox<String> rolesComboBox;
    private Button addButton;
    private Button removeButton;
    private List<Librarian> librarians;
    private List<Manager> managers;

    @Override
    public void start(Stage stage) {
        librarians = new ArrayList<>();
        managers = new ArrayList<>();
        ManageRolesView.showManageRoles(stage, librarians, managers);
        stage.show();
    }

    @BeforeEach
    public void setUp() {
        userComboBox = lookup(".combo-box").nth(0).query();
        rolesComboBox = lookup(".combo-box").nth(1).query();
        addButton = lookup("Add Role").queryButton();
        removeButton = lookup("Remove Role").queryButton();
    }

    @Test
    public void testAddRole(FxRobot robot) {
        //selecting manager user type
        robot.clickOn(userComboBox);
        robot.clickOn("Manager");

        //selecting role "Create Bill"
        robot.clickOn(rolesComboBox);
        robot.clickOn("Create Bill");

        //adding role
        robot.clickOn(addButton);

        //verifying role was added
        assertTrue(Roles.getLibrarianRoles().contains("Create Bill"), "Role should be added");
    }

    @Test
    public void testRemoveRole(FxRobot robot) {
        //select librarian user type
        robot.clickOn(userComboBox);
        robot.clickOn("Librarian");

        //selecting role "Create Bill"
        robot.clickOn(rolesComboBox);
        robot.clickOn("Create Bill");

        //removing role
        robot.clickOn(removeButton);

        //verifying role was removed
        assertTrue(!Roles.getLibrarianRoles().contains("Create Bill"), "Role should be removed");

    }
}
