package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;

public class FitnessClub extends ALieuVisitable {

    public FitnessClub(InternshipQuestGame game){
        super(game);
    }

    public void pushUps(Hero hero) {
        int newEndurance = hero.getEndurance() + 3;
        int newEnergy = hero.getEnergy() - 5;
        day.addHour(1);


        currentMessage = "After 1 hour of push-ups, your endurance increased to "
                + newEndurance + " but your energy decreased to " + newEnergy;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);

        showingMessage = true;
        messageTimer = 0f;
    }

    public void deadlift(Hero hero) {
        int newEndurance = hero.getEndurance() + 4;
        int newEnergy = hero.getEnergy() - 8;
        day.addHour(5);

        currentMessage = "After 2 hours of deadlift, your endurance increased to "
                + newEndurance + " but your energy decreased to " + newEnergy;

        hero.setEndurance(newEndurance);
        hero.setEnergy(newEnergy);

        showingMessage = true;
        messageTimer = 0f;
    }
}
