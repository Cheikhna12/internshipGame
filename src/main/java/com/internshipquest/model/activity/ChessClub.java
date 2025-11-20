package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class ChessClub extends AActivity {

    public boolean hasGoneToChessClub = false;

    public ChessClub() {
        super("Go to the Chess Club", 1, 10,0);
        System.out.println("ChessClub");
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("chess",1f);
        int newSocial = hero.getSocial() + 2;
        int newEnergy = hero.getEnergy() - energyUse;
        System.out.println("has gone to " + hero.hasGoneToChessClub);
        hero.hasGoneToChessClub = true;


        hero.setSocial(newSocial);
        hero.setEnergy(newEnergy);

        day.addHour(duration);

        message = "You play " + duration + " hour with the other club member, getting along with them.";
        System.out.println(hasGoneToChessClub);

    }
}