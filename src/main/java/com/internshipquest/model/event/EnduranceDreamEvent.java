package com.internshipquest.model.event;

import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.ALieuVisitable;
import com.internshipquest.model.Day;

public class EnduranceDreamEvent extends AEvent {

    private int bonus;

    public EnduranceDreamEvent(AHero hero, int bonus) {
        super("You trained in your dreams and gained " + bonus + " endurance!");
        this.bonus = bonus;
    }

    @Override
    public void applyEffect(AHero hero, Day day) {
        hero.setEndurance(hero.getEndurance() + bonus);
    }
}