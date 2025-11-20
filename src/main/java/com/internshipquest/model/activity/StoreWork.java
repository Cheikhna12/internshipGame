package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class StoreWork extends AActivity {
    // name, duration, costEnergy, costMoney
    public StoreWork() {
        super("Do a day of temporary work", 8, 25,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("StoreWork",0.4f);
        int newEndurance = hero.getEndurance() + 3;
        int newSocial = hero.getSocial() + 3;
        int newEnergy = hero.getEnergy() - energyUse;
        int money = 50;
        hero.setEndurance(newEndurance);
        hero.setSocial(newSocial);
        hero.setEnergy(newEnergy);
        hero.setMoney(hero.getMoney()+money);

        day.addHour(duration);

        message = "After " + duration + " hour of handling goods and operating the cash register, \nyou receive your daily pay. 'Fortune always begins with a single coin'.";
        hero.hasCarrefoured = true;
    }
}