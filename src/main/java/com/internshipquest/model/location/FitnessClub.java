package com.internshipquest.model.location;

import com.internshipquest.model.hero.*;
import com.internshipquest.model.Day;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.badlogic.gdx.graphics.Texture;
import com.internshipquest.utils.SoundManager;
import com.internshipquest.model.hero.AHero;


import java.util.List;


public class FitnessClub extends ALieuVisitable {

    private AHero hero;
    private Texture coachTexture;

    public FitnessClub(InternshipQuestGame game) {
        super(game);
        this.hero=game.getHero();
        this.openHour = 8;
        this.closedHour = 22;
        this.openOnWeekends = true;
        activities = ActivityFactory.getFitnessActivities(this,hero);
        coachTexture = new Texture("assets/coach.png");
    }
    @Override
    public void reloadActivities() {
        activities = ActivityFactory.getFitnessActivities(this, hero);
    }

    @Override
    public void onEnter(AHero hero,Day day) {
        // nom, loop or not, volume %
        SoundManager.playMusic("gym", true, 0.8f);
        if (day.getCodeEvent()==3) {
            if (hero.getLuck()>20){
            hero.setMoney(hero.getMoney() + 15);
            currentMessage = "You find a stolen wallet on the ground, you return it to its owner.\n He thanks you by giving you 15 euros.";
            showingMessage = true;
            messageTimer = 0f;

            day.setCodeEvent(0);}
        }
    }

    public AHero getHero() {
        return hero;
    }

    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }


    @Override
    public Texture getNpcTexture() {
        return coachTexture;
    }

    @Override
    public String getNpcMessage() {
        if (hero.isLicence()==false){return "You have to pay for a sports license \nif you want to train here.";} else { return "Hey kid, what do you want to do today ?";}
    }


    public void dispose() {
        if (coachTexture != null)
            coachTexture.dispose();
    }
}





