package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class FitnessClub extends ALieuVisitable {

    private Texture coachTexture;

    public FitnessClub(InternshipQuestGame game) {
        super(game);
        this.openHour = 8;
        this.closedHour = 22;
        this.openOnWeekends = true;
        activities = ActivityFactory.getFitnessActivities(this);
        coachTexture = new Texture("assets/coach.png");
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





