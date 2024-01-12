package controller;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.*;
import model.Utility.FileReaderUtil;
import view.UserDashboardView;
import java.util.ArrayList;

public class LoginFormController {
    private Stage primaryStage;
    ArrayList<Librarian> librarians = new LibrarianList().getReadLibrarians();
    ArrayList<Manager> managers = new ManagerList().getReadManagers();
    ArrayList<Admin> admins = FileReaderUtil.readArrayListFromFile("data/binaryFiles/admins.bin");
    private User currentUser;

    public LoginFormController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }
    public LoginFormController() {
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public User getCurrentUser() {
        return currentUser;
    }
    public void handleLogin(String username, String password, Label messageLabel) {
        boolean loginSuccessful = false;

        for (Librarian librarian : librarians) {
            if (librarian.getUsername().equals(username) && librarian.getPassword().equals(password)) {
                System.out.println(librarian.getName());
                currentUser = librarian;
                System.out.println(currentUser.getUsername());
                loginSuccessful = true;
                break;
            }
        }
        if (!loginSuccessful) {
            for (Manager manager : managers) {
                if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                    currentUser = manager;
                    loginSuccessful = true;
                    break;
                }
            }
        }
        if (!loginSuccessful) {
            for (Admin admin : admins) {
                if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                    currentUser = admin;
                    loginSuccessful = true;
                    break;
                }
            }
        }
        if (loginSuccessful) {
            messageLabel.setText("Login Successful");
            messageLabel.setStyle("-fx-text-fill: green;");
            UserDashboardView.showUserDashboard(primaryStage, currentUser);
            if (currentUser instanceof Librarian) {
                UserDashboardView.showUserDashboard(primaryStage, (Librarian) currentUser);
            } else if (currentUser instanceof Manager) {
                UserDashboardView.showUserDashboard(primaryStage, (Manager) currentUser);
            } else if (currentUser instanceof Admin) {
                UserDashboardView.showUserDashboard(primaryStage, (Admin) currentUser);
            }
            primaryStage.show();
        } else {
            messageLabel.setText("Invalid username or password");
            messageLabel.setStyle("-fx-text-fill: red;");
        }
    }
}