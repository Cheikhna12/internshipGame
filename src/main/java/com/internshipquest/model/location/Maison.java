package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;

import java.util.List;

public class Maison extends ALieuVisitable {


    public Maison(InternshipQuestGame game) {
        super(game);
        this.openHour = 0;
        this.closedHour = 24;
        this.openOnWeekends = true;
        activities = ActivityFactory.getMaisonActivities();
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





