package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class ChessClub extends AActivity {
    // name, duration, costEnergy, costMoney
    public ChessClub() {
        super("Go to the Chess Club", 1, 10,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("chess",1f);
        int newSocial = hero.getSocial() + 2;
        int newEnergy = hero.getEnergy() - energyUse;

        hero.setSocial(newSocial);
        hero.setEnergy(newEnergy);

        day.addHour(duration);

        message = "You play " + duration + " hour with the other club member, getting along with them.";
    }
}