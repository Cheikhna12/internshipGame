package com.internshipquest.model.location;

import com.internshipquest.model.hero.*;
import com.internshipquest.model.Day;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;
import com.badlogic.gdx.graphics.Texture;

import java.util.List;

public class Bar extends ALieuVisitable {

    private Texture barmaidTexture;

    public Bar(InternshipQuestGame game) {
        super(game);
        this.openHour =20;
        this.closedHour =28;
       this.openOnWeekends = true;
        activities = ActivityFactory.getBarActivities(this,game);
        barmaidTexture = new Texture("assets/barmaid.png");
    }

    @Override
    public void onEnter(AHero hero,Day day) {
        // nom, loop or not, volume %
        SoundManager.playMusic("bar", true, 0.4f);
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }

    @Override
    public Texture getNpcTexture() {
        return barmaidTexture;
    }

    @Override
    public String getNpcMessage() {
        return "Good evening sir!\n What can I get you today?";
    }


    public void dispose() {
        if (barmaidTexture != null)
            barmaidTexture.dispose();
    }
}





