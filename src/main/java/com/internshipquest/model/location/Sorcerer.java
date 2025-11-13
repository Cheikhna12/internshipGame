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
        // nom, loop or not, volume %
        SoundManager.playMusic("sorcerer", true, 0.4f); // !!! need to change music
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }
}





