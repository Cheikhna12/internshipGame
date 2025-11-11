package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;

import java.util.List;

public class Bar extends ALieuVisitable {


    public Bar(InternshipQuestGame game) {
        super(game);
        this.openHour =20;
        this.closedHour =24;
       this.openOnWeekends = true;
        activities = ActivityFactory.getBarActivities();
    }

    @Override
    public void onEnter() {
        // nom, loop or not, volume %
        SoundManager.playMusic("house", true, 0.4f); // !!! need to change music
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }
}





