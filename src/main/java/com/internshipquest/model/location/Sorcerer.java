package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;
import com.badlogic.gdx.graphics.Texture;
import com.internshipquest.model.hero.*;
import com.internshipquest.model.Day;


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
    public void onEnter(AHero hero, Day day) {
        // nom, loop or not, volume %
        SoundManager.playMusic("sorcerer", true, 0.4f);
        if (day.getCodeEvent() == 3) {
            if (hero.getEndurance() > 40) {
                hero.setLuck(hero.getLuck() + 10);
                currentMessage = "You see a pickpocket steal from the sorcerer,\n you stop him, and the sorcerer thanks you by giving you a magic amulet: your luck increases.";
            } else {
                hero.setLuck(hero.getLuck() - 10);
                currentMessage = "You see a pickpocket steal from the wizard. Alas, you are not strong enough to stop him.\n The sorcerer curses you for your weakness.";
            }
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





