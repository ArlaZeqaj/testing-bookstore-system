package model;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.Year;
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
        System.out.println("2. Search by ISBN");
        System.out.println("3. Search by author");
        System.out.println("4. Search by category");
    }

    public List<Book> findBookByTitle(String searchTitle, List<Book> books) {
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

    public List<Book> findBookByISBN(String searchISBN, List<Book> books) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getISBN().equals(searchISBN)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public List<Book> findBookByAuthor(String searchAuthor, List<Book> books) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            String authorName = book.getAuthor().toString().toLowerCase();
            if (authorName.contains(searchAuthor)) {
                matchingBooks.add(book);
            }
        }
        return matchingBooks;
    }

    public List<Book> findBookByCategory(String searchCategory, List<Book> books) {
        List<Book> matchingBooks = new ArrayList<>();
        for (Book book : books) {
            String category = book.getCategory().getName().toLowerCase();
            if (category.equals(searchCategory)) {
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
                    //System.out.println(billUnit);
                } else {
                    System.out.println("You don't have access.");
                }
            }
        } else {
            System.out.println("No books found matching the search criteria.");
        }
    }


    // Inside SearchBook class
    public void saveBillToFile(Bill bill) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("bills/" + bill.getBillNo() + ".txt"))) {
            writer.println("Bill details:");
            writer.println("Bill No.: " + bill.getBillNo());
            writer.println("Retailer name: ");
            writer.println("Purchase date: " + bill.getPurchaseDate());

            for (BillUnit billUnit : bill.getBillUnits()) {
                    System.out.println(billUnit.getBook());
                    System.out.print("Enter the amount of books to purchase: "); // Input the amount of books needed
                    int amount = scanner.nextInt();
                    scanner.nextLine();

                    billUnit.setAmount(amount);
                    writer.println("Book: " + billUnit.getBook().getTitle());
                    writer.println("Amount: " + billUnit.getAmount());
                    writer.println("Price: " + billUnit.getBook().getSellingPrice());
                    writer.println("Total Cost: " + (billUnit.getAmount() * billUnit.getBook().getSellingPrice()));
                    writer.println(); // Empty line for separation
            }

            System.out.println("Bill details have been saved to bills/" + bill.getBillNo() + ".txt");
        } catch (IOException e) {
            System.err.println("Error writing the bill details to the file: " + e.getMessage());
        }
    }

}