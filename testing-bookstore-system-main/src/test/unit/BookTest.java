package test.unit;

import model.Author;
import model.Book;
import model.Category;
import org.junit.jupiter.api.Test;

import java.time.Year;

import static org.junit.jupiter.api.Assertions.*;

public class BookTest {
    @Test
    public void testValidInputs() {
        Book testBook = new Book(
                "978-0-13131-3131317-1-2",
                "Crime and Punishment",
                new Category("Classic"),
                "Vintage Books",
                Year.of(2018),
                7.0,
                11.0,
                9.5,
                new Author("Fyodor", "", "Dostoevsky"),
                4);
        assertNotNull(testBook);
    }

    @Test
    public void testInvalidISBN() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("123",
                    "The Secret History",
                    new Category("Mystery"),
                    "Penguin Books",
                    Year.of(2018),
                    12.5,
                    18.0,
                    16.5,
                    new Author("Donna", "", "Tartt"),
                    27);
        });
        assertEquals("The format of this field is incorrect!", exception.getMessage());
    }

    @Test
    public void testInvalidTitle() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book(
                    "978-0-73829-2849720-5-9",
                    "INVALID_TITLE",
                    new Category("Mystery"),
                    "Penguin Books",
                    Year.of(2018),
                    12.5,
                    18.0,
                    16.5,
                    new Author("Donna", "", "Tartt"),
                    27
            );
        });
        assertEquals("The format of this field is incorrect!", exception.getMessage());
    }

    @Test
    public void testInvalidCategory() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9", "The Secret History",
                    new Category("123"),
                    "Penguin Books",
                    Year.of(2018),
                    12.5,
                    18.0,
                    16.5,
                    new Author("Donna", "", "Tartt"),
                    27);
        });
        assertEquals("The format of this field is incorrect!", exception.getMessage());
    }

    @Test
    public void testInvalidSupplierName() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9",
                    "The Secret History",
                    new Category("Mystery"),
                    "",  // Invalid supplierName
                    Year.of(2018),
                    12.5,
                    18.0,
                    16.5,
                    new Author("Donna", "", "Tartt"),
                    27);
        });
        assertEquals("This field cannot be empty!", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9",
                    "The Secret History",
                    new Category("Mystery"),
                    "P@nguin Books",  // Invalid supplierName (contains special character)
                    Year.of(2018),
                    12.5,
                    18.0,
                    16.5,
                    new Author("Donna", "", "Tartt"),
                    27);
        });
        assertEquals("The format of this field is incorrect!", exception.getMessage());
    }

    @Test
    public void testInvalidIsoYear() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9",
                    "The Secret History",
                    new Category("Mystery"),
                    "Penguin Books",
                    Year.of(2029),
                    12.5,
                    18.0,
                    16.5,
                    new Author("Donna", "", "Tartt"),
                    27);
        });
        assertEquals("The publication year must be valid and not in the future.", exception.getMessage());
    }

    @Test
    public void testInvalidPrices() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9",
                    "The Secret History",
                    new Category("Mystery"),
                    "Penguin Books",
                    Year.of(2018),
                    -5.0,  // Invalid purchasedPrice
                    25.0,
                    18.0,
                    new Author("Donna", "", "Tartt"),
                    27);
        });
        assertEquals("Price values must be non-negative", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9",
                    "The Secret History",
                    new Category("Mystery"),
                    "Penguin Books",
                    Year.of(2018),
                    20.0,
                    -25.0,  // Invalid originalPrice
                    18.0,
                    new Author("Donna", "", "Tartt"),
                    27);
        });
        assertEquals("Price values must be non-negative", exception.getMessage());

        exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9",
                    "The Secret History",
                    new Category("Mystery"),
                    "Penguin Books",
                    Year.of(2018),
                    20.0,
                    25.0,
                    -18.0,  // Invalid sellingPrice
                    new Author("Donna", "", "Tartt"),
                    27);
        });
        assertEquals("Price values must be non-negative", exception.getMessage());
    }

    @Test
    public void testInvalidAuthor() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9", "The Secret History",
                    new Category("Mystery"),
                    "Penguin Books",
                    Year.of(2018),
                    12.5,
                    18.0,
                    16.5,
                    new Author("", "", ""),
                    27);
        });
        assertEquals("This field cannot be empty!", exception.getMessage());
    }

    @Test
    public void testInvalidStock() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Book("978-0-73829-2849720-5-9", "The Secret History",
                    new Category("Mystery"),
                    "Penguin Books",
                    Year.of(2018),
                    12.5,
                    18.0,
                    16.5,
                    new Author("Donna", "", "Tartt"),
                    -5);
        });
        assertEquals("Stock number must be greater than zero", exception.getMessage());
    }

    @Test
    public void testIsoYear() {
        Book testBook = new Book(
                "978-0-73829-2849720-5-9",
                "The Secret History",
                new Category("Mystery"),
                "Penguin Books",
                Year.of(2018),
                12.5,
                18.0,
                16.5,
                new Author("Donna", "", "Tartt"),
                27);

        assertEquals(2018, testBook.getPublishYear().getValue());
    }
}
