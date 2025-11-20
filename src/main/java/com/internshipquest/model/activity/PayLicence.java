package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.ALieuVisitable;
import com.internshipquest.utils.SoundManager;

public class PayLicence extends AActivity {
    private ALieuVisitable lieu;
    // name, duration, costEnergy, costMoney
    public PayLicence(ALieuVisitable lieu) {
        super("Pay the sports license", 0, 0,50);this.lieu=lieu;
    }

    @Override
    public void doIt(AHero hero, Day day) {
        //SoundManager.playSound("pushup",0.4f);
        hero.setMoney(hero.getMoney()-50);
        hero.setLicence(true);
        day.addHour(duration);

        message = "You pay 50 euros for access to the fitness club.";
        hero.hasPaidLicence = true;

        lieu.reloadActivities();
    }
}