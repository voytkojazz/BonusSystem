package de.szut;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class BonusDecorator implements Bonus {

    protected final Bonus decoratedBonus;

}


