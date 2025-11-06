package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.Hero;

public class PushUps extends AActivity {

    public PushUps() {
        super("Push-Ups", 1, 5);
    }

    @Override
    public void doIt(Hero hero, Day day) {
        int newEndurance = hero.getEndurance() + 3;
        int newEnergy = hero.getEnergy() - energyUse;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);

        day.addHour(duration);

        message = "After " + duration + " hour(s) of push-ups, your endurance increased to "
                + newEndurance + " and energy decreased to " + newEnergy;
    }
}