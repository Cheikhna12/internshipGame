package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.badlogic.gdx.graphics.Texture;

public abstract class ALieuVisitable {

    protected InternshipQuestGame game;
    protected Day day;
    protected String currentMessage = null;
    protected float messageTimer = 0f;
    protected boolean showingMessage = false;
    protected int openHour = 9;
    protected int closedHour =21;
    protected boolean openOnWeekends = true;


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

    public String getCurrentMessage() {
        return currentMessage;
    }
    public Texture getNpcTexture() { return null; }
    public String getNpcMessage() { return null; }
}
