package de.szut;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeamLeaderBonusTest {

    @Mock
    Bonus decoratedBonus;

    @Mock
    Employee employee;

    TeamLeaderBonus teamLeaderBonus;

    @BeforeEach
    void beforeEach() {
        when(decoratedBonus.calculate(employee)).thenReturn(150.0);
        this.teamLeaderBonus = new TeamLeaderBonus(decoratedBonus, 150);
    }

    @Test
    void calculateBonusAfterOneYear() {
        when(employee.getYearsAtCompany()).thenReturn(1);

        double calculate = teamLeaderBonus.calculate(employee);

        assertThat(calculate).isZero();

    }

    @Test
    void calculateBonusAfterTwoYears() {
        when(employee.getYearsAtCompany()).thenReturn(2);

        double calculate = teamLeaderBonus.calculate(employee);

        assertThat(calculate).isZero();

    }

    @Test
    void calculateBonusAfterThreeYears() {
        when(employee.getYearsAtCompany()).thenReturn(3);

        double calculate = teamLeaderBonus.calculate(employee);

        assertThat(calculate).isEqualTo(150);

    }

    @Test
    void calculateBonusAfterFourYears() {
        when(employee.getYearsAtCompany()).thenReturn(4);

        double calculate = teamLeaderBonus.calculate(employee);

        assertThat(calculate).isEqualTo(150);

    }

    @Test
    void calculateBonusAfterSixYears() {
        when(employee.getYearsAtCompany()).thenReturn(6);

        double calculate = teamLeaderBonus.calculate(employee);

        assertThat(calculate).isEqualTo(300);

    }

    @Test
    void calculateBonusAfterEveryThreeYears() {
        int initialBonus = 150;

        for (int years = 3; years <= 12; years += 3) {
            when(employee.getYearsAtCompany()).thenReturn(years);

            double calculate = teamLeaderBonus.calculate(employee);

            assertThat(calculate).isEqualTo(initialBonus * ((double) years / 3));
        }
    }
}
