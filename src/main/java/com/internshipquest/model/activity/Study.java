package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class Study extends AActivity {
    
    public Study() {
        super("Make the pool of the day", 8, 30, 0);
    }


    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("keybordTyping", 0.9f);
        double result = 7 * hero.getCodingSkills() / 100;
        int exoPoolSuccess = (int) Math.round(result);
        int gainCodingskill = exoPoolSuccess * 5;
        int gainStress = (7 - exoPoolSuccess) * 2;
        hero.setCodingSkills(hero.getCodingSkills() + gainCodingskill);
        hero.setStress(hero.getStress() + gainStress);
        int newEnergy = hero.getEnergy() - energyUse;
        hero.setEnergy(newEnergy);
        day.addHour(duration);

        if (exoPoolSuccess == 7) {
            message = "You successfully solve all the pool exercises, increasing your coding skills by " + gainCodingskill + ".";
        } else {
            message = "You successfully solve " + exoPoolSuccess + " pool exercises, increasing your coding skills by " + gainCodingskill + ".\nNot having succeeded in all the exercises increases your stress by " + gainStress + ".";
        }

        hero.hasStudied = true;
    }
}