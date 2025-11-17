package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class ChessClub extends AActivity {
    
    public ChessClub() {
        super("Go to the Chess Club", 1, 5,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("pushup",0.4f); 
        int newSocial = hero.getSocial() + 3;
        int newEnergy = hero.getEnergy() - energyUse;

        hero.setSocial(newSocial);
        hero.setEnergy(newEnergy);

        day.addHour(duration);

        message = "You play " + duration + " hour with the other club member, getting along with them.";
    }
}