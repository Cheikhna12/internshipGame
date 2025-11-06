package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;

import java.util.List;

public class FitnessClub extends ALieuVisitable {

    private List<AActivity> activities;

    public FitnessClub(InternshipQuestGame game) {
        super(game);
        activities = ActivityFactory.getFitnessActivities();
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





