package de.szut;

public class LowAbsenceBonus extends BonusDecorator {
    private int firstThreshold;
    private int secondThreshold;
    private double firstLevelBonus;
    private double secondLevelBonus;
    private Bonus decoratedBonus;

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
        return 0;
    }
}
