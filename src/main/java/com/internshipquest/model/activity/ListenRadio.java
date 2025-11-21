package com.internshipquest.model.activity;

import java.util.Random;
import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import java.util.ArrayList;
import java.util.List;
import com.internshipquest.utils.SoundManager;

public class ListenRadio extends AActivity {

    private  List<String> radioTips = new ArrayList<>();
    private  Random random = new Random();

    // name, duration, costEnergy, costMoney
    public ListenRadio() {
        super("Listen to the radio", 0, 0,0);
        radioTips.add("You're listening to the radio.\n The current program is talking about how:\n 'not eating enough reduces your endurance'.\n\n I need to eat my fill to keep going");
        radioTips.add("You are listening to the radio.\n The current program is talking about how:\n 'stress reduces your sleep quality and your energy upon waking'.\n\n Maybe I should monitor my stress levels.");
        radioTips.add("You are listening to the radio.\nIt's an ad; the local store is looking for employees.\n\n Maybe I should go check it out to earn some money.");
        radioTips.add("You are listening to the radio.\nThe current program is talking about how:\n'The salary determines the difficulty of the interview'.\n\nI need to prepare myself well if I'm aiming for a high salary.");
        radioTips.add("You're listening to the radio.\n The current program is talking about how:\n 'the better you are at coding, the more successful you'll be at pool exercises'.\n\nWhat a very strange program!");
        radioTips.add("You're listening to the radio.\n The current program is talking about how:\n 'If life were a video game, doing certain activities would earn victory points.'.\n\nNow I'm asking myself silly questions. I am a human being, right? Not a program, am I?");
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("radio",0.4f);
        int index = random.nextInt(radioTips.size());
        message = radioTips.get(index);

        day.addHour(duration);

        hero.hasListenedToRadio = true;

    }
}