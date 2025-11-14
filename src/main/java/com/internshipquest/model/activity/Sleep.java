package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class Sleep extends AActivity {
    // name, duration, cost
    public Sleep() {
        super("Sleep until tomorrow", 0, 0,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("snore", 0.4f);
        day.setDay(day.getDay()+ 1);
        hero.newEnergy(hero.getEndurance());
        day.setHour(7+day.getHourAfterMidnight());
        day.setHourAfterMidnight(0);
        day.setNightTriggered(false);
        message = "After a good night, you regenere your energy to "+hero.getEnergy()+"thanks to your endurance.\n Your stress level affects your sleep";
    }
}