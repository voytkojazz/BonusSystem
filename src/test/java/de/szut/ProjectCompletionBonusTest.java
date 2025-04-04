package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProjectCompletionBonusTest {

    @Mock
    Bonus decoratedBonus;

    @Mock
    Employee employee;

    private ProjectCompletionBonus underTest;
    private final double bonusPerProject = 50.0;

    @BeforeEach
    void setUp() {
        underTest = new ProjectCompletionBonus(decoratedBonus, bonusPerProject);
    }

    @Test
    void shouldCalculateCorrectBonusForEmployeeWithProjects() {
        // Arrange
        double baseBonus = 300.0;
        int completedProjects = 2;

        when(decoratedBonus.calculate(employee)).thenReturn(baseBonus);
        when(employee.getCompletedProjects()).thenReturn(completedProjects);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        double expectedBonus = baseBonus + (completedProjects * bonusPerProject);
        assertThat(calculated).isEqualTo(expectedBonus);
    }

    @Test
    void shouldReturnOnlyDecoratedBonusWhenEmployeeHasNoProjects() {
        // Arrange
        double baseBonus = 300.0;
        int completedProjects = 0;

        when(decoratedBonus.calculate(employee)).thenReturn(baseBonus);
        when(employee.getCompletedProjects()).thenReturn(completedProjects);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(baseBonus);
    }

    @Test
    void shouldCalculateCorrectBonusForEmployeeWithManyProjects() {
        // Arrange
        double baseBonus = 500.0;
        int completedProjects = 10;

        when(decoratedBonus.calculate(employee)).thenReturn(baseBonus);
        when(employee.getCompletedProjects()).thenReturn(completedProjects);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        double expectedBonus = baseBonus + (completedProjects * bonusPerProject);
        assertThat(calculated).isEqualTo(expectedBonus);
    }

    @Test
    void shouldHandleNullEmployee() {
        // Arrange
        double baseBonus = 300.0;
        when(decoratedBonus.calculate(null)).thenReturn(baseBonus);

        // Act
        double calculated = underTest.calculate(null);

        // Assert
        assertThat(calculated).isEqualTo(baseBonus);
    }

    @Test
    void shouldWorkCorrectlyWithExampleEmployeeA() {
        // Arrange - Based on Example Employee A with 2 projects
        double baseBonus = 800.0; // Sum of other bonuses without project completion
        int completedProjects = 2;

        when(decoratedBonus.calculate(employee)).thenReturn(baseBonus);
        when(employee.getCompletedProjects()).thenReturn(completedProjects);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(900.0); // 800 + (2 * 50)
    }

    @Test
    void shouldWorkCorrectlyWithExampleEmployeeB() {
        // Arrange - Based on Example Employee B with 5 projects
        double baseBonus = 1050.0; // Sum of other bonuses without project completion
        int completedProjects = 5;

        when(decoratedBonus.calculate(employee)).thenReturn(baseBonus);
        when(employee.getCompletedProjects()).thenReturn(completedProjects);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(1300.0); // 1050 + (5 * 50)
    }
}