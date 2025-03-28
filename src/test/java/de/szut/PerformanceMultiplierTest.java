package de.szut;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PerformanceMultiplierTest {

    @Mock
    Bonus decoratedBonus;

    @Test
    void testCalculateWhenLowFactor() {
        PerformanceMultiplier performanceMultiplier = new PerformanceMultiplier(decoratedBonus);
    }
}