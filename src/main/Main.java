package main;

import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import model.Utility.FilePrinterUtil;
import model.Utility.FileReaderUtil;
import model.Utility.FileWriterUtil;
import view.LoginFormView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static model.AuthorList.printAuthors;
import static model.CategoryList.printCategories;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(LoginFormView.class, args);
        Employee employee = new Librarian("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0, "2323");

        Scanner scanner = new Scanner(System.in);

        List<Book> books = new BookList().getReadBooks(); // Get list of books from BookList
        ArrayList<Librarian> librarians = new LibrarianList().getReadLibrarians();
        ArrayList<Manager> managers = new ManagerList().getReadManagers();
        ArrayList<Author> authors = new AuthorList().getReadAuthors();
        //printAuthors(authors);
        ArrayList<Category> categories = new CategoryList().getReadCategories();
        //printCategories(categories);
        ArrayList<Admin> admins;
/*
        admins.add(new Admin("arlazeqaj", "00000"));
        admins.add(new Admin("estelamele", "12345"));
        admins.add(new Admin("ildalama", "34343"));
        FileWriterUtil.writeArrayListToFile(admins, "data/binaryFiles/admins.bin");
        System.out.println("ArrayList of admins has been written to " + "data/binaryFiles/admins.bin");
 */

        admins = FileReaderUtil.readArrayListFromFile("data/binaryFiles/admins.bin");
        System.out.println("ArrayList of admins from file: ");
        for (Admin admin : admins) {
            System.out.println("Username: " + admin.getUsername());
            System.out.println("Password: " + admin.getPassword());
            System.out.println();
        }

        SearchBook searchBook = new SearchBook(scanner); // Pass the scanner to the SearchBook

        searchBook.displaySearchOptions();
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        List<Book> selectedBooks = new ArrayList<>(); // This will store selected books

        if (searchChoice == 1) {
            System.out.println("Enter part of the book title you want to search:");
            String searchTitle = scanner.nextLine().toLowerCase();
            selectedBooks = searchBook.findBookByTitle(searchTitle, books);
        } else if (searchChoice == 2) {
            System.out.println("Enter the ISBN of the book:");
            String searchISBN = scanner.nextLine();
            selectedBooks = searchBook.findBookByISBN(searchISBN, books);
        } else if (searchChoice == 3) {
            System.out.println("Enter the name of the author:");
            String searchAuthor = scanner.nextLine().toLowerCase();
            selectedBooks = searchBook.findBookByAuthor(searchAuthor, books);
        } else if (searchChoice == 4) {
            System.out.println("Enter the name of the category:");
            String searchCategory = scanner.nextLine().toLowerCase();
            selectedBooks = searchBook.findBookByCategory(searchCategory, books);
        } else {
            System.out.println("Invalid choice.");
        }

        if (!selectedBooks.isEmpty()) {
            for (Book selectedBook : selectedBooks) {
                //create a Bill object here
                BillUnit billUnit = new BillUnit(selectedBook, 1); // 1 is an example; replace it with the actual quantity
                Bill bill = new Bill(new BillUnit[]{billUnit});
                searchBook.saveBillToFile(bill);
            }
        } else {
            System.out.println("No books found matching the search criteria.");
        }
    }
    //we can add exceptions here
    @Override
    public void start(Stage primaryStage) {
    }

}
