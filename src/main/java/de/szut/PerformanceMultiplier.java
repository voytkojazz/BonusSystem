package de.szut;

import java.util.Optional;

public class PerformanceMultiplier extends BonusDecorator {

    private static final int LOW_FACTOR = 1;
    private static final int MEDIUM_FACTOR = 2;
    private static final int HIGH_FACTOR = 3;

    public PerformanceMultiplier(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculate(Employee employee, double currentBonus) {
        double calculatedBonus =  Optional.of(employee.getPerformanceScore())
                .map(performanceScore -> calculatePerformanceBonus(currentBonus, performanceScore))
                .orElse(currentBonus);
        return decoratedBonus.calculate(employee, calculatedBonus);
    }

    private static Double calculatePerformanceBonus(double currentBonus, Integer performanceScore) {
        if (performanceScore < 33) {
            return currentBonus * ((100 - (double) LOW_FACTOR) / 100);
        } else if (performanceScore < 66) {
            return currentBonus * ((100 - (double) MEDIUM_FACTOR) / 100);
        } else {
            return currentBonus * ((100 - (double) HIGH_FACTOR) / 100);
        }
    }
}
