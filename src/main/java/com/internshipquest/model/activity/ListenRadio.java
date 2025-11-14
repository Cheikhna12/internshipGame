package com.internshipquest.model.activity;

import java.util.Random;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class ListenRadio extends AActivity {
    // name, duration, costEnergy, costMoney
    public ListenRadio() {
        super("Listen to the radio", 0, 0,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        double chance = Math.random();
        // SoundManager.playSound("cloverSearch",1f);
        if (chance < 0.2) {
            message = "You're listening to the radio.\n The current program is talking about how:\n 'not eating enough reduces your endurance'.\n\n I need to eat my fill to keep going";
        } else if (chance < 0.4 && chance >=0.2) {;
            message = "You are listening to the radio.\n The current program is talking about how:\n 'stress reduces your sleep quality and your energy upon waking'.\n\n Maybe I should monitor my stress levels.";
        } else if (chance < 0.6 && chance >=0.4) {;
            message = "You are listening to the radio.\nIt's an ad; the local store is looking for employees.\n\n Maybe I should go check it out to earn some money.";
        } else if (chance < 0.8 && chance >=0.6) {;
            message = "You are listening to the radio.\nIThe current program is talking about how:\n'The salary level determines the difficulty of the interview'.\n\nI need to prepare myself well if I'm aiming for a high salary.";
        } else {
            message = "You're listening to the radio.\n The current program is talking about how:\n 'the better you are at coding, the more successful you'll be at pool exercises'.\n\nWhat a very strange program!";
        }
        day.addHour(duration);

    }
}