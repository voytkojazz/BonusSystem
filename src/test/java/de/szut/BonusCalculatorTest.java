package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the BonusCalculator.
 */
public class BonusCalculatorTest {

    private BonusCalculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new BonusCalculator();
    }

    @Test
    void testEmployeeA() {
        // Example from README: Employee A
        // - 6 years in company
        // - 2 projects
        // - Not a team leader
        // - 2 absence days
        // - Medium performance score
        Employee employeeA = new NormalEmployee("A", 6, 50, 2, 2);
        double bonus = calculator.calculate(employeeA);

        // Calculation breakdown:
        // - Base: 300
        // - Seniority (6 years): 300 + (6/5 * 150) = 300 + 150 = 450
        // - Projects (2): 450 + (2 * 50) = 450 + 100 = 550
        // - Team leader: Not applicable
        // - Low absence (2 days <= 3): 550 + 200 = 750
        // - Performance multiplier (medium): 750 * 1.12 = 840
        // This is between min (500) and max (2000), so should be 840
        assertEquals(840.0, bonus, 0.01);
    }

    @Test
    void testEmployeeB() {
        // Example from README: Employee B
        // - 12 years in company
        // - 5 projects
        // - Team leader
        // - 16 absence days
        // - High performance score
        Employee employeeB = new TeamLeader("B", 12, 80, 5, 16);
        double bonus = calculator.calculate(employeeB);

        // Calculation breakdown:
        // - Base: 300
        // - Seniority (12 years): 300 + (12/5 * 150) = 300 + 300 = 600
        // - Projects (5): 600 + (5 * 50) = 600 + 250 = 850
        // - Team leader: 850 + 200 = 1050
        // - High absence penalty (16 days >= 15): 1050 - 50 = 1000
        // - Performance multiplier (high): 1000 * 1.17 = 1170
        // This is between min (500) and max (2000), so should be 1170
        assertEquals(1170.0, bonus, 0.01);
    }

    @Test
    void testBaseBonus() {
        // Employee with no special attributes
        Employee employee = new NormalEmployee("John", 0, 20, 0, 20);
        double bonus = calculator.calculate(employee);

        // Base 300 * 1.07 (low performance) = 321, but no other bonuses
        // Since this is below min (500), it should be set to 500
        assertEquals(500.0, bonus, 0.01);
    }

    @Test
    void testSeniorityBonus() {
        // Testing seniority bonus (every 5 years gets 150 euros)

        // 4 years (no complete interval)
        Employee fourYears = new NormalEmployee("Four", 4, 50, 0, 5);
        double fourYearsBonus = calculator.calculate(fourYears);
        // Base 300 * 1.12 = 336, no seniority bonus yet
        // Min is 500, so should be 500
        assertEquals(500.0, fourYearsBonus, 0.01);

        // 5 years (exactly one interval)
        Employee fiveYears = new NormalEmployee("Five", 5, 50, 0, 5);
        double fiveYearsBonus = calculator.calculate(fiveYears);
        // Base 300 + 150 (seniority) = 450 * 1.12 = 504
        assertEquals(504.0, fiveYearsBonus, 0.01);

        // 11 years (two intervals)
        Employee elevenYears = new NormalEmployee("Eleven", 11, 50, 0, 5);
        double elevenYearsBonus = calculator.calculate(elevenYears);
        // Base 300 + 300 (seniority) = 600 * 1.12 = 672
        assertEquals(672.0, elevenYearsBonus, 0.01);
    }

    @Test
    void testTeamLeaderBonus() {
        // Team leader with no other bonuses
        Employee teamLeader = new TeamLeader("Leader", 2, 30, 0, 5);
        double leaderBonus = calculator.calculate(teamLeader);

        // Base 300 + 200 (team leader) = 500 * 1.07 = 535
        assertEquals(535.0, leaderBonus, 0.01);

        // Non-team leader for comparison
        Employee nonLeader = new NormalEmployee("NonLeader", 2, 30, 0, 5);
        double nonLeaderBonus = calculator.calculate(nonLeader);

        // Base 300 * 1.07 = 321, below min, so 500
        assertEquals(500.0, nonLeaderBonus, 0.01);
    }

    @Test
    void testAbsenceBonusAndPenalty() {
        // Testing low absence bonuses

        // 2 days (first tier: ≤ 3 days, +200 euros)
        Employee lowAbsence1 = new NormalEmployee("Low1", 0, 50, 0, 2);
        double lowBonus1 = calculator.calculate(lowAbsence1);
        // Base 300 + 200 (low absence) = 500 * 1.12 = 560
        assertEquals(560.0, lowBonus1, 0.01);

        // 7 days (second tier: ≤ 10 days, +50 euros)
        Employee lowAbsence2 = new NormalEmployee("Low2", 0, 50, 0, 7);
        double lowBonus2 = calculator.calculate(lowAbsence2);
        // Base 300 + 50 (low absence tier 2) = 350 * 1.12 = 392, below min so 500
        assertEquals(500.0, lowBonus2, 0.01);

        // Testing high absence penalties

        // 17 days (first tier: ≥ 15 days, -50 euros)
        Employee highAbsence1 = new NormalEmployee("High1", 10, 50, 0, 17);
        double highPenalty1 = calculator.calculate(highAbsence1);
        // Base 300 + 300 (seniority) = 600 - 50 (absence penalty) = 550 * 1.12 = 616
        assertEquals(616.0, highPenalty1, 0.01);

        // 30 days (second tier: ≥ 25 days, -100 euros)
        Employee highAbsence2 = new NormalEmployee("High2", 10, 50, 0, 30);
        double highPenalty2 = calculator.calculate(highAbsence2);
        // Base 300 + 300 (seniority) = 600 - 100 (absence penalty) = 500 * 1.12 = 560
        assertEquals(560.0, highPenalty2, 0.01);
    }

    @Test
    void testPerformanceMultiplier() {
        // Low performance (< 33)
        Employee lowPerformance = new NormalEmployee("LowPerf", 0, 20, 0, 4);
        double lowPerfBonus = calculator.calculate(lowPerformance);
        // Base 300 * 1.07 = 321, below min so 500
        assertEquals(500.0, lowPerfBonus, 0.01);

        // Medium performance (33-66)
        Employee medPerformance = new NormalEmployee("MedPerf", 0, 50, 5, 4);
        double medPerfBonus = calculator.calculate(medPerformance);
        // Base 300 + 250 (projects) = 550 * 1.12 = 616
        assertEquals(616.0, medPerfBonus, 0.01);

        // High performance (> 66)
        Employee highPerformance = new NormalEmployee("HighPerf", 0, 80, 5, 4);
        double highPerfBonus = calculator.calculate(highPerformance);
        // Base 300 + 250 (projects) = 550 * 1.17 = 643.5
        assertEquals(643.5, highPerfBonus, 0.01);
    }

    @Test
    void testMinMaxBoundaries() {
        // Test max boundary
        Employee maxEmployee = new TeamLeader("Max", 30, 90, 15, 0);
        double maxBonus = calculator.calculate(maxEmployee);

        // This should exceed max bonus (2000) and be capped
        assertEquals(2000.0, maxBonus, 0.01);

        // Test min boundary
        Employee minEmployee = new NormalEmployee("Min", 0, 10, 0, 30);
        double minBonus = calculator.calculate(minEmployee);

        // This should be below min bonus (500) and be raised
        assertEquals(500.0, minBonus, 0.01);
    }

    @Test
    void testCustomBonusChain() {
        // Create a simple custom bonus chain for testing
        Bonus customChain = new BaseBonus();
        customChain = new PerformanceMultiplier(customChain);
        customChain = new MaxMinBonus(customChain, 100, 1000); // Custom min/max

        BonusCalculator customCalculator = new BonusCalculator(customChain);

        Employee employee = new NormalEmployee("Custom", 5, 80, 3, 0);
        double bonus = customCalculator.calculate(employee);

        // Base 300 * 1.17 (high performance) = 351
        // This is between custom min (100) and max (1000), so should be 351
        assertEquals(351.0, bonus, 0.01);
    }
}