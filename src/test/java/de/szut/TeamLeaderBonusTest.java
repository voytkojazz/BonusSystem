package de.szut;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for the TeamLeaderBonus decorator.
 */
public class TeamLeaderBonusTest {

    /**
     * A simple stub implementation of the Bonus interface for testing.
     */
    private static class StubBonus implements Bonus {
        private final double returnValue;

        public StubBonus(double returnValue) {
            this.returnValue = returnValue;
        }

        @Override
        public double calculate(Employee employee) {
            return returnValue;
        }
    }

    @Test
    void testTeamLeaderGetsBonusAmount() {
        // Arrange
        double baseAmount = 1000.0;
        double leaderBonusAmount = 200.0;

        Bonus baseBonus = new StubBonus(baseAmount);
        TeamLeaderBonus teamLeaderBonus = new TeamLeaderBonus(baseBonus, leaderBonusAmount);

        Employee teamLeader = new TeamLeader("Leader", 5, 70, 3, 2);

        // Act
        double calculatedBonus = teamLeaderBonus.calculate(teamLeader);

        // Assert
        // Team leader should get the base amount + leader bonus amount
        assertEquals(baseAmount + leaderBonusAmount, calculatedBonus);
    }

    @Test
    void testNonTeamLeaderDoesNotGetBonus() {
        // Arrange
        double baseAmount = 800.0;
        double leaderBonusAmount = 200.0;

        Bonus baseBonus = new StubBonus(baseAmount);
        TeamLeaderBonus teamLeaderBonus = new TeamLeaderBonus(baseBonus, leaderBonusAmount);

        Employee nonLeader = new NormalEmployee("Regular", 5, 70, 3, 2);

        // Act
        double calculatedBonus = teamLeaderBonus.calculate(nonLeader);

        // Assert
        // Non-team leader should just get the base amount without the leader bonus
        assertEquals(baseAmount, calculatedBonus);
    }

    @Test
    void testMultipleDecoratedBonuses() {
        // Arrange
        Bonus baseBonus = new BaseBonus(); // 300 euros
        Bonus performanceBonus = new PerformanceMultiplier(baseBonus); // Multiplier based on performance
        TeamLeaderBonus teamLeaderBonus = new TeamLeaderBonus(performanceBonus, 200.0);

        // High performer team leader (performance score > 66)
        Employee highPerformingLeader = new TeamLeader("HighLeader", 0, 80, 0, 0);

        // Act
        double calculatedBonus = teamLeaderBonus.calculate(highPerformingLeader);

        // Assert
        // Base 300 * 1.17 (high performer) + 200 (leader bonus) = 351 + 200 = 551
        assertEquals(551.0, calculatedBonus, 0.01);
    }

    @Test
    void testZeroLeaderBonusAmount() {
        // Arrange
        double baseAmount = 500.0;
        double leaderBonusAmount = 0.0; // Zero bonus amount

        Bonus baseBonus = new StubBonus(baseAmount);
        TeamLeaderBonus teamLeaderBonus = new TeamLeaderBonus(baseBonus, leaderBonusAmount);

        Employee teamLeader = new TeamLeader("Leader", 5, 70, 3, 2);

        // Act
        double calculatedBonus = teamLeaderBonus.calculate(teamLeader);

        // Assert
        // Team leader should just get the base amount since leader bonus is zero
        assertEquals(baseAmount, calculatedBonus);
    }

    @Test
    void testNegativeLeaderBonusAmount() {
        // Arrange
        double baseAmount = 1000.0;
        double leaderBonusAmount = -200.0; // Negative bonus amount (penalty)

        Bonus baseBonus = new StubBonus(baseAmount);
        TeamLeaderBonus teamLeaderBonus = new TeamLeaderBonus(baseBonus, leaderBonusAmount);

        Employee teamLeader = new TeamLeader("Leader", 5, 70, 3, 2);

        // Act
        double calculatedBonus = teamLeaderBonus.calculate(teamLeader);

        // Assert
        // Team leader should get base amount minus the negative bonus amount
        assertEquals(baseAmount + leaderBonusAmount, calculatedBonus);
    }
}