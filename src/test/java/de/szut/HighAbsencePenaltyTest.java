package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HighAbsencePenaltyTest {
    @Mock
    Bonus decoratedBonus;

    @Mock
    Employee employee;

    HighAbsencePenalty highAbsencePenalty;

    @BeforeEach
    void beforeEach() {
        when(decoratedBonus.calculate(employee)).thenReturn(300.0);
        this.highAbsencePenalty = new HighAbsencePenalty(decoratedBonus, 15, 25, 50, 100);
    }

    @Test
    void calculateBonusWithLowThreshold() {
        when(employee.getAbsenceDays()).thenReturn(16);

        double calculate = highAbsencePenalty.calculate(employee);

        assertThat(calculate).isEqualTo(250);
    }

    @Test
    void calculateBonusWithHighThreshold() {
        when(employee.getAbsenceDays()).thenReturn(30);

        double calculate = highAbsencePenalty.calculate(employee);

        assertThat(calculate).isEqualTo(200);
    }

    @Test
    void calculateBonusWithHighThresholdExactDays() {
        when(employee.getAbsenceDays()).thenReturn(25);

        double calculate = highAbsencePenalty.calculate(employee);

        assertThat(calculate).isEqualTo(200);
    }


    @Test
    void calculateBonusWithLowThresholdExactDays() {
        when(employee.getAbsenceDays()).thenReturn(15);

        double calculate = highAbsencePenalty.calculate(employee);

        assertThat(calculate).isEqualTo(250);
    }
}
