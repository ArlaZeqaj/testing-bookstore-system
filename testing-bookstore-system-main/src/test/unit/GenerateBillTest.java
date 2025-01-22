package test.unit;

import model.Bill;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GenerateBillTest {
    @Test
    public void testStatementCoverage() {
        assertNotNull(Bill.generateRandomBillNumber(12));
        assertNotNull(Bill.generateRandomBillNumber(8));
        assertNotNull(Bill.generateRandomBillNumber(0));  // Edge case
        assertNotNull(Bill.generateRandomBillNumber(20)); // Large length
        assertThrows(IllegalArgumentException.class, () -> Bill.generateRandomBillNumber(-5));  // Negative length
    }


    @Test
    public void testBranchCoverage() {
        // Test branch when length is positive
        assertNotNull(Bill.generateRandomBillNumber(12));

        // Test branch when length is zero
        assertNotNull(Bill.generateRandomBillNumber(0));

        // Test branch for invalid negative length
        assertThrows(IllegalArgumentException.class, () -> Bill.generateRandomBillNumber(-5));
    }

    @Test
    public void testConditionCoverage() {
        // Testing condition where length > 0
        assertNotNull(Bill.generateRandomBillNumber(12));

        // Testing condition where length == 0
        assertNotNull(Bill.generateRandomBillNumber(0));

        // Testing condition where length < 0 (Negative length)
        assertThrows(IllegalArgumentException.class, () -> Bill.generateRandomBillNumber(-5));
    }

    @Test
    public void testMCDC() {
        // Test for MC/DC coverage: true and false branches for all involved conditions
        assertNotNull(Bill.generateRandomBillNumber(12));
        assertNotNull(Bill.generateRandomBillNumber(8));
        assertNotNull(Bill.generateRandomBillNumber(20));
        assertThrows(IllegalArgumentException.class, () -> Bill.generateRandomBillNumber(-5));
    }

}
