package main;

import model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Employee employee = new Librarian("Erion", "Molla", LocalDate.of(1998, 5, 11), "+355697900872", 65000.0, "2323");

        Scanner scanner = new Scanner(System.in);

        List<Book> books = new BookList().getReadBooks(); // Get list of books from BookList

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
                // Create a Bill object here...
                BillUnit billUnit = new BillUnit(selectedBook, 1); // 1 is an example; replace it with the actual quantity
                Bill bill = new Bill(new BillUnit[]{billUnit});
                searchBook.saveBillToFile(bill);
            }
        } else {
            System.out.println("No books found matching the search criteria.");
        }
    }
}
