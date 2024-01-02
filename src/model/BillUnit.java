package model;

public class BillUnit {
    private final String title;
    private final double price;
    private final int amount;
    private final double unitPrice;

    public BillUnit(String title, double price, int amount, double unitPrice) {
        this.title = title; //check that this book title exists
        this.price = price; //get the actual book selling price from Book
        this.amount = amount;
        this.unitPrice = price*amount;
    }
    public double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public int getAmount() {
        return amount;
    }
    public double getUnitPrice() {
        return unitPrice;
    }
}
