package main;

import model.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchBook {
    public void displaySearchOptions() {
        System.out.println("Choose the method to search for a book:");
        System.out.println("1. Search by title");
        // Add more search options like IBSN, author, etc.
    }

    public void searchByTitle(Scanner scanner, Employee employee, List<Book> books) {
        System.out.println("Enter part of the book title you want to search:");
        String searchTitle = scanner.nextLine().toLowerCase();
        List<Book> selectedBooks = findBookByTitle(searchTitle, books);
        displayBookDetails(selectedBooks, employee);
    }

    private List<Book> findBookByTitle(String searchTitle, List<Book> books) {
        List<Book> matchingBooks = new ArrayList<>();
        String searchTitleLower = searchTitle.toLowerCase();

        for (Book book : books) {
            String bookTitleLower = book.getTitle().toLowerCase();
            if (bookTitleLower.contains(searchTitleLower)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    private void displayBookDetails(List<Book> selectedBooks, Employee employee) {
        if (!selectedBooks.isEmpty()) {
            for (Book selectedBook : selectedBooks) {
                if (employee.getAccessLevel() == AccessLevel.SIMPLE) {
                    // For librarian: Create a bill
                    BillUnit billUnit = new BillUnit(selectedBook, "the", 23, 2, 6);
                    Bill bill = new Bill("12345", new BillUnit[]{billUnit}, null, 0.0);

                    // Display bill details
                    System.out.println("\nBill details for " + selectedBook.getTitle() + ":");
                    System.out.println("Bill Number: " + bill.getBillNo());
                    System.out.println("Book: " + billUnit.getBook().getTitle());
                    System.out.println("Purchase Date: " + bill.getPurchaseDate());
                    System.out.println("Amount: " + billUnit.getAmount());
                    System.out.println("Total Cost: " + bill.getTotalCost());
                } else if (employee.getAccessLevel() == AccessLevel.ADVANCED) {
                    System.out.println("Manager can add a book.");

                    // Create a bill
                    BillUnit billUnit = new BillUnit(selectedBook, "the", 23, 2, 6);
                    Bill bill = new Bill("12345", new BillUnit[]{billUnit}, null, 0.0);

                    // Display bill details
                    System.out.println("\nBill details for " + selectedBook.getTitle() + ":");
                    System.out.println("Bill Number: " + bill.getBillNo());
                    System.out.println("Book: " + billUnit.getBook().getTitle());
                    System.out.println("Purchase Date: " + bill.getPurchaseDate());
                    System.out.println("Amount: " + billUnit.getAmount());
                    System.out.println("Total Cost: " + bill.getTotalCost());
                }
            }
        } else {
            System.out.println("No books found matching the search criteria.");
        }
    }
}
