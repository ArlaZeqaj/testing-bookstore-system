package test;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.Librarian;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import view.ProfileView;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(org.testfx.framework.junit5.ApplicationExtension.class) // Ensure that TestFX is being used.
public class ProfileViewTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) {
        // Create a mock Librarian object
        Librarian librarian = new Librarian("Sara", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0,"1234");
        ProfileView.showProfileView(primaryStage, librarian);
    }

    @Test
    void testLibrarianProfileView(FxRobot robot) {
        // Verify the full name is displayed correctly
        Label nameLabel = robot.lookup("Label:Full name:").queryAs(Label.class);
        assertEquals("Full name:  John Doe", nameLabel.getText());

        // Verify the username is displayed correctly
        Label usernameLabel = robot.lookup("Label:Username:").queryAs(Label.class);
        assertEquals("Username: @johndoe", usernameLabel.getText());

        // Verify the email is displayed correctly
        Label emailLabel = robot.lookup("Label:Email:").queryAs(Label.class);
        assertEquals("Email: john@example.com", emailLabel.getText());

        // Verify the phone number is displayed correctly
        Label phoneLabel = robot.lookup("Label:Phone number:").queryAs(Label.class);
        assertEquals("Phone number: 123456789", phoneLabel.getText());

        // Verify the access level is displayed correctly
        Label accessLabel = robot.lookup("Label:Access level:").queryAs(Label.class);
        assertEquals("Access level: MANAGER", accessLabel.getText());

        // Verify that the librarian's image is displayed
        ImageView imageView = robot.lookup(".profile-container > ImageView").queryAs(ImageView.class);
        assertNotNull(imageView);

        // Verify the presence of the Logout button
        Button logoutButton = robot.lookup("Button:Logout").queryAs(Button.class);
        assertNotNull(logoutButton);

        // Simulate a logout action and verify the screen changes
        robot.clickOn(logoutButton);
        Button loginButton = robot.lookup("loginButton").queryAs(Button.class);
        assertNotNull(loginButton);
    }
}

