package model;

import java.util.Date;

public class Bill {
    private String billNo;
    private BillUnit[] books;
    private Date purchaseDate;
    private final double totalCost;

    public Bill(String billNo, BillUnit[] books, Date purchaseDate, double totalCost) {
        this.billNo = billNo;
        this.books = books;
        this.purchaseDate = new Date();
        this.totalCost = calculateTotalCost(); //calculate by adding cost all bill units
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public BillUnit[] getBooks() {
        return books;
    }

    public void setBooks(BillUnit[] books) {
        this.books = books;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    private double calculateTotalCost() {
        double total = 0.0;
        for (BillUnit unit : books) {
            total += unit.getUnitPrice();
        }
        return total;
    }

    public double getTotalCost() {
        return totalCost;
    }
}
