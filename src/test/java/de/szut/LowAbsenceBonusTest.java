package de.szut;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LowAbsenceBonusTest {
    @Mock
    Bonus decoratedBonus;

    @Mock
    Employee employee;

    LowAbsenceBonus lowAbsenceBonus;

    @BeforeEach
    void beforeEach() {
        when(decoratedBonus.calculate(employee)).thenReturn(300.0);
        this.lowAbsenceBonus = new LowAbsenceBonus(decoratedBonus, 3, 10, 200, 50);
    }


    @Test
    void calculateBonusWithLowThreshold() {
        when(employee.getAbsenceDays()).thenReturn(0);

        double calculate = lowAbsenceBonus.calculate(employee);

        assertThat(calculate).isEqualTo(500);
    }

    @Test
    void calculateBonusWithHighThreshold() {
        when(employee.getAbsenceDays()).thenReturn(6);

        double calculate = lowAbsenceBonus.calculate(employee);

        assertThat(calculate).isEqualTo(350);
    }

    @Test
    void calculateBonusWithHighThresholdExactDays() {
        when(employee.getAbsenceDays()).thenReturn(3);

        double calculate = lowAbsenceBonus.calculate(employee);

        assertThat(calculate).isEqualTo(500);
    }


    @Test
    void calculateBonusWithLowThresholdExactDays() {
        when(employee.getAbsenceDays()).thenReturn(10);

        double calculate = lowAbsenceBonus.calculate(employee);

        assertThat(calculate).isEqualTo(350);
    }
}
