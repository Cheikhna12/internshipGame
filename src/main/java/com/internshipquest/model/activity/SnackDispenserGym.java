package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class SnackDispenserGym extends AActivity {
    // name, duration, cost
    public SnackDispenserGym() {
        super("Eat a protein bar from the snack vending machine", 0, 0, 5);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("machine_nourriture",0.6f);
        int newEndurance = hero.getEndurance() + 1;
        int newEnergy= hero.getEnergy()+2;
        int newMoney= hero.getMoney()-cost;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);
        hero.setEnergy(newMoney);
        hero.setSatiety(hero.getSatiety()+30);

        day.addHour(duration);

        message = "You pay "+cost+" euros for the protein bar \nand increase your energy to "+newEnergy+" and your endurance to "+newEndurance+".";
    }
}