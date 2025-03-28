package de.szut;

import java.util.Optional;

public class ProjectCompletionBonus extends BonusDecorator {

    private final double bonusPerProject;

    public ProjectCompletionBonus(Bonus decoratorBonus, double bonusPerProject){
        super(decoratorBonus);

        this.bonusPerProject = bonusPerProject;
    }
    @Override
    public double calculate(Employee employee) {
        double currentBonus = decoratedBonus.calculate(employee);
        return Optional.ofNullable(employee)
                .map(Employee::getCompletedProjects)
                .map(completedProject -> completedProject * 50)
                .map(calculated -> calculated + currentBonus)
                .orElse(currentBonus);
    }
}
