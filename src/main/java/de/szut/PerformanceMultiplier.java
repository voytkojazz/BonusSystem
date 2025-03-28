package de.szut;

import java.math.BigDecimal;
import java.util.Optional;

public class PerformanceMultiplier extends BonusDecorator {

    protected static final int LOWER_BOUND = 33;
    protected static final int MEDIUM_BOUND = 66;

    protected static final int LOW_FACTOR = 7;
    protected static final int MEDIUM_FACTOR = 12;
    protected static final int HIGH_FACTOR = 17;

    public PerformanceMultiplier(Bonus decoratedBonus) {
        super(decoratedBonus);
    }

    @Override
    public double calculate(Employee employee) {
        var currentBonus = decoratedBonus.calculate(employee);
        return Optional.ofNullable(employee)
                .map(Employee::getPerformanceScore)
                .map(performanceScore -> calculatePerformanceBonus(currentBonus, performanceScore))
                .map(calculated -> new BigDecimal(String.format("%.2f", calculated)).doubleValue())
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
