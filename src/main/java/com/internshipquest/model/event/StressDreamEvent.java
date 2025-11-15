package com.internshipquest.model.event;

import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.ALieuVisitable;
import com.internshipquest.model.Day;

public class StressDreamEvent extends AEvent {


    public StressDreamEvent(AHero hero) {
        super("You have a terrible nightmare: you go to your internship interview without your trousers... your salary increases by 5.");
    }

    @Override
    public void applyEffect(AHero hero, Day day) {
        hero.setStress(hero.getStress() + 5);
    }
}