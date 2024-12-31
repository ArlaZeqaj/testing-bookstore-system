package test;

import model.Author;
import model.Book;
import model.Category;
import model.BillUnit;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

class BillUnitTest {
    //add a new book with an initial stock of 10
    Book book1 = new Book("978-0-73829-2849720-5-9", "White Nights", new Category("Classics"), "Penguin", Year.of(2015), 9.0, 16.0, 16.0, new Author("Fyodor", "", "Dostoyevsky"), 10);
    //add book 1 to bill with amount 1
    BillUnit bill1 = new BillUnit(book1, 1);
    //add a new book with an initial stock of 0
    Book book2 = new Book("978-0-73829-2849720-5-9", "The Gambler", new Category("Classics"), "Penguin", Year.of(2015), 10.0, 15.0, 12.0, new Author("Fyodor", "", "Dostoyevsky"), 0);
    //add book2 1 to bill with amount 1
    BillUnit bill2 = new BillUnit(book2, 1);
    @Test
    void testLowerBoundaryAmount() {
        //invalid amount (0) - should not change stock
        bill1.updateStockNo(0);
        assertEquals(10, book1.getStockNo());  //stock remains unchanged
    }

    @Test
    void testValidLowerBoundaryAmount() {
        //valid amount (1) - should decrease stock
        bill1.updateStockNo(1);
        assertEquals(9, book1.getStockNo());  //stock reduced by 1
    }

    @Test
    void testAmountEqualToStock() {
        // Amount equals stock - should set stock to 0
        bill1.updateStockNo(10);
        assertEquals(0, book1.getStockNo());  // Stock reduced to 0
    }

    @Test
    void testAmountGreaterThanStock() {
        // Amount greater than stock - should set stock to 0
        bill1.updateStockNo(15);
        assertEquals(0, book1.getStockNo());  // Stock reduced to 0
    }

    @Test
    void testZeroStock() {
        // Amount greater than 0 but stock is 0 - should remain 0
        bill2.updateStockNo(1);
        assertEquals(0, book2.getStockNo());  // Stock remains 0
    }

    @Test
    void testNegativeAmount() {
        // Negative amount should be ignored (invalid input)
        bill1.updateStockNo(-5);
        assertEquals(10, book1.getStockNo());  // Stock remains unchanged
    }
}
