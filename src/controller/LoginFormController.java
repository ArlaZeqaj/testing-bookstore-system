package controller;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import model.Utility.FileReaderUtil;
import view.LoginFormView;
import view.UserDashboardView;

import javax.swing.text.View;
import java.util.ArrayList;

public class LoginFormController {
    //private final LoginFormView view;
    private final Stage primaryStage;
    ArrayList<Librarian> librarians = new LibrarianList().getReadLibrarians();
    ArrayList<Manager> managers = new ManagerList().getReadManagers();
    ArrayList<Admin> admins = FileReaderUtil.readArrayListFromFile("data/binaryFiles/admins.bin");

    public LoginFormController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /*
            public LoginFormView getView() {
                return view;
            }
         */
    public void handleLogin(String username, String password, Label messageLabel) {
        boolean loginSuccessful = false;

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username) && librarian.getPassword().equals(password)) {
                loginSuccessful = true;
                break;
            }
        }
        if (!loginSuccessful) {
            for (Manager manager : managers) {
                if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                    loginSuccessful = true;
                    break;
                }
            }
        }
        if (!loginSuccessful) {
            for (Admin admin : admins) {
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    loginSuccessful = true;
                    break;
                }
            }
        }
        if (loginSuccessful) {
            messageLabel.setText("Login Successful");
            messageLabel.setStyle("-fx-text-fill: green;");
            UserDashboardView.showUserDashboard(primaryStage);
            primaryStage.show();
        } else {
            messageLabel.setText("Invalid username or password");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
}