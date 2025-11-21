package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class LookFridge extends AActivity {
    // name, duration, costEnergy, costMoney
    public LookFridge() {
        super("Look in the fridge", 0, 0, 0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        //SoundManager.playSound("pushup",0.4f);
        if (hero.getNbFood() == 0) {
            message = "You notice the abyssal emptiness of your fridge.\n It might be time to go grocery shopping.";
        } else {
            message = "You have enough food for " + hero.getNbFood() + " days.";
        }
    }
}