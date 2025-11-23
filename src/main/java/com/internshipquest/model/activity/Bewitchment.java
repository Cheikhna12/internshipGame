package com.internshipquest.model.activity;

import java.util.Random;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class Bewitchment extends AActivity {
    // name, duration, costEnergy, costMoney
    public Bewitchment() {
        super("Buy a spell to gain luck", 2, 15, 100);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        double chance = Math.random();
        int luck= hero.getLuck();
        if (chance+ luck/1000 < 0.2) {
            SoundManager.playSound("SpellReverse",0.5f);
            hero.setMoney(hero.getMoney() - cost);
            hero.setLuck(hero.getLuck()-10);
            message = "After " + duration + " hour of ritual, you notice that the sorcerer is holding his spellbook upside down.\n It's too late to escape, misfortune strikes.";

        } else if (chance +luck/1000 < 0.7 && chance+luck /1000 >=0.2) {
            SoundManager.playSound("SpellCough",0.5f);
            hero.setMoney(hero.getMoney() - cost);
            message = "After " + duration + " hour of ritual, you sneeze...\n interrupting the sorcerer at the critical moment. The spell fails.";
        } else {
            SoundManager.playSound("SpellWork",0.5f);
            hero.setMoney(hero.getMoney() - cost);
            hero.setLuck(hero.getLuck() + 25);
            message = "After " + duration + " hour(s) of ritual, the sorcerer finishes casting his spell,\n you feel your Karma improving.";
        }
        int newEnergy = hero.getEnergy() - energyUse;
        hero.setEnergy(newEnergy);
        day.addHour(duration);
        hero.hasBeenBewitched = true;

    }
}