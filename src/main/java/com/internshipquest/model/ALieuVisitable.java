package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.badlogic.gdx.graphics.Texture;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.utils.SoundManager;
import java.util.List;

public abstract class ALieuVisitable {

    protected InternshipQuestGame game;
    protected Day day;
    protected String currentMessage = null;
    protected float messageTimer = 0f;
    protected boolean showingMessage = false;
    protected int openHour = 9;
    protected int closedHour =21;
    protected boolean openOnWeekends = true;
    protected List<AActivity> activities;


    public ALieuVisitable(InternshipQuestGame game){
        this.game=game;
        this.day=game.getDay();
    }

    public boolean isOpen(Day day) {
        int hour = day.getHour();
        boolean weekend = day.isWeekend();
        return hour >= openHour && hour < closedHour && (openOnWeekends || !weekend);
    }

    public int getClosedHour() {
        return closedHour;
    }

    public boolean isOpenWeekend() {
        return openOnWeekends;
    }

    public int getOpenHour() {
        return openHour;
    }
    public abstract void onEnter();
    public abstract void onExit();


    public void update(float delta) {
        if (showingMessage) {
            messageTimer += delta;
            if (messageTimer >= 3.5f) { // 3.5 secondes écoulées
                showingMessage = false;
                currentMessage = null;
                messageTimer = 0f;
            }
        }
    }

    public boolean isShowingMessage() {
        return showingMessage;
    }

    public void performActivity(int index, AHero hero, Day day) {
        if (index < 0 || index >= activities.size()) return;

        AActivity activity = activities.get(index);
        if (day.getHour() + activity.getDuration() > closedHour) {
            currentMessage = "I don't have time for that !!!";
            showingMessage = true;
            messageTimer = 0f;
            return;
        }

        if (hero.getEnergy() < activity.getEnergyUse() ) {
            currentMessage = "Damn !!! I am too tired to do that...";
            showingMessage = true;
            messageTimer = 0f;
            return;
        }

        if (hero.getMoney() < activity.getCost() ) {
            currentMessage = "I'm too broke for buy that...";
            showingMessage = true;
            messageTimer = 0f;
            return;
        }

        activity.doIt(hero, day);

        // Affichage du message
        currentMessage = activity.getMessage();
        showingMessage = true;
        messageTimer = 0f;
    }

    public String getCurrentMessage() {
        return currentMessage;
    }
    public Texture getNpcTexture() { return null; }
    public String getNpcMessage() { return null; }

    public List<AActivity> getActivities() {
        return activities;
    }

}
