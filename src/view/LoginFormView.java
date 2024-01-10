package view;
import controller.LoginFormController;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.swing.text.View;

public class LoginFormView extends Application{
    //private final LoginFormController controller = new LoginFormController(primaryStage);
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Bookstore Interactive System");
        LoginFormController controller = new LoginFormController(primaryStage);
        Scene scene = getLoginScene(controller);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public Scene getLoginScene(LoginFormController controller){
        Text titleText = new Text("Bookstore Interactive System");
        titleText.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        GridPane grid = new GridPane();
        grid.setAlignment(javafx.geometry.Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        grid.add(titleText, 0, 0, 2, 1);

        Label usernameLabel = new Label("Username:");
        grid.add(usernameLabel, 0, 1);

        TextField usernameTextField = new TextField();
        grid.add(usernameTextField, 1, 1);

        Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, 2);

        PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, 2);

        Button loginButton = new Button("Login");
        grid.add(loginButton, 1, 4);

        Label messageLabel = new Label();
        grid.add(messageLabel, 1, 6);

        loginButton.setOnAction(e -> {
            String username = usernameTextField.getText();
            String password = passwordField.getText();
            controller.handleLogin(username, password, messageLabel);
        });
        return new Scene(grid, 730, 530);
    }

}
