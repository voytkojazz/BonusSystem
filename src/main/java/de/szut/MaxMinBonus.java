package de.szut;

/**
 * Represents a decorator that enforces the maximum and minimum bonus limits.
 * Uses the BonusDefinition enum for configuration.
 */
public class MaxMinBonus extends BonusDecorator {

    private final double maxBonus;
    private final double minBonus;

    /**
     * Creates a new MaxMinBonus decorator.
     *
     * @param decoratedBonus The bonus to decorate
     */
    public MaxMinBonus(Bonus decoratedBonus) {
        super(decoratedBonus);
        this.minBonus = BonusDefinition.MIN_BONUS.getAmount();
        this.maxBonus = BonusDefinition.MAX_BONUS.getAmount();
    }

    /**
     * Creates a new MaxMinBonus decorator with custom min/max values.
     *
     * @param decoratedBonus The bonus to decorate
     * @param minBonus The minimum bonus amount
     * @param maxBonus The maximum bonus amount
     */
    public MaxMinBonus(Bonus decoratedBonus, double minBonus, double maxBonus) {
        super(decoratedBonus);
        this.minBonus = minBonus;
        this.maxBonus = maxBonus;
    }

    @Override
    public double calculate(Employee employee) {
        double currentBonus = decoratedBonus.calculate(employee);

        if (currentBonus > maxBonus) {
            return maxBonus;
        }

        if (currentBonus < minBonus) {
            return minBonus;
        }

        return currentBonus;
    }
}