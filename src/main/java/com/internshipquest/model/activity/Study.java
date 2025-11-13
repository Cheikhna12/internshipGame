package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class Study extends AActivity {
    // name, duration, cost
    public Study() {
        super("Make the pool of the day", 8, 30 ,0);
    }


    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("keybordTyping", 0.9f);
        double result = 7 * hero.getCodingSkills() / 100;
        int exoPoolSuccess =(int) Math.round(result);
        int gainCodingskill= exoPoolSuccess*5;
        hero.setCodingSkills(hero.getCodingSkills()+gainCodingskill);
        int newEnergy = hero.getEnergy() - energyUse;
        hero.setEnergy(newEnergy);
        day.addHour(duration);

        message = "You successfully solve "+exoPoolSuccess+" pool exercises, increasing your coding skills by "+gainCodingskill+".";
    }
}