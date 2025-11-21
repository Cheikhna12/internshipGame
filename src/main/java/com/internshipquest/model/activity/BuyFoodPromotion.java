package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class BuyFoodPromotion extends AActivity {
    // name, duration, cost
    public BuyFoodPromotion() {
        super("buy food on sale", 0, 0,15);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("StoreWork", 0.4f);
        hero.setNbFood(hero.getNbFood()+2);
        hero.setMoney(hero.getMoney()-cost);
        day.addHour(duration);

        message = "You spend "+cost+" euros on food.\n You got 2 meals for 15 euros.\n You currently have "+hero.getNbFood()+" food items available.";
    }
}