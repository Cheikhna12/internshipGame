package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class FitnessClub extends ALieuVisitable {

    private List<AActivity> activities;
    private Texture coachTexture;

    public FitnessClub(InternshipQuestGame game) {
        super(game);
        this.openHour = 8;
        this.closedHour = 22;
        this.openOnWeekends = true;
        activities = ActivityFactory.getFitnessActivities();
        coachTexture = new Texture("assets/coach.png");
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

    @Override
    public Texture getNpcTexture() {
        return coachTexture;
    }

    @Override
    public String getNpcMessage() {
        return "Hey kid, what do you want to do today ?";
    }


    public void dispose() {
        if (coachTexture != null)
            coachTexture.dispose();
    }
}





