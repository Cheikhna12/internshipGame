package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class EatFood extends AActivity {
    // name, duration, cost
    public EatFood() {
        super("Eat a meal from the fridge", 1, 0, 0);
    }

    @Override
    public void doIt(AHero hero, Day day) {

        if (hero.getSatiety() == 100) {
            message = "I'm not at Grandma's,\n no need to eat when I'm not hungry anymore";
        } else {
            if (hero.getNbFood() == 0) {
                message = "It's empty! I need to buy some food.";
            } else if (hero.getNbFood() == 1) {
                hero.setNbFood(0);
                hero.setSatiety(100);
                hero.setEnergy(hero.getEnergy() + 20);
                day.addHour(duration);
                message = "I'm full, but I need to buy food for tomorrow.";
            } else {
                hero.setNbFood(hero.getNbFood() - 1);
                hero.setSatiety(100);
                hero.setEnergy(hero.getEnergy() + 20);
                day.addHour(duration);
                message = "\n" +
                        "I ate well, that's enough for me today!!";
            }
        }
    }
}