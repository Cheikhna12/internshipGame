package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;

public class Wait extends AActivity {
    // name, duration, cost
    public Wait() {
        super("Wait one hour", 1, 0, 0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        day.addHour(duration);

        message = "You wait " + duration + " hour(s). As they say: 'everything comes to those who wait'.";
    }
}