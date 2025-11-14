package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Epitech extends ALieuVisitable {

    private Texture profTexture;

    public Epitech(InternshipQuestGame game) {
        super(game);
        this.openHour = 8;
        this.closedHour = 19;
        this.openOnWeekends = false;
        activities = ActivityFactory.getEpitechActivities(this);
        profTexture = new Texture("assets/prof.png");
    }

    @Override
    public void onEnter() {
        // nom, loop or not, volume %
        SoundManager.playMusic("epitech", true, 0.4f);
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }

    @Override
    public Texture getNpcTexture() {
        return profTexture;
    }

    @Override
    public String getNpcMessage() {
        return "There you are!,\n Did you remember to swipe your badge?";
    }


    public void dispose() {
        if (profTexture != null)
            profTexture.dispose();
    }
}





