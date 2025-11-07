package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;

import java.util.List;

public class Maison extends ALieuVisitable {


    public Maison(InternshipQuestGame game) {
        super(game);
        this.openHour = 0;
        this.closedHour = 24;
        this.openOnWeekends = true;
        activities = ActivityFactory.getMaisonActivities();
    }
}





