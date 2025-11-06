package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;

public abstract class ALieuVisitable {

    protected InternshipQuestGame game;
    protected Day day;
    protected String currentMessage = null;
    protected float messageTimer = 0f;
    protected boolean showingMessage = false;

    public ALieuVisitable(InternshipQuestGame game){
        this.game=game;
        this.day=game.getDay();
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
}
