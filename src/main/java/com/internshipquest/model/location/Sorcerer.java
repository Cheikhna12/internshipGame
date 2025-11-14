package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Sorcerer extends ALieuVisitable {

    private Texture sorcererTexture;


    public Sorcerer(InternshipQuestGame game) {
        super(game);
        this.openHour = 8;
        this.closedHour = 19;
        this.openOnWeekends = true;
        activities = ActivityFactory.getSorcererActivities(this);
        sorcererTexture = new Texture("assets/Sorcerer.png");
    }

    @Override
    public void onEnter() {
        // nom, loop or not, volume %
        SoundManager.playMusic("sorcerer", true, 0.4f);
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }

    @Override
    public Texture getNpcTexture() {
        return sorcererTexture;
    }

    @Override
    public String getNpcMessage() {
        return "Uh... Hello! Don't touch the cauldron...\n He's a bit... temperamental.";
    }


    public void dispose() {
        if (sorcererTexture != null)
            sorcererTexture.dispose();
    }
}





