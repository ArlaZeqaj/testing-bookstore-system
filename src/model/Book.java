package model;

import model.Utility.ValidationUtil;

import java.time.Year;
import java.time.LocalDate;
import java.io.Serializable;

public class Book implements Serializable {
    private String ISBN;
    private String title;
    private String category;
    private String supplierName;
    private Year publishYear;
    private final LocalDate purchasedDate;
    private double purchasedPrice;
    private double originalPrice;
    private double sellingPrice;
    private String author;
    private int stockNo;

    public Book(String ISBN, String title, Category category, String supplierName, Year publishYear, double purchasedPrice, double originalPrice, double sellingPrice, Author author, int stockNo) {
        // Validate ISBN using regex
        if (ValidationUtil.isValid(ISBN, ValidationUtil.ISBN_13_REGEX)) {
            this.ISBN = ISBN;
        } else {
            throw new IllegalArgumentException("Invalid ISBN format");
        }

        // Validate title using regex
        if (ValidationUtil.isValid(title, ValidationUtil.BOOK_TITLE_REGEX)) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Invalid title format");
        }

        // Validate category name using regex
        if (ValidationUtil.isValid(category.getName(), ValidationUtil.STRING_REGEX)) {
            this.category = category.getName();  // Use category.getName() to get the category name
        } else {
            throw new IllegalArgumentException("Invalid category format");
        }

        // Validate supplier name using regex
        if (ValidationUtil.isValid(supplierName, ValidationUtil.BOOK_TITLE_REGEX)) {
            this.supplierName = supplierName;
        } else {
            throw new IllegalArgumentException("Invalid supplier name format");
        }

        // Validate author name using regex
        String authorFullName = author.getFirstName() + " " + author.getLastName();
        if (ValidationUtil.isValid(authorFullName, ValidationUtil.BOOK_TITLE_REGEX)) {
            this.author = authorFullName;  // Concatenate first and last name of the author
        } else {
            throw new IllegalArgumentException("Invalid author format");
        }

        // Validate publishYear (must not be older than the current year)
        if (publishYear.isAfter(Year.now())) {
            throw new IllegalArgumentException("The publication year must be valid and not in the future.");
        } else {
            this.publishYear = publishYear;
        }

        if (purchasedPrice < 0 || originalPrice < 0 || sellingPrice < 0) {
            throw new IllegalArgumentException("Price values must be non-negative");
        }
        this.purchasedDate = LocalDate.now();  // Current date as purchased date
        this.purchasedPrice = purchasedPrice;
        this.originalPrice = originalPrice;
        this.sellingPrice = sellingPrice;
        if (stockNo <= 0) {
            throw new IllegalArgumentException("Stock number must be greater than zero");
        }
        this.stockNo = stockNo;
    }


    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public Year getPublishYear() {
        return publishYear;
    }

    public  LocalDate getPurchasedDate() {
        return purchasedDate;
    }

    public double getPurchasedPrice() {
        return purchasedPrice;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public String getAuthor() {
        return author;
    }

    public int getStockNo() {
        return stockNo;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setStockNo(int stockNo) {this.stockNo = stockNo;}

    public void setISBN(String ISBN) {
        if(ValidationUtil.isValid(ISBN, ValidationUtil.ISBN_13_REGEX))
            this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setPublishYear(Year publishYear) {
        if (publishYear.isAfter(Year.now())) {
            throw new IllegalArgumentException("The publication year must be valid and not in the future.");
        } else if (publishYear.isBefore(Year.now().minusYears(1))) {
            throw new IllegalArgumentException("The publication year must be valid and not in the past.");
        } else {
            this.publishYear = publishYear;
        }
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public void setPurchasedPrice(double purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    @Override
    public String toString() {
        return "Book details: \n" +
                "ISBN: " + ISBN + '\n' +
                "title: " + title + '\n' +
                "category: " + category.toString() + '\n' +
                "supplierName: " + supplierName + '\n' +
                "publishYear: " + publishYear + '\n' +
                "purchasedDate: " + purchasedDate + '\n' +
                "purchasedPrice: " + purchasedPrice + '\n' +
                "originalPrice: " + originalPrice + '\n' +
                "sellingPrice: " + sellingPrice + '\n' +
                "author: " + author + '\n' +
                "stockNo: " + stockNo ;
    }
}