package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SeniorityBonusTest {
    @Mock
    Bonus decoratedBonus;

    @Mock
    Employee employee;

    SeniorityBonus seniorityBonus;

    @BeforeEach
    void beforeEach() {
        when(decoratedBonus.calculate(employee)).thenReturn(300.0);
        this.seniorityBonus = new SeniorityBonus(decoratedBonus,3, 150.0);
    }

    @Test
    void testCalculateWithZeroYearBonus() {
        double calculate = seniorityBonus.calculate(employee);

        assertThat(calculate).isEqualTo(300);
    }

    @Test
    void testCalculateWithOneLevelBonus() {
        when(employee.getYearsAtCompany()).thenReturn(4);

        double calculate = seniorityBonus.calculate(employee);

        assertThat(calculate).isEqualTo(300 + 150);
    }

    @Test
    void testCalculateBonus20Years() {
        when(employee.getYearsAtCompany()).thenReturn(20);

        double calculate = seniorityBonus.calculate(employee);

        assertThat(calculate).isEqualTo(300 + 900);
    }
}
