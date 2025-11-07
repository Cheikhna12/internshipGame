package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.Hero;

public class DevWait16h extends AActivity {
    // name, duration, cost
    public DevWait16h() {
        super("Wait 16 hour (dev function)", 16, 0);
    }

    @Override
    public void doIt(Hero hero, Day day) {
        day.addHour(duration);

        message = "You wait " + duration + " hour(s). As they say: 'everything comes to those who wait'.";
    }
}