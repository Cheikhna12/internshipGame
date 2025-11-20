package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

import java.util.Random;

public class DrinkAlcool extends AActivity {
    
    public DrinkAlcool() {
        super("Drink a glass", 1, 0, 10);
    }

    @Override
    public void doIt(AHero hero, Day day) {

        int newStress = hero.getStress() - 6;
        hero.setStress(newStress);
        hero.setMoney(hero.getMoney()-cost);

        double chance = Math.random();
        int luck = hero.getLuck();
        if (chance+(luck/1000) < 0.1) { // la chance aide un peu à éviter les problemes
            SoundManager.playSound("GlassDrinkV", 0.4f);
            int newSocial = hero.getSocial() - 3;
            int newEndurance = hero.getEndurance() - 3;
            hero.setSocial(newSocial);
            hero.setEndurance(newEndurance);
            hero.setSatiety(hero.getSatiety()-30);
            message = "That was one drink too many !!!\n You vomit on the barmaid, losing your self-confidence and your stamina.";
        } else {
            SoundManager.playSound("GlassDrink", 0.4f);
            int newSocial = hero.getSocial() + 3;
            int newEnergy = hero.getEnergy() + 5;
            hero.setSocial(newSocial);
            hero.setEnergy(newEnergy);
            message = "You spend "+ duration +" hours drinking and chatting with the bartender,\n you feel your stress disappear and you become a little more talkative.";
        }

        day.addHour(duration);
        hero.hasDrunk  = true;
    }
}