package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.AHero;

public class DisplayStat extends AActivity {
    // name, duration, cost
    public DisplayStat() {
        super("looking at oneself in the mirror", 0, 0,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        day.addHour(duration);

        message = "You look deeply within yourself and see your potential:\n" +
                "Endurance: " + hero.getEndurance() + "\n" +
                "Luck: " + hero.getLuck() + "\n" +
                "Coding Skills: " + hero.getCodingSkills() + "\n" +
                "Social capacity: " + hero.getSocial() + ".";
    }
}