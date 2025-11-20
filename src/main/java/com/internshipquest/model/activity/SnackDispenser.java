package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class SnackDispenser extends AActivity {
    
    public SnackDispenser() {
        super("buy a protein bar from the snack vending machine", 0, 0, 5);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("machine_nourriture",0.6f);
        int newEndurance = hero.getEndurance() + 1;
        int newEnergy= hero.getEnergy()+2;
        int newMoney= hero.getMoney()-5;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);
        hero.setEnergy(newMoney);

        day.addHour(duration);

        message = "You pay 5 euros for the protein bar and gain "+newEnergy+" energy and "+newEndurance+" endurance.";
        hero.hasHadSNacks = true;
    }
}