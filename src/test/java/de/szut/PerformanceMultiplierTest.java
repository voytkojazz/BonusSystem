package de.szut;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PerformanceMultiplierTest {

    @Mock
    Bonus decoratedBonus;

    @Mock
    Employee employee;

    @Test
    void testCalculateWhenLowFactor() {
        PerformanceMultiplier performanceMultiplier = new PerformanceMultiplier(decoratedBonus);
        when(decoratedBonus.calculate(employee)).thenReturn(100.0);
        when(employee.getPerformanceScore()).thenReturn(PerformanceMultiplier.LOWER_BOUND);

        double calculate = performanceMultiplier.calculate(employee);

        System.out.println(calculate);
        assertThat(calculate).isEqualTo(107.0);
    }
}