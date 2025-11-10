package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class PushUps extends AActivity {
    // name, duration, costEnergy, costMoney
    public PushUps() {
        super("Do some push-Ups", 1, 5,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("pushup",0.4f);
        int newEndurance = hero.getEndurance() + 3;
        int newEnergy = hero.getEnergy() - energyUse;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);

        day.addHour(duration);

        message = "After " + duration + " hour of push-ups, your endurance increased to "
                + newEndurance + " and energy decreased to " + newEnergy;
    }
}