package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;

public class Sleep extends AActivity {
    
    public Sleep() {
        super("Sleep until tomorrow", 0, 0,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        day.setDay(day.getDay()+ 1);
        hero.setEnergy(hero.getEndurance()*2);

        day.setHour(7+day.getHourAfterMidnight());
        day.setHourAfterMidnight(0);
        day.setNightTriggered(false);
        message = "After a good night, you regenere your energie to "+hero.getEnergy()+".";
    }
}