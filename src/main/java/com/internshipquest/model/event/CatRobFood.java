package com.internshipquest.model.event;

import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.Day;


public class CatRobFood extends AEvent {


    public CatRobFood(AHero hero) {
        super(hero.getNbFood() > 0
                ? "Your cat steals a meal from the fridge."
                : "Hearing a noise, you get up to find your cat sadly staring at the empty fridge.");
    }

    @Override
    public void applyEffect(AHero hero,Day day) {

        if (hero.getNbFood() > 0) {
            hero.setNbFood(hero.getNbFood() - 1);
        }
    }
}