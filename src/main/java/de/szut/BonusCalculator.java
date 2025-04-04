package de.szut;

/**
 * Handles the construction and calculation of employee bonuses.
 * This class implements the Bonus interface and sets up the entire
 * bonus calculation chain using the Decorator pattern.
 */
public class BonusCalculator implements Bonus {

    private final Bonus bonusChain;

    /**
     * Constructs a new BonusCalculator with default configuration based on the README.
     * Sets up the entire chain of bonus decorators with values specified in the requirements.
     */
    public BonusCalculator() {
        // Start with the base bonus - 300 euros as per README
        Bonus baseBonus = new BaseBonus();

        Bonus seniorityBonus = new SeniorityBonus(baseBonus, 5, 150.0);

        Bonus projectBonus = new ProjectCompletionBonus(seniorityBonus, 50.0);

        Bonus leaderBonus = new TeamLeaderBonus(projectBonus, 200.0);

        Bonus lowAbsenceBonus = new LowAbsenceBonus(leaderBonus, 3, 10, 200.0, 50.0);

        Bonus highAbsence = new HighAbsencePenalty(lowAbsenceBonus, 15, 25, 50.0, 100.0);

        Bonus performanceBonus = new PerformanceMultiplier(highAbsence);
        Bonus maxMinBonus = new MaxMinBonus(performanceBonus);

        this.bonusChain = maxMinBonus;

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