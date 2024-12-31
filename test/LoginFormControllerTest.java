package test;

import controller.LoginFormController;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Admin;
import model.Librarian;
import model.Manager;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(ApplicationExtension.class)
class LoginFormControllerTest {

    LoginFormController controller;

    @Start
    private void start(Stage stage) {
        controller = new LoginFormController(stage);
    }

    @Test
    void testValidLibrarianLogin() {
        Label label = new Label();

        //test existing librarian credentials
        Platform.runLater(() -> {
            controller.handleLogin("saramolla", "1234", label);
            assertEquals("Login Successful", label.getText());
            assertTrue(controller.getCurrentUser() instanceof Librarian);
        });
        waitForFxEvents();
    }

    //wait for JavaFX events to complete
    private void waitForFxEvents() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testValidManagerLogin() {
        Label label = new Label();

        //test existing manager credentials
        Platform.runLater(() -> {
            controller.handleLogin("erionmolla", "4444", label);
            assertEquals("Login Successful", label.getText());
            assertTrue(controller.getCurrentUser() instanceof Manager);
        });

        waitForFxEvents();
    }
    @Test
    void testValidAdminLogin() {
        Label label = new Label();

        //test admin credentials
        Platform.runLater(() -> {
            controller.handleLogin("arlazeqaj", "00000", label);
            assertEquals("Login Successful", label.getText());
            assertTrue(controller.getCurrentUser() instanceof Admin);
        });
        waitForFxEvents();
    }

    @Test
    void testInvalidLogin() {
        Label label = new Label();
        //test invalid user and invalid password login
        Platform.runLater(() -> {
            controller.handleLogin("invalidUser", "invalidPass", label);
            assertEquals("Invalid username or password", label.getText());
            assertNull(controller.getCurrentUser());
        });
        waitForFxEvents();
    }

    @Test
    void testInvalidPasswordForValidUser() {
        Label label = new Label();
        //test valid user and invalid password login
        Platform.runLater(() -> {
            controller.handleLogin("saramolla", "5555", label);
            assertEquals("Invalid username or password", label.getText());
            assertNull(controller.getCurrentUser());
        });
        waitForFxEvents();
    }

    @Test
    void testEmptyCredentials() {
        Label label = new Label();
        //test login with empty credentials
        Platform.runLater(() -> {
            controller.handleLogin("", "", label);
            assertEquals("Invalid username or password", label.getText());
            assertNull(controller.getCurrentUser());
        });
        waitForFxEvents();
    }
}
