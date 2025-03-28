package de.szut;

public class HighAbsencePenalty extends BonusDecorator {
        private final int firstThreshold;
        private final int secondThreshold;
        private final double firstLevelPenaltyFactor;
        private final double secondLevelPenaltyFactor;

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
                if (employee.getAbsenceDays() >= this.secondThreshold) {
                        return currentBonus - this.secondLevelPenaltyFactor;
                }
                if (employee.getAbsenceDays() >= this.firstThreshold) {
                        return currentBonus - this.firstLevelPenaltyFactor;
                }

                return currentBonus;
        }
}
