package com.internshipquest.model;

public class FitnessClub extends ALieuVisitable {

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

    public void deadlift(Hero hero) {
        int newEndurance = hero.getEndurance() + 8;
        int newEnergy = hero.getEnergy() - 10;

        currentMessage = "After 1 hour of deadlift, your endurance increased to "
                + newEndurance + " but your energy decreased to " + newEnergy;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);

        showingMessage = true;
        messageTimer = 0f;
    }
}
