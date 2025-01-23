package test.unit;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.Category;
import model.CategoryList;

public class CategoryListTest {

    private CategoryList categoryList;

    @Before
    public void setUp() {
        // Initialize CategoryList and clear existing categories to avoid overlap
        categoryList = CategoryList.getInstance();
        categoryList.clear(); // Make sure the list is empty before each test
    }

    @Test
    public void noDuplicate() {
        Category category1 = new Category("Science Fiction");
        Category category2 = new Category("Horror");

        // Ensure category2 is not a duplicate since it has not been added yet
        assertTrue(categoryList.hasDuplicate(category2));
    }

    @Test
    public void withDuplicate() {
        Category category1 = new Category("Science Fiction");
        Category category2 = new Category("Science Fiction");

        categoryList.addCategory(category1);

        // The second category should be a duplicate since it matches category1
        assertTrue(categoryList.hasDuplicate(category2));
    }

    @Test
    public void whenCategoryIsAdded() {
        Category category1 = new Category("Science Fiction");
        Category category2 = new Category("Science Fiction");

        // Adding category1
        categoryList.addCategory(category1);

        // Adding category2 should cause it to be a duplicate
        assertTrue(categoryList.hasDuplicate(category2));
    }
}
