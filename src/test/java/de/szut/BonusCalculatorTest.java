package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BonusCalculator.
 * Contains focused tests that verify the complete bonus chain functionality.
 */
class BonusCalculatorTest {

    private BonusCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new BonusCalculator();
    }

    @Test
    void testExampleEmployeeA() {
        Employee employeeA = new NormalEmployee("A", 6, 50, 2, 2);
        double bonus = calculator.calculate(employeeA);
        assertEquals(840.0, bonus, 0.01);
    }

    @Test
    void testExampleEmployeeB() {
        Employee employeeB = new TeamLeader("B", 12, 80, 5, 16);
        double bonus = calculator.calculate(employeeB);
        assertEquals(1170.0, bonus, 0.01);
    }

    @Test
    void testMinBoundary() {
        Employee minEmployee = new NormalEmployee("MinCase", 0, 20, 0, 11);
        double bonus = calculator.calculate(minEmployee);

        // Base 300 * 1.07 = 321 -> Below minimum, should be 500
        assertEquals(500.0, bonus, 0.01);
    }

    @Test
    void testMaxBoundary() {
        Employee maxEmployee = new TeamLeader("MaxCase", 30, 90, 20, 0);
        double bonus = calculator.calculate(maxEmployee);

        // Expected to be capped at maximum 2000.0
        assertEquals(2000.0, bonus, 0.01);
    }

    @Test
    void testAllBonusComponentsInteraction() {
        Employee employee = new TeamLeader("CompleteTest", 8, 45, 3, 5);
        double bonus = calculator.calculate(employee);

        // Calculation:
        // Base: 300
        // Seniority (8 years): +150 (one 5-year interval)
        // Projects (3): +150 (3 * 50)
        // Team leader: +200
        // Low absence (5 days <= 10): +50
        // Subtotal: 850
        // Performance multiplier (medium): 850 * 1.12 = 952
        assertEquals(952.0, bonus, 0.01);
    }
}