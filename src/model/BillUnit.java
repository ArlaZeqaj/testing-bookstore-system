package model;

public class BillUnit {
    private final Book book;
    private final double price;
    private int amount;
    private final double unitPrice;

    public BillUnit(Book book, String title, double price, int amount) {
        this.book = book; // Assign the book object passed to the BillUnit
        this.price = book.getSellingPrice(); //get the actual book selling price from Book
        this.amount = amount;
        book.setStockNo(book.getStockNo()-amount);
        this.unitPrice = book.getSellingPrice() * amount;
    }

    public Book getBook() {return book;}

    public double getPrice() {
        return price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
}
