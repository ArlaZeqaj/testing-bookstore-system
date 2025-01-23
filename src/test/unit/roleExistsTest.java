package test.unit;

import model.Roles;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class roleExistsTest {

    @Test
    public void existsWithExistingRole() {
        // Arrange
        List<String> roles = new ArrayList<>();
        roles.add(Roles.CREATE_BILL);

        // Act
        boolean result = Roles.roleExists(roles, Roles.CREATE_BILL);

        // Assert
        assertTrue(result, "The role should exist in the list.");
    }

    @Test
    public void existsWithNonExistingRole() {
        // Arrange
        List<String> roles = new ArrayList<>();
        roles.add(Roles.CREATE_BILL);

        // Act
        boolean result = Roles.roleExists(roles, Roles.ADD_NEW_BOOKS);

        // Assert
        assertFalse(result, "The role should not exist in the list.");
    }

    @Test
    public void existsWithEmptyList() {
        // Arrange
        List<String> roles = new ArrayList<>();

        // Act
        boolean result = Roles.roleExists(roles, Roles.CREATE_BILL);

        // Assert
        assertFalse(result, "The role should not exist in an empty list.");
    }

    @Test
    public void existsWithNullRole() {
        // Arrange
        List<String> roles = new ArrayList<>();
        roles.add(Roles.CREATE_BILL);

        // Act
        boolean result = Roles.roleExists(roles, null);

        // Assert
        assertFalse(result, "A null role should not exist in the list.");
    }

    @Test
    public void existsWithNullList() {
        // Act and Assert
        assertThrows(NullPointerException.class, () -> Roles.roleExists(null, Roles.CREATE_BILL),
                "Calling roleExists with a null list should throw a NullPointerException.");
    }
}
