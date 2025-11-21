package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class Wait extends AActivity {

    public Wait() {
        super("Wait one hour", 1, 0, 0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("waiting",0.9f);
        day.addHour(duration);

        message = "You wait " + duration + " hour(s). As they say: 'everything comes to those who wait'.";
    }
}
