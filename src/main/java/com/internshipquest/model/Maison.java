package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;

import java.util.List;

public class Maison extends ALieuVisitable {

    private List<AActivity> activities;

    public Maison(InternshipQuestGame game) {
        super(game);
        this.openHour = 0;
        this.closedHour = 24;
        this.openOnWeekends = true;
        activities = ActivityFactory.getMaisonActivities();
    }


    public void performActivity(int index, Hero hero, Day day) {
        if (index < 0 || index >= activities.size()) return;

        AActivity activity = activities.get(index);
        activity.doIt(hero, day);

        // Affichage du message
        currentMessage = activity.getMessage();
        showingMessage = true;
        messageTimer = 0f;
    }

    public List<AActivity> getActivities() {
        return activities;
    }
}





