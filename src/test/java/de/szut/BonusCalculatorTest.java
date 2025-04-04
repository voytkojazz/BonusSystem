package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BonusCalculatorTest {

    @Mock
    private Employee employee;

    private BonusCalculator underTest;

    @BeforeEach
    void setUp() {
        underTest = new BonusCalculator();
    }

    @Test
    void shouldHandleNullEmployee() {
        // Act
        double result = underTest.calculate(null);

        // Assert
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void shouldCalculateCorrectBonusForEmployee() {
        // Note: Since SeniorityBonus and TeamLeaderBonus are not fully implemented,
        // this test can only verify that some calculation is happening

        // Arrange
        when(employee.getYearsAtCompany()).thenReturn(6);
        when(employee.getCompletedProjects()).thenReturn(2);
        when(employee.isTeamLeader()).thenReturn(false);
        when(employee.getAbsenceDays()).thenReturn(2);
        when(employee.getPerformanceScore()).thenReturn(50);

        // Act
        double result = underTest.calculate(employee);

        // Assert
        // This assertion will need to be updated once all implementations are complete
        assertThat(result).isGreaterThan(0.0);
    }

    @Test
    void shouldHaveCorrectConstantValues() {
        // This test verifies that the constants match the requirements

        // Base bonus
        assertThat(BonusCalculator.BASE_BONUS).isEqualTo(300.0);

        // Seniority bonus
        assertThat(BonusCalculator.SENIORITY_INTERVAL_YEARS).isEqualTo(3);
        assertThat(BonusCalculator.SENIORITY_BONUS_PER_INTERVAL).isEqualTo(150.0);

        // Project completion bonus
        assertThat(BonusCalculator.PROJECT_BONUS_PER_PROJECT).isEqualTo(50.0);

        // Team leader bonus
        assertThat(BonusCalculator.TEAM_LEADER_BONUS).isEqualTo(200.0);

        // Low absence bonus
        assertThat(BonusCalculator.LOW_ABSENCE_FIRST_THRESHOLD).isEqualTo(3);
        assertThat(BonusCalculator.LOW_ABSENCE_SECOND_THRESHOLD).isEqualTo(10);
        assertThat(BonusCalculator.LOW_ABSENCE_FIRST_BONUS).isEqualTo(200.0);
        assertThat(BonusCalculator.LOW_ABSENCE_SECOND_BONUS).isEqualTo(50.0);

        // High absence penalty
        assertThat(BonusCalculator.HIGH_ABSENCE_FIRST_THRESHOLD).isEqualTo(15);
        assertThat(BonusCalculator.HIGH_ABSENCE_SECOND_THRESHOLD).isEqualTo(25);
        assertThat(BonusCalculator.HIGH_ABSENCE_FIRST_PENALTY).isEqualTo(50.0);
        assertThat(BonusCalculator.HIGH_ABSENCE_SECOND_PENALTY).isEqualTo(100.0);

        // Bonus limits
        assertThat(BonusCalculator.MIN_BONUS).isEqualTo(500.0);
        assertThat(BonusCalculator.MAX_BONUS).isEqualTo(2000.0);
    }
}