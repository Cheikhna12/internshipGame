package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;
import com.badlogic.gdx.graphics.Texture;
import com.internshipquest.model.hero.*;
import com.internshipquest.model.Day;


import java.util.List;

public class Epitech extends ALieuVisitable {

    private Texture profTexture;

    public Epitech(InternshipQuestGame game) {
        super(game);
        this.openHour = 8;
        this.closedHour = 19;
        this.openOnWeekends = false;
        activities = ActivityFactory.getEpitechActivities(this,day);
        profTexture = new Texture("assets/prof.png");
    }

    @Override
    public void onEnter(AHero hero, Day day) {
        // nom, loop or not, volume %
        SoundManager.playMusic("epitech", true, 0.4f);
        if (day.getCodeEvent()==3) {
            hero.setMoney(hero.getMoney() - 5);
            currentMessage = "Pickpockets stole some money from you near Epitech!";
            showingMessage = true;
            messageTimer = 0f;

            day.setCodeEvent(0);
        }
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
    public String getNpcMessage() { if (day.getCodeEvent()==1){return "I hope you will go to the meet-up today.";} else {
        return "There you are!,\n Did you remember to swipe your badge?";}
    }

    public void dispose() {
        if (profTexture != null)
            profTexture.dispose();
    }

    @Override
    public void reloadActivities() {
        activities = ActivityFactory.getEpitechActivities(this, day);
    }
}





