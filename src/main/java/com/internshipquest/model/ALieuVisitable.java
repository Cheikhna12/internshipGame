package com.internshipquest.model;

public abstract class ALieuVisitable{

    protected String currentMessage = null;
    protected float messageTimer = 0f;
    protected boolean showingMessage = false;

    public ALieuVisitable(){}


    public void update(float delta) {
        if (showingMessage) {
            messageTimer += delta;
            if (messageTimer >= 4f) { // 4 secondes écoulées
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
