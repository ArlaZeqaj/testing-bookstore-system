package test.unit;

import model.Author;
import model.AuthorList;
import model.Category;
import model.CategoryList;
import org.junit.jupiter.api.*;

import java.io.File;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HasDuplicateTest {

    private static final String CATEGORY_FILE_PATH = "data/binaryFiles/categories.bin";
    private static final String AUTHOR_FILE_PATH = "data/binaryFiles/authors.bin";

    @BeforeAll
    static void setup() {
        File categoryFile = new File(CATEGORY_FILE_PATH);
        File authorFile = new File(AUTHOR_FILE_PATH);

        // Ensure the directory exists
        categoryFile.getParentFile().mkdirs();

        // Delete files if they exist (reset state)
        categoryFile.delete();
        authorFile.delete();
    }

    @Test
    @Order(1)
    void testHasDuplicateForCategory() {
        CategoryList categoryList = new CategoryList();

        Category category1 = new Category("Fiction");
        Category category2 = new Category("Non-Fiction");

        // Check that no duplicates exist initially
        System.out.println("Checking if 'Fiction' exists initially...");
        assertFalse(categoryList.hasDuplicate(category1), "Category 'Fiction' should not exist initially.");

        System.out.println("Checking if 'Non-Fiction' exists initially...");
        assertFalse(categoryList.hasDuplicate(category2), "Category 'Non-Fiction' should not exist initially.");

        // Add categories
        System.out.println("Adding categories 'Fiction' and 'Non-Fiction'...");
        categoryList.addCategory(category1);
        categoryList.addCategory(category2);

        // Check for duplicates after adding
        System.out.println("Checking for duplicate category 'Fiction'...");
        Category duplicateCategory = new Category("Fiction");
        assertTrue(categoryList.hasDuplicate(duplicateCategory), "Category 'Fiction' should now exist as a duplicate.");
    }

    @Test
    @Order(2)
    void testHasDuplicateForAuthor() {
        AuthorList authorList = new AuthorList();

        Author author1 = new Author("Jane", "", "Austen");
        Author author2 = new Author("Mark", "", "Twain");

        // Check that no duplicates exist initially
        System.out.println("Checking if 'Jane Austen' exists initially...");
        assertFalse(authorList.hasDuplicate(author1), "Author 'Jane Austen' should not exist initially.");

        System.out.println("Checking if 'Mark Twain' exists initially...");
        assertFalse(authorList.hasDuplicate(author2), "Author 'Mark Twain' should not exist initially.");

        // Add authors
        System.out.println("Adding authors 'Jane Austen' and 'Mark Twain'...");
        authorList.addAuthor(author1);
        authorList.addAuthor(author2);

        // Check for duplicates after adding
        System.out.println("Checking for duplicate author 'Jane Austen'...");
        Author duplicateAuthor = new Author("Jane", "", "Austen");
        assertTrue(authorList.hasDuplicate(duplicateAuthor), "Author 'Jane Austen' should now exist as a duplicate.");
    }

    @AfterAll
    static void cleanup() {
        // Clean up the binary files after all tests
        System.out.println("Cleaning up binary files...");
        new File(CATEGORY_FILE_PATH).delete();
        new File(AUTHOR_FILE_PATH).delete();
    }
}
