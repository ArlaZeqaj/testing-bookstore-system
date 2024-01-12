package controller;

import javafx.scene.Scene;
import javafx.stage.Stage;
import view.LoginFormView;
import view.UserDashboardView;

public class UserDashboardController {
    public void handleLogout(Stage primaryStage){
        LoginFormController controller = new LoginFormController(primaryStage);
        LoginFormView loginView = new LoginFormView();
        Scene scene = loginView.getLoginScene(controller);
        scene.getStylesheets().add(UserDashboardView.class.getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
    }
}
