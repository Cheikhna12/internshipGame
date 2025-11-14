package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class SnackDispenserEpitech extends AActivity {
    // name, duration, cost
    public SnackDispenserEpitech() {
        super("Eat sandwich triangles from the snack vending machine", 0, 0, 7);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("machine_nourriture",0.6f);
        int newEnergy= hero.getEnergy()+5;
        int newMoney= hero.getMoney()-cost;

        hero.setEnergy(newEnergy);
        hero.setEnergy(newMoney);
        hero.setSatiety(hero.getSatiety()+35);

        day.addHour(duration);

        message = "You pay "+cost+" euros for the sandwich triangles \nand increase your energy to "+newEnergy+".";
    }
}