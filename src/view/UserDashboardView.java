package view;

import controller.LoginFormController;
import controller.UserDashboardController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.*;

import java.time.Year;
import java.util.List;

public class UserDashboardView {
    public UserDashboardView() {
    }
    private final LoginFormController loginFormController = new LoginFormController();
    User currentUser = loginFormController.getCurrentUser();
    static List<Book> books = new BookList().getReadBooks();

    public static void showUserDashboard(Stage primaryStage, User currentUser) {
        Employee employee;
        if (currentUser instanceof Librarian) {
            employee = (Librarian) currentUser;
        } else if (currentUser instanceof Manager) {
            employee = (Manager) currentUser;
        } else {
            employee = null;
            if (currentUser instanceof Admin) {
                UserDashboardView.showUserDashboard(primaryStage, (Admin) currentUser);
            }
        }
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Button btnProfile = new Button("Profile");
        Button btnBooks = new Button("Books");
        Button btnSettings = new Button("Settings");
        Button logoutButton = new Button("Logout");

        gridPane.add(btnProfile, 0, 0);
        gridPane.add(btnBooks, 0, 1);
        gridPane.add(btnSettings, 0, 2);
        gridPane.add(logoutButton, 0, 3);      

        btnProfile.setOnAction(e -> {
            assert employee != null;
            showAlert("Name: " + employee.getName());
        });
        btnBooks.setOnAction(e -> showBooksTable(primaryStage, books));
        btnSettings.setOnAction(e -> showAlert("Settings button clicked"));
        VBox dashboardLayout = new VBox(20);
        dashboardLayout.setAlignment(Pos.TOP_CENTER);
        dashboardLayout.setPadding(new Insets(10, 10, 10, 10));

        assert employee != null;
        Label welcomeLabel = new Label("Welcome " + employee.getName() + " " + employee.getSurname());
        welcomeLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        UserDashboardController userController = new UserDashboardController();
        logoutButton.setOnAction(e -> userController.handleLogout(primaryStage));

        dashboardLayout.getChildren().addAll(welcomeLabel, gridPane);
        Scene dashboardScene = new Scene(dashboardLayout, 730, 530);
        dashboardScene.getStylesheets().add(UserDashboardView.class.getResource("styles.css").toExternalForm());
        primaryStage.setScene(dashboardScene);
    }
    private static void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("User Dashboard");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
    private static void showBooksTable(Stage primaryStage, List<Book> books) {
        VBox dashboardLayout = new VBox(20);
        dashboardLayout.setAlignment(Pos.TOP_CENTER);
        dashboardLayout.setPadding(new Insets(10, 10, 10, 10));

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Button btnProfile = new Button("Profile");
        Button btnBooks = new Button("Books");
        Button btnSettings = new Button("Settings");
        Button logoutButton = new Button("Logout");

        gridPane.add(btnProfile, 0, 0);
        gridPane.add(btnBooks, 0, 1);
        gridPane.add(btnSettings, 0, 2);
        gridPane.add(logoutButton, 0, 3);

        btnBooks.setOnAction(e -> showBooksTable(primaryStage, books));
        btnSettings.setOnAction(e -> showAlert("Settings button clicked"));
        dashboardLayout.setAlignment(Pos.TOP_CENTER);
        dashboardLayout.setPadding(new Insets(10, 10, 10, 10));

        UserDashboardController userController = new UserDashboardController();
        logoutButton.setOnAction(e -> userController.handleLogout(primaryStage));
        Label titleLabel = new Label("Book List");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        TableView<Book> tableView = new TableView<>();
        TableColumn<Book, String> titleColumn = new TableColumn<>("Title");
        TableColumn<Book, String> authorColumn = new TableColumn<>("Author");
        TableColumn<Book, Year> yearColumn = new TableColumn<>("Year");

        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("publishYear"));

        tableView.getColumns().addAll(titleColumn, authorColumn, yearColumn);

        ObservableList<Book> bookData = FXCollections.observableArrayList(books);

        tableView.setItems(bookData);

        dashboardLayout.getChildren().clear();
        dashboardLayout.getChildren().addAll(titleLabel, gridPane, tableView);

        Scene dashboardScene = new Scene(dashboardLayout, 730, 530);
        dashboardScene.getStylesheets().add(UserDashboardView.class.getResource("styles.css").toExternalForm());
        primaryStage.setScene(dashboardScene);
    }

}
