package de.szut;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class BaseBonusTest {

    @Mock
    Employee anotherEmployee;
    @Mock
    private Employee employee;
    private BaseBonus underTest;

    @BeforeEach
    void setUp() {
        underTest = new BaseBonus();
    }

    @Test
    void shouldReturnBaseBonus() {
        // Act
        double calculated = underTest.calculate(employee);

        // Assert
        assertThat(calculated).isEqualTo(300.0);
    }

    @Test
    void shouldReturnBaseBonusForDifferentEmployees() {
        // Arrange

        // Act
        double calculatedForFirstEmployee = underTest.calculate(employee);
        double calculatedForSecondEmployee = underTest.calculate(anotherEmployee);

        // Assert
        assertThat(calculatedForFirstEmployee).isEqualTo(300.0);
        assertThat(calculatedForSecondEmployee).isEqualTo(300.0);
    }

    @Test
    void shouldHandleNullEmployee() {
        // Act
        double calculated = underTest.calculate(null);

        // Assert
        assertThat(calculated).isEqualTo(300.0);
    }

    @Test
    void shouldBeConsistentWithMultipleCalls() {
        // Act
        double firstCall = underTest.calculate(employee);
        double secondCall = underTest.calculate(employee);
        double thirdCall = underTest.calculate(employee);

        // Assert
        assertThat(firstCall).isEqualTo(300.0);
        assertThat(secondCall).isEqualTo(300.0);
        assertThat(thirdCall).isEqualTo(300.0);
    }
}