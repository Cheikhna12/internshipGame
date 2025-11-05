package com.internshipquest.model;

public class FitnessClub {

    private String currentMessage = null;
    private float messageTimer = 0f;
    private boolean showingMessage = false;

    public void pushUps(Hero hero) {
        int newEndurance = hero.getEndurance() + 3;
        int newEnergy = hero.getEnergy() - 5;

        currentMessage = "After 1 hour of push-ups, your endurance increased to "
                + newEndurance + " but your energy decreased to " + newEnergy;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);

        showingMessage = true;
        messageTimer = 0f;
    }

    // Appelé depuis render()
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
