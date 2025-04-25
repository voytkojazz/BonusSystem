package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MaxMinBonusTest {

    @Mock
    private Bonus decoratedBonus;

    @Mock
    private Employee employee;

    private MaxMinBonus underTest;
    private final double minBonus = 500.0;
    private final double maxBonus = 2000.0;

    @BeforeEach
    void setUp() {
        underTest = new MaxMinBonus(decoratedBonus, minBonus, maxBonus);
    }

    @Test
    void shouldReturnUnchangedBonusWhenWithinLimits() {
        // Arrange
        double withinLimitsBonus = 1000.0;
        when(decoratedBonus.calculate(employee)).thenReturn(withinLimitsBonus);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(withinLimitsBonus);
    }

    @Test
    void shouldCapBonusAtMaximum() {
        // Arrange
        double aboveMaxBonus = 2500.0;
        when(decoratedBonus.calculate(employee)).thenReturn(aboveMaxBonus);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(maxBonus);
    }

    @Test
    void shouldEnsureMinimumBonus() {
        // Arrange
        double belowMinBonus = 300.0;
        when(decoratedBonus.calculate(employee)).thenReturn(belowMinBonus);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(minBonus);
    }

    @Test
    void shouldHandleExactlyMaximumBonus() {
        // Arrange
        when(decoratedBonus.calculate(employee)).thenReturn(maxBonus);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(maxBonus);
    }

    @Test
    void shouldHandleExactlyMinimumBonus() {
        // Arrange
        when(decoratedBonus.calculate(employee)).thenReturn(minBonus);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(minBonus);
    }

    @Test
    void shouldHandleNullEmployee() {
        // Arrange
        double baseBonus = 1000.0;
        when(decoratedBonus.calculate(null)).thenReturn(baseBonus);

        // Act
        double calculated = underTest.calculate(null);

        // Assert
        assertThat(calculated).isEqualTo(baseBonus);
    }

    @Test
    void shouldWorkWithCustomLimits() {
        // Arrange
        double customMin = 100.0;
        double customMax = 500.0;
        double belowCustomMin = 50.0;
        underTest = new MaxMinBonus(decoratedBonus, customMin, customMax);

        when(decoratedBonus.calculate(employee)).thenReturn(belowCustomMin);

        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(customMin);
    }
}