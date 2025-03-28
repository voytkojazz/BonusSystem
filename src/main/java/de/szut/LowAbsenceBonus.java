package de.szut;

public class LowAbsenceBonus extends BonusDecorator {
    private final int firstThreshold;
    private final int secondThreshold;
    private final double firstLevelBonus;
    private final double secondLevelBonus;
    private final Bonus decoratedBonus;

    public LowAbsenceBonus(Bonus decoratedBonus, int firstThreshold, int secondThreshold, double firstLevelBonus, double secondLevelBonus) {
        super(decoratedBonus);
        this.decoratedBonus = decoratedBonus;
        this.firstThreshold = firstThreshold;
        this.secondThreshold = secondThreshold;
        this.firstLevelBonus = firstLevelBonus;
        this.secondLevelBonus = secondLevelBonus;
    }


    @Override
    public double calculate(Employee employee) {
        var currentBonus = decoratedBonus.calculate(employee);
        if (employee.getAbsenceDays() <= this.firstThreshold) {
            return currentBonus + this.firstLevelBonus;
        }
        if (employee.getAbsenceDays() <= this.secondThreshold) {
            return currentBonus + this.secondLevelBonus;
        }

        return currentBonus;
    }
}
