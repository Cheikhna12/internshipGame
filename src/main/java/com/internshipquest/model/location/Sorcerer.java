package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;

import java.util.List;

public class Sorcerer extends ALieuVisitable {


    public Sorcerer(InternshipQuestGame game) {
        super(game);
        this.openHour = 8;
        this.closedHour = 19;
        this.openOnWeekends = true;
        activities = ActivityFactory.getSorcererActivities(this);
    }

    @Override
    public void onEnter() {
        
        SoundManager.playMusic("house", true, 0.4f); 
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }
}





