package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class BuyFood extends AActivity {
    // name, duration, cost
    public BuyFood() {
        super("buy food to fill the fridge", 0, 0,20);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("StoreWork", 0.4f);
        hero.setNbFood(hero.getNbFood()+1);
        hero.setMoney(hero.getMoney()-cost);
        day.addHour(duration);

        message = "You spend "+cost+" euros on food. You currently have "+hero.getNbFood()+" food items available.";
    }
}