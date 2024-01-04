package main;

import model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /*
        // testing the creation of Book objects
        Book book1 = new Book("978-0-12345-0123456-7-0", "The Secret History", new Category("Mystery"), "Penguin Books", Year.of(2018), 12.5, 18.0, 16.5, new Author("Donna", "", "Tartt"), 27);
        System.out.println(book1);
        */
        //testing Manager objects
        /*
        Manager manager1 = new Manager("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0);
        System.out.println(manager1);
        */
        Employee employee = new Librarian("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0);
        ManagerList managerList = new ManagerList();
        managerList.printManagers();

        LibrarianList librarianList = new LibrarianList();
        librarianList.printLibrarians();

        Scanner scanner = new Scanner(System.in);

        List<Book> books = new BookList().getBooks(); //get list of books from BookList

        SearchBook searchBook = new SearchBook(scanner); // Pass the scanner to the SearchBook

        searchBook.displaySearchOptions();
        int searchChoice = scanner.nextInt();
        scanner.nextLine();

        if (searchChoice == 1) {
            searchBook.searchByTitle(employee, books);
        } else {
            System.out.println("Invalid choice.");
        }
    }
}
