package com.internshipquest.model.activity;

import java.util.Random;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class Bewitchment extends AActivity {
    // name, duration, costEnergy, costMoney
    public Bewitchment() {
        super("Ask for a spell to gain luck", 2, 15, 100);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("pushup",0.4f); // !!!!! son à changer
        double chance = Math.random();
        int luck= hero.getLuck();
        if (chance+ luck/1000 < 0.2) {
            hero.setMoney(hero.getMoney() - cost);
            hero.setLuck(hero.getLuck()-10);
            message = "After " + duration + " hour of ritual, you notice that the sorcerer is holding his grimoire upside down.\n Too late to escape, misfortune strikes.";

        } else if (chance +luck/1000 < 0.7 && chance+luck /1000 >=0.2) {
            hero.setMoney(hero.getMoney() - cost);
            message = "After " + duration + " hour of ritual, you sneeze...\n interrupting the sorcerer at the critical moment. The spell fails.";
        } else {
            hero.setMoney(hero.getMoney() - cost);
            hero.setLuck(hero.getLuck() + 25);
            message = "After " + duration + " hour(s) of ritual, the sorcerer finishes casting his spell,\n you feel your Karma improving.";
        }

        day.addHour(duration);

    }
}