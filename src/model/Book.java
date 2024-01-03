package model;

import java.time.Year;
import java.time.LocalDate;

public class Book {
    private String ISBN;
    private String title;
    private Category category;
    private String supplierName;
    //added the book publication year
    private Year publishYear;
    private final LocalDate purchasedDate;
    private final double purchasedPrice;
    private final double originalPrice;
    private double sellingPrice;
    private Author author;
    private int stockNo;
    private static final String ISBN_13_REGEX = "^(978|979)-\\d{1,5}-\\d{1,7}-\\d{1,7}-\\d{1,7}-\\d$";
    private static final String STRING_REGEX = "^[\\p{L}\\s'.-]+$"; //must consist only of Unicode letters, whitespaces, single quotes, periods, or hyphens

    public Book(String ISBN, String title, Category category, String supplierName, Year publishYear, double purchasedPrice, double originalPrice, double sellingPrice, Author author, int stockNo) {
       if(isValid(ISBN, ISBN_13_REGEX))
            this.ISBN = ISBN;
        if(isValid(title, STRING_REGEX))
            this.title = title;
        if(isValid(category.getName(), STRING_REGEX))
            this.category = category;
        if(isValid(supplierName, STRING_REGEX))
            this.supplierName = supplierName;
        this.publishYear = publishYear;
        this.purchasedDate =  LocalDate.now();
        this.purchasedPrice = purchasedPrice;
        this.originalPrice = originalPrice;
        this.sellingPrice = sellingPrice;
        if(isValid(author.toString(), STRING_REGEX))
            this.author = author;
        this.stockNo = stockNo;
    }

    public String getISBN() {
        return ISBN;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
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

    public Author getAuthor() {
        return author;
    }

    public int getStockNo() {
        return stockNo;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setStockNo(int stockNo) {
        this.stockNo = stockNo;
    }

    public void setISBN(String ISBN) {
        if(isValid(ISBN, ISBN_13_REGEX))
            this.ISBN = ISBN;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public void setPublishYear(Year publishYear) {
        this.publishYear = publishYear;
    }
    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
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

    //method for validating string fields
    public static boolean isValid(String field, String regEx){
        if(field == null || field.trim().isEmpty()){
            throw new IllegalArgumentException("This field cannot be empty!");
        } else if (!(field.matches(regEx))){
            throw new IllegalArgumentException("The format of this field is incorrect!");
        }
        return true;
    }

}
