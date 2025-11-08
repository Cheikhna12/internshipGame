package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.AHero;

public class DeadLifts extends AActivity {
    // name, duration, cost
    public DeadLifts() {
        super("Do some Deadlifts", 1, 10);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        int newEndurance = hero.getEndurance() + 5;
        int newEnergy = hero.getEnergy() - energyUse;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);
        day.addHour(duration);

        message = "After " + duration + " hour(s) of deadlifts, your endurance increased to "
                + newEndurance + " and energy decreased to " + newEnergy;
    }
}