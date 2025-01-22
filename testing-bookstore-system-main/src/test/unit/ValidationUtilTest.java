package test.unit;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import model.Utility.ValidationUtil;

class ValidationUtilTest {

    @Test

    void testFieldNullThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.isValid(null, ValidationUtil.BOOK_TITLE_REGEX);
        });
        assertEquals("This field cannot be empty!", exception.getMessage());
    }

    @Test
    void testFieldEmptyThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.isValid("", ValidationUtil.BOOK_TITLE_REGEX);
        });
        assertEquals("This field cannot be empty!", exception.getMessage());
    }

    @Test
    void testFieldSpacesOnlyThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.isValid("   ", ValidationUtil.BOOK_TITLE_REGEX);
        });
        assertEquals("This field cannot be empty!", exception.getMessage());
    }

    @Test
    void testValidBookTitle() {
        assertTrue(ValidationUtil.isValid("Valid Title", ValidationUtil.BOOK_TITLE_REGEX));
    }

    @Test
    void testInvalidBookTitleThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.isValid("Invalid@Title", ValidationUtil.BOOK_TITLE_REGEX);
        });
        assertEquals("The format of this field is incorrect!", exception.getMessage());
    }

    @Test
    void testValidISBN13() {
        assertTrue(ValidationUtil.isValid("978-0-73829-2849720-5-9", ValidationUtil.ISBN_13_REGEX));
    }

    @Test
    void testInvalidISBN13ThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.isValid("978-3-16-INVALID", ValidationUtil.ISBN_13_REGEX);
        });
        assertEquals("The format of this field is incorrect!", exception.getMessage());
    }

    @Test
    void testValidPhoneNumber() {
        assertTrue(ValidationUtil.isValid("+355662345678", ValidationUtil.PHONE_REGEX));
    }

    @Test
    void testInvalidPhoneNumberThrowsException() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ValidationUtil.isValid("invalidPhone", ValidationUtil.PHONE_REGEX);
        });
        assertEquals("The format of this field is incorrect!", exception.getMessage());
    }
}
