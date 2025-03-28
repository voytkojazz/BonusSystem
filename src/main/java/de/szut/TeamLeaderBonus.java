package de.szut;

public class TeamLeaderBonus extends BonusDecorator {

    private double leaderBonusAmount;

    public TeamLeaderBonus(Bonus decoratedBonus, double leaderBonusAmount) {
    super(decoratedBonus);
    this.leaderBonusAmount = leaderBonusAmount;

    }

    @Override
    public double calculate(Employee employee) {
        return 0;
    }
}
