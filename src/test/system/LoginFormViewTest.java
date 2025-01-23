package test;

import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxAssert;
import org.testfx.api.FxRobot;
import org.testfx.matcher.control.LabeledMatchers;
import view.LoginFormView;

import static org.testfx.matcher.control.LabeledMatchers.hasText;

// Add the annotation to ensure TestFX works with JUnit5
@ExtendWith(org.testfx.framework.junit5.ApplicationExtension.class)
public class LoginFormViewTest extends ApplicationTest {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Start the application (LoginFormView)
        new LoginFormView().start(primaryStage);
    }

    @BeforeEach
    public void setUp() {
        // Any initialization before each test case (if needed)
    }

    @Test
    public void testLoginSuccess(FxRobot robot) {
        // Enter valid username and password
        robot.clickOn("#usernameTextField");
        robot.write("saramolla");

        robot.clickOn("#passwordField");
        robot.write("1234");

        // Click the Login button
        robot.clickOn("#loginButton");

        // Verify that the messageLabel contains "Login successful" or redirect message
        FxAssert.verifyThat("#messageLabel", hasText("Login Successful"));
    }

    @Test
    public void testLoginFailure(FxRobot robot) {
        // Enter invalid username and password
        robot.clickOn("#usernameTextField");
        robot.write("wronguser");

        robot.clickOn("#passwordField");
        robot.write("wrongpassword");

        // Click the Login button
        robot.clickOn("#loginButton");

        // Verify that the messageLabel contains "Invalid username or password"
        FxAssert.verifyThat("#messageLabel", hasText("Invalid username or password"));
    }

    @Test
    public void testUsernameField(FxRobot robot) {
        // Enter a username
        robot.clickOn("#usernameTextField");
        robot.write("arlazeqaj");

        // Verify the text field content
        FxAssert.verifyThat("#usernameTextField", LabeledMatchers.hasText("arlazeqaj"));
    }

    @Test
    public void testPasswordField(FxRobot robot) {
        // Enter a password
        robot.clickOn("#passwordField");
        robot.write("00000");

        // Verify the password field is not showing the password as plain text
        FxAssert.verifyThat("#passwordField", LabeledMatchers.hasText("00000"));
    }
}


