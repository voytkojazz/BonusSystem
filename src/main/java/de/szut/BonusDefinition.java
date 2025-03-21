package de.szut;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BonusDefinition {

    MAX_BONUS(10), MIN_BONUS(1);
    private final double amount;

}
