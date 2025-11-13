package com.internshipquest.model.activity;

import java.util.Random;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class Rest extends AActivity {
    // name, duration, costEnergy, costMoney
    public Rest() {
        super("Take a short nap.", 1, 0,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("snore", 0.4f); // !!!!!! need to change the sound
        double chance = Math.random();
        if (chance < 0.95) {
            int newEnergy = hero.getEnergy() + 5;
            message = "After a short one-hour nap, you feel great, your energy has increased to " + newEnergy + ".";
            hero.setEnergy(newEnergy);
            day.addHour(duration);
        }else { int newEnergy = hero.getEnergy() + 10;
            this.duration=3;
            message = "Oops,you oveslept, transforming your little nap into a long "+duration+"-hour sleep. But your energy has increased to " + newEnergy + ".";
            hero.setEnergy(newEnergy);
            day.addHour(duration);}

    }
}