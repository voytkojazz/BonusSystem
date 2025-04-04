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
        var currentBonus = this.decoratedBonus.calculate(employee);
        if (employee.getYearsAtCompany() == 0) {
            return currentBonus;
        }
        var intervals = employee.getYearsAtCompany() / this.intervalYears;

        return currentBonus + (intervals * amountPerInterval);
    }
}
