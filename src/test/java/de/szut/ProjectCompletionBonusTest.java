package de.szut;

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

    @Test
    void testCalculate() {
        var underTest = new ProjectCompletionBonus(decoratedBonus, 50);
        when(decoratedBonus.calculate(employee)).thenReturn(100.0);
        when(employee.getCompletedProjects()).thenReturn(2);

        double calculated = underTest.calculate(employee);

        assertThat(calculated).isEqualTo(200.0);
    }
}