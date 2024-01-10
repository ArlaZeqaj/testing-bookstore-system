package view;

import controller.LoginFormController;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class UserDashboardView {
    public static void showUserDashboard(Stage primaryStage) {
        VBox dashboardLayout = new VBox(20);
        dashboardLayout.setAlignment(Pos.CENTER);

        Label welcomeLabel = new Label("Welcome to the User Dashboard!");
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        Button logoutButton = new Button("Logout");
        LoginFormController controller = new LoginFormController(primaryStage);
        LoginFormView loginView = new LoginFormView();
        logoutButton.setOnAction(e -> {
            Scene scene = loginView.getLoginScene(controller);
            scene.getStylesheets().add(UserDashboardView.class.getResource("styles.css").toExternalForm());
            primaryStage.setScene(scene);
        });

        dashboardLayout.getChildren().addAll(welcomeLabel, logoutButton);
        Scene dashboardScene = new Scene(dashboardLayout, 730, 530);
        dashboardScene.getStylesheets().add(UserDashboardView.class.getResource("styles.css").toExternalForm());
        primaryStage.setScene(dashboardScene);
    }
}
