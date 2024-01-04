package model;

public class BillUnit {
    private final Book book;
    private int amount;
    private final double unitPrice;

    public BillUnit(Book book, int amount) {
        this.book = book; // Assign the book object passed to the BillUnit
        this.amount = amount;
        book.setStockNo(book.getStockNo()-amount);
        this.unitPrice = book.getSellingPrice() * amount;
    }

    public Book getBook() {return book;}

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getUnitPrice() {
        return unitPrice;
    }
    @Override
    public String toString() {
        return "Item: \n" +
                "book: " + book.getTitle() + "\n" +
                "price: " + book.getSellingPrice() + "\n" +
                "amount: " + amount + "\n" +
                "unitPrice: " + unitPrice + "\n";
    }
}
