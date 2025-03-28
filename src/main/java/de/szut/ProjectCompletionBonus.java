package de.szut;

public class ProjectCompletionBonus extends BonusDecorator{

    private double bonusPerProject;

    public ProjectCompletionBonus(Bonus decoratorBonus, double bonusPerProject){
        super(decoratorBonus);

        this.bonusPerProject = bonusPerProject;
    }
    @Override
    public double calculate(Employee employee) {
        return 0;
    }
}
