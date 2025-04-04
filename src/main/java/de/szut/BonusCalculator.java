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

        // Add performance multiplier (7% for low, 12% for medium, 17% for high performance)
        Bonus performanceBonus = new PerformanceMultiplier(baseBonus);

        // Add seniority bonus (150 euros for every 5 years)
        // "Ein Interval, also eine Höherstufung des Seniority Levels erfolgt nach 3 Jahren.
        // Dabei wird jede 5 Jahre ein 150 € Euro bonus kalkuliert."
        Bonus seniorityBonus = new SeniorityBonus(performanceBonus, 5, 150.0);

        // Add project completion bonus (50 euros per project)
        // "Dieser Bonus beträgt 50 € pro kompletten Projekt."
        Bonus projectBonus = new ProjectCompletionBonus(seniorityBonus, 50.0);

        // Add team leader bonus (200 euros flat)
        // "Teamleader erhalten einen Bonus von 200 €."
        Bonus leaderBonus = new TeamLeaderBonus(projectBonus, 200.0);

        // Add absence bonuses and penalties
        // Low absence bonus:
        // "Die erste Abstufung der geringen Fehlzeiten wird bei 3 angesetzt.
        // Das heißt bei 3 oder weniger Fehltagen gibt es einen 200 € Bonus.
        // Die zweite Abstufung wird bei 10 angesetzt, wobei es einen 50 € Bonus gibt."
        Bonus lowAbsenceBonus = new LowAbsenceBonus(leaderBonus, 3, 10, 200.0, 50.0);

        // High absence penalty:
        // "Die erste Abstufung sind hier 15 Fehltage, bei den es einen Abzug von 50 € gibt.
        // Die zweite Abstufung, ab 25 Fehltage, bei den es einen Abzug von 100 € gibt."
        Bonus highAbsencePenalty = new HighAbsencePenalty(lowAbsenceBonus, 15, 25, 50.0, 100.0);

        // Finally, enforce the min/max bounds from README
        // "Der Höchstbonus beträgt 2000 € und der Mindestbonus 500 €."
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