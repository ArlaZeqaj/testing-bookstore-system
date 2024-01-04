package model;

//secureRandom generates secure random numbers, with a lower chance of repeating compared to math random
import java.security.SecureRandom;
import java.util.Date;

public class Bill {
    private String billNo;
    private BillUnit[] books;
    private Date purchaseDate;
    private final double totalCost;


    public Bill(BillUnit[] books, Date purchaseDate, double totalCost) {
        this.billNo = generateRandomBillNumber(12); //generates a 9-character bill number
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
    private static final String ALLOWED_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRandomBillNumber(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(ALLOWED_CHARACTERS.length());
            stringBuilder.append(ALLOWED_CHARACTERS.charAt(randomIndex));
        }

        return stringBuilder.toString();
    }

    public double getTotalCost() {
        return totalCost;
    }
}
