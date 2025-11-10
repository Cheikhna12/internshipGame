package com.internshipquest.model.activity;

import java.util.Random;

import com.internshipquest.model.Day;
import com.internshipquest.model.AHero;
import com.internshipquest.utils.SoundManager;

public class FindClover extends AActivity {
    // name, duration, costEnergy, costMoney
    public FindClover() {
        super("Look for a four-leaf clover", 1, 5,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("pushup",0.4f); // !!!!! son à changer
        double chance = Math.random();
        if (chance < 0.6) {
            message = "After " + duration + " hour of fruitless searching, you give up. No lucky charm for you.";

        } else if (chance < 0.9 && chance >=0.6) {
            hero.setMoney(hero.getMoney() + 10);
            message = "After " + duration + " hour(s) of searching, After an hour of searching, you only find a lost 10 euro note. 'When life gives you lemons, make lemonade.' as your mother used to say. You haven't completely wasted your time.";
        } else {
            hero.setLuck(hero.getLuck() + 15);
            message = "After " + duration + " hour(s) of searching, a ray of light catches your eye. It illuminates a magnificent four-leaf clover. As soon as you touch it, you feel better and, above all, luckier (+15).";
        }

        day.addHour(duration);

    }
}