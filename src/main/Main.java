package main;

import model.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Employee employee = new Librarian("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0);

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
