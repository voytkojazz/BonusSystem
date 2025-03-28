package de.szut;

public class SeniorityBonus extends BonusDecorator {

    private int intervalYears;
    private double amountPerInterval;

    public SeniorityBonus(Bonus decoratedBonus, int intervalYears, double amountPerInterval){
        super(decoratedBonus);

        this.intervalYears = intervalYears;
        this.amountPerInterval = amountPerInterval;
    }


    @Override
    public double calculate(Employee employee) {
        return 0;
    }
}
