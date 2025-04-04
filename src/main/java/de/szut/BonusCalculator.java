package de.szut;

/**
 * Main class for calculating an employee's total bonus.
 * Combines all bonus decorators according to the specified bonus system.
 */
public class BonusCalculator {

    // Base bonus amount
    protected static final double BASE_BONUS = 300.0;

    // Seniority bonus constants
    protected static final int SENIORITY_INTERVAL_YEARS = 3;
    protected static final double SENIORITY_BONUS_PER_INTERVAL = 150.0;

    // Project completion bonus constant
    protected static final double PROJECT_BONUS_PER_PROJECT = 50.0;

    // Team leader bonus constant
    protected static final double TEAM_LEADER_BONUS = 200.0;

    // Low absence bonus constants
    protected static final int LOW_ABSENCE_FIRST_THRESHOLD = 3;
    protected static final int LOW_ABSENCE_SECOND_THRESHOLD = 10;
    protected static final double LOW_ABSENCE_FIRST_BONUS = 200.0;
    protected static final double LOW_ABSENCE_SECOND_BONUS = 50.0;

    // High absence penalty constants
    protected static final int HIGH_ABSENCE_FIRST_THRESHOLD = 15;
    protected static final int HIGH_ABSENCE_SECOND_THRESHOLD = 25;
    protected static final double HIGH_ABSENCE_FIRST_PENALTY = 50.0;
    protected static final double HIGH_ABSENCE_SECOND_PENALTY = 100.0;

    // Bonus limits constants
    protected static final double MIN_BONUS = 500.0;
    protected static final double MAX_BONUS = 2000.0;

    /**
     * Calculates the total bonus for an employee based on all applicable bonus rules.
     *
     * @param employee The employee for whom to calculate the bonus
     * @return The total bonus amount
     */
    public double calculate(Employee employee) {
        if (employee == null) {
            return 0.0;
        }

        // Start with the base bonus
        Bonus bonus = new BaseBonus();

        // Add seniority bonus
        bonus = new SeniorityBonus(bonus, SENIORITY_INTERVAL_YEARS, SENIORITY_BONUS_PER_INTERVAL);

        // Add project completion bonus
        bonus = new ProjectCompletionBonus(bonus, PROJECT_BONUS_PER_PROJECT);

        // Add team leader bonus
        bonus = new TeamLeaderBonus(bonus, TEAM_LEADER_BONUS);

        // Add low absence bonus
        bonus = new LowAbsenceBonus(bonus,
                LOW_ABSENCE_FIRST_THRESHOLD,
                LOW_ABSENCE_SECOND_THRESHOLD,
                LOW_ABSENCE_FIRST_BONUS,
                LOW_ABSENCE_SECOND_BONUS);

        // Apply high absence penalty
        bonus = new HighAbsencePenalty(bonus,
                HIGH_ABSENCE_FIRST_THRESHOLD,
                HIGH_ABSENCE_SECOND_THRESHOLD,
                HIGH_ABSENCE_FIRST_PENALTY,
                HIGH_ABSENCE_SECOND_PENALTY);

        // Apply performance multiplier
        bonus = new PerformanceMultiplier(bonus);

        // Apply max/min limits
        bonus = new MaxMinBonus(bonus, MIN_BONUS, MAX_BONUS);

        // Calculate the final bonus
        return bonus.calculate(employee);
    }
}