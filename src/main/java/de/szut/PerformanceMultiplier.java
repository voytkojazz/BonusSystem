package de.szut;

import java.util.Optional;

public class PerformanceMultiplier extends BonusDecorator {

    protected static final int LOWER_BOUND = 33;
    protected static final int MEDIUM_BOUND = 66;

    private static final int LOW_FACTOR = 7;
    private static final int MEDIUM_FACTOR = 12;
    private static final int HIGH_FACTOR = 17;

    public PerformanceMultiplier(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculate(Employee employee) {
        var currentBonus = decoratedBonus.calculate(employee);
        return Optional.of(employee.getPerformanceScore())
                .map(performanceScore -> calculatePerformanceBonus(currentBonus, performanceScore))
                .orElse(currentBonus);
    }

    private static Double calculatePerformanceBonus(double currentBonus, Integer performanceScore) {
        if (performanceScore <= 33) {
            return currentBonus * ((100 + (double) LOW_FACTOR) / 100);
        } else if (performanceScore <= 66) {
            return currentBonus * ((100 + (double) MEDIUM_FACTOR) / 100);
        } else {
            return currentBonus * ((100 + (double) HIGH_FACTOR) / 100);
        }
    }
}
