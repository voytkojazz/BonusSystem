package de.szut;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum BonusDefinition {

    MAX_BONUS(2000), MIN_BONUS(500);
    private final double amount;

}
