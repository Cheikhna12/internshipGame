package com.internshipquest.model.location;


import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Maison extends ALieuVisitable {

    private Texture catTexture;


    public Maison(InternshipQuestGame game) {
        super(game);
        this.openHour = 0;
        this.closedHour = 28;
        this.openOnWeekends = true;
        activities = ActivityFactory.getMaisonActivities(game);
        catTexture = new Texture("assets/chat.png");
    }

    @Override
    public void onEnter() {
        // nom, loop or not, volume %
        SoundManager.playMusic("house", true, 0.4f);
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }

    @Override
    public Texture getNpcTexture() {
        return catTexture;
    }

    @Override
    public String getNpcMessage() {
        return "Miaaaaoouu!!!!!\n(bienvenue maitre!!!)";
    }


    public void dispose() {
        if (catTexture != null)
            catTexture.dispose();
    }
}





