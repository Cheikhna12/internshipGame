package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;

import java.util.List;

public class Shop extends ALieuVisitable {


    public Shop(InternshipQuestGame game) {
        super(game);
        this.openHour =9;
        this.closedHour =20;
       this.openOnWeekends = true;
        activities = ActivityFactory.getShopActivities(this);
    }

    @Override
    public void onEnter() {
        // nom, loop or not, volume %
        SoundManager.playMusic("shop", true, 0.3f);
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }
}





