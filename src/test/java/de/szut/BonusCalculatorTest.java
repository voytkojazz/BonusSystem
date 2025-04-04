package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BonusCalculator.
 * Contains focused tests that verify the complete bonus chain functionality.
 */
public class BonusCalculatorTest {

    private BonusCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new BonusCalculator();
    }

    @Test
    void testExampleEmployeeA() {
        // Example from README: Employee A
        // - 6 years at company
        // - 2 projects
        // - Not a team leader
        // - 2 absence days (low absence bonus tier)
        // - Medium performance (50)
        Employee employeeA = new NormalEmployee("A", 6, 50, 2, 2);
        double bonus = calculator.calculate(employeeA);

        // Expected: 840.0 (as per README example)
        assertEquals(840.0, bonus, 0.01);
    }

    @Test
    void testExampleEmployeeB() {
        // Example from README: Employee B
        // - 12 years at company
        // - 5 projects
        // - Team leader
        // - 16 absence days (high absence penalty tier)
        // - High performance (80)
        Employee employeeB = new TeamLeader("B", 12, 80, 5, 16);
        double bonus = calculator.calculate(employeeB);

        // Expected: 1170.0 (as per README example)
        assertEquals(1170.0, bonus, 0.01);
    }

    @Test
    void testMinBoundary() {
        // Employee with attributes that would result in below minimum bonus
        // - 0 years at company
        // - 0 projects
        // - Not a team leader
        // - 11 days absence (no bonus, no penalty)
        // - Low performance (20)
        Employee minEmployee = new NormalEmployee("MinCase", 0, 20, 0, 11);
        double bonus = calculator.calculate(minEmployee);

        // Base 300 * 1.07 = 321 -> Below minimum, should be 500
        assertEquals(500.0, bonus, 0.01);
    }

    @Test
    void testMaxBoundary() {
        // Employee with attributes that would result in above maximum bonus
        // - 30 years at company
        // - 20 projects
        // - Team leader
        // - 0 days absence (max absence bonus)
        // - High performance (90)
        Employee maxEmployee = new TeamLeader("MaxCase", 30, 90, 20, 0);
        double bonus = calculator.calculate(maxEmployee);

        // Expected to be capped at maximum 2000.0
        assertEquals(2000.0, bonus, 0.01);
    }

    @Test
    void testAllBonusComponentsInteraction() {
        // Employee with moderate values to test all components working together
        // - 8 years (one 5-year interval plus remainder)
        // - 3 projects
        // - Team leader
        // - 5 days absence (second tier absence bonus)
        // - Medium performance (45)
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

    @Test
    void testCustomBonusChain() {
        // Simple test for custom bonus chain constructor
        Bonus customChain = new BaseBonus();
        customChain = new PerformanceMultiplier(customChain);

        BonusCalculator customCalculator = new BonusCalculator(customChain);

        Employee employee = new NormalEmployee("Custom", 0, 80, 0, 0);
        double bonus = customCalculator.calculate(employee);

        // Base 300 * 1.17 (high performance) = 351
        assertEquals(351.0, bonus, 0.01);
    }
}