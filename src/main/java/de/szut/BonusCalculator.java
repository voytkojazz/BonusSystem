package de.szut;

/**
 * Handles the construction and calculation of employee bonuses.
 * This class implements the Bonus interface and sets up the entire
 * bonus calculation chain using the Decorator pattern.
 */
public class BonusCalculator implements Bonus {

    private final Bonus bonusChain;

    /**
     * Constructs a new BonusCalculator with default configuration.
     * Sets up the entire chain of bonus decorators.
     */
    public BonusCalculator() {
        // Start with the base bonus
        Bonus baseBonus = new BaseBonus();

        // Add performance multiplier
        Bonus performanceBonus = new PerformanceMultiplier(baseBonus);

        // Add seniority bonus (150 euros for every 5 years)
        Bonus seniorityBonus = new SeniorityBonus(performanceBonus, 5, 150.0);

        // Add project completion bonus (50 euros per project)
        Bonus projectBonus = new ProjectCompletionBonus(seniorityBonus, 50.0);

        // Add team leader bonus (200 euros flat)
        Bonus leaderBonus = new TeamLeaderBonus(projectBonus, 200.0);

        // Add absence bonuses and penalties
        // Low absence bonus: <= 3 days: +200 euros, <= 10 days: +50 euros
        Bonus lowAbsenceBonus = new LowAbsenceBonus(leaderBonus, 3, 10, 200.0, 50.0);

        // High absence penalty: >= 15 days: -50 euros, >= 25 days: -100 euros
        Bonus highAbsencePenalty = new HighAbsencePenalty(lowAbsenceBonus, 15, 25, 50.0, 100.0);

        // Finally, enforce the min/max bounds from README (500 to 2000 euros)
        bonusChain = new MaxMinBonus(highAbsencePenalty, 500.0, 2000.0);
    }

    /**
     * Constructs a BonusCalculator with custom configuration.
     * This constructor allows for custom configuration of the bonus chain.
     *
     * @param customBonusChain The custom bonus chain to use
     */
    public BonusCalculator(Bonus customBonusChain) {
        this.bonusChain = customBonusChain;
    }

    /**
     * Calculates the bonus for the given employee.
     *
     * @param employee The employee to calculate the bonus for
     * @return The calculated bonus amount
     */
    @Override
    public double calculate(Employee employee) {
        return bonusChain.calculate(employee);
    }
}