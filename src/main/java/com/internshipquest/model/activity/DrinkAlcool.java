package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

import java.util.Random;

public class DrinkAlcool extends AActivity {
    // name, duration, costEnergy, costMoney
    public DrinkAlcool() {
        super("Drink a glass", 1, 0, 10);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("pushup", 0.4f); // !!!!! need to find some music

        int newStress = hero.getStress() - 6;
        hero.setStress(newStress);

        double chance = Math.random();
        if (chance < 0.1) {
            int newSocial = hero.getSocial() - 3;
            int newEndurance = hero.getEndurance() - 3;
            hero.setSocial(newSocial);
            hero.setEndurance(newEndurance);
            message = "That was one drink too many !!!\n You vomit on the barmaid, losing your self-confidence and your stamina.";
        } else {
            int newSocial = hero.getSocial() + 3;
            int newEnergy = hero.getEnergy() + 5;
            hero.setSocial(newSocial);
            message = "You spend "+ duration +" hours drinking and chatting with the bartender,\n you feel your stress disappear and you become a little more talkative.";
        }

        day.addHour(duration);
    }
}