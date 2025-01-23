package test.unit;

import model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

public class BillUnitTest1 {
    private Book testBook;

    @BeforeEach
    public void setUp() {
        testBook = new Book(
                "978-0-12345-6789125-3-3",
                "The Great Gatsby",
                new Category("Classic"),
                "Random House",
                Year.of(1925),
                8.5,
                12.0,
                10.0,
                new Author("F.", "Scott ", "Fitzgerald"),
                100);
    }

    @Test
    public void testTotalCostWithZeroAmount() {
        BillUnit billUnit = new BillUnit(testBook, 0);
        assertEquals(0.0, billUnit.getTotalCost(), "Total cost should be 0 when amount is 0");
    }

    @Test
    public void testTotalCostWithNegativeAmount() {
        BillUnit billUnit = new BillUnit(testBook, -1);
        assertEquals(0.0, billUnit.getTotalCost(), "Total cost should be 0 when amount is negative");
    }

    @Test
    public void testTotalCostWithValidAmount() {
        BillUnit billUnit1 = new BillUnit(testBook, 1);
        assertEquals(10.0, billUnit1.getTotalCost(), "Total cost should be 10 for amount 1");

        BillUnit billUnit2 = new BillUnit(testBook, 10);
        assertEquals(100.0, billUnit2.getTotalCost(), "Total cost should be 100 for amount 10");
    }

    @Test
    public void testTotalCostWithMaximumStockAmount() {
        BillUnit billUnit = new BillUnit(testBook, 100);
        assertEquals(1000.0, billUnit.getTotalCost(), "Total cost should be correctly calculated for maximum stock amount");
    }

    @Test
    public void testTotalCostWithAboveMaximumStockAmount() {
        BillUnit billUnit = new BillUnit(testBook, 101);
        assertEquals(0.0, billUnit.getTotalCost(), "Total cost should be 0 when amount exceeds stock");
    }
}
