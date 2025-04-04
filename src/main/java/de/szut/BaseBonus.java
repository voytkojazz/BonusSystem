package de.szut;

/**
 * Represents the base bonus that every employee receives.
 * According to the bonus system specifications, this is 300 euros.
 */
public class BaseBonus implements Bonus {

    private static final double BASE_BONUS_AMOUNT = 300.0;

    @Override
    public double calculate(Employee employee) {
        return BASE_BONUS_AMOUNT;
    }
}