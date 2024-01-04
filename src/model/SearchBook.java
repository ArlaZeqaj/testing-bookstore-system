package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SearchBook {
    private final Scanner scanner; // Add Scanner field

    public SearchBook(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displaySearchOptions() {
        System.out.println("Choose the method to search for a book:");
        System.out.println("1. Search by title");
        // Add more search options like ISBN, author, etc.
    }

    public void searchByTitle(Employee employee, List<Book> books) {
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
                if (employee.getAccessLevel() == AccessLevel.SIMPLE || employee.getAccessLevel() == AccessLevel.ADVANCED) {
                    System.out.println(selectedBook);
                    System.out.print("Enter the amount of books to purchase: "); //input the amount of books needed
                    int amount = scanner.nextInt();
                    scanner.nextLine();

                    BillUnit billUnit = new BillUnit(selectedBook,  amount);
                    Bill bill = new Bill(new BillUnit[]{billUnit});

                    System.out.println("\nBill details:");
                    System.out.println("Bill No.: " + bill.getBillNo());
                    System.out.println("Retailer name: ");
                    System.out.println("Book: " + billUnit.getBook().getTitle());
                    System.out.println("Purchase date: " + bill.getPurchaseDate());
                    System.out.println("Amount: " + billUnit.getAmount());
                    System.out.println("Price: " + billUnit.getBook().getSellingPrice());
                    System.out.println("Total Cost: " + bill.getTotalCost());
                    System.out.println(billUnit);

                    System.out.println(selectedBook);
                } else {
                    System.out.println("You don't have access.");
                }
            }
        } else {
            System.out.println("No books found matching the search criteria.");
        }
    }

}