package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class DeadLifts extends AActivity {
    
    public DeadLifts() {
        super("Do some Deadlifts", 1, 15,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("deadlift", 0.4f);
        int newEndurance = hero.getEndurance() + 6;
        int newEnergy = hero.getEnergy() - energyUse;

        hero.setStress(hero.getStress()-1);
        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);
        day.addHour(duration);

        message = "After " + duration + " hour(s) of deadlifts, your endurance increased \nto "
                + newEndurance + " and energy decreased to " + newEnergy+".\nThis workout session helps you de-stress slightly.";

        hero.hasDeadLifted = true;
    }
}