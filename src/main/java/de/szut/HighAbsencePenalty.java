package de.szut;

public class HighAbsencePenalty extends BonusDecorator {
        private int firstThreshold;
        private int secondThreshold;
        private double firstLevelPenaltyFactor;
        private double secondLevelPenaltyFactor;

        public HighAbsencePenalty(Bonus decoratedBonus, int firstThreshold, int secondThreshold,
                                  double firstLevelPenaltyFactor, double secondLevelPenaltyFactor) {
                super(decoratedBonus);
                this.firstThreshold = firstThreshold;
                this.secondThreshold = secondThreshold;
                this.firstLevelPenaltyFactor = firstLevelPenaltyFactor;
                this.secondLevelPenaltyFactor = secondLevelPenaltyFactor;
        }

        @Override
        public double calculate(Employee employee) {
                double currentBonus = decoratedBonus.calculate(employee);
                return 0;
        }
}
