package com.internshipquest.model.activity;

import java.util.Random;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class FindClover extends AActivity {
    // name, duration, costEnergy, costMoney
    public FindClover() {
        super("Look for a four-leaf clover", 1, 5,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        double chance = Math.random();
        if (chance < 0.6) {
            SoundManager.playSound("cloverSearch",0.5f);
            message = "After " + duration + " hour of fruitless searching, you give up. No lucky charm for you.";

        } else if (chance < 0.9 && chance >=0.6) {
            SoundManager.playSound("cloverSearch",0.5f);
            hero.setMoney(hero.getMoney() + 10);
            message = "After " + duration + " hour(s) of searching, you only find a lost 10 euro note.\n 'When life gives you lemons, make lemonade.' as your mother used to say.\n You haven't completely wasted your time.";
        } else {
            SoundManager.playSound("cloverDiscover",0.5f);
            hero.setLuck(hero.getLuck() + 15);
            message = "After " + duration + " hour(s) of searching, a ray of light catches your eye.\n It illuminates a magnificent four-leaf clover.\n As soon as you touch it, you feel better\n and, above all, luckier (+15).";
        }
        int newEnergy = hero.getEnergy() - energyUse;
        hero.setEnergy(newEnergy);
        day.addHour(duration);

    }
}