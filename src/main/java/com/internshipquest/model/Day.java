package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.screens.NightScreen;

public class Day {
    private Hero hero;
    private InternshipQuestGame game;
    private int day = 0;
    private int hour;
    private boolean weekend = false;
    private int hourAfterMidnight = 0;

    public Day(InternshipQuestGame game, Hero hero) {
        this.hero = hero;
        this.game = game;
        day += 1;
        this.hour = 7;
        hourAfterMidnight = 0;
    }


    // if (this.hour == 10) {
    //     hero.setEnergy(Hero.calcEnergy(hero.getEndurance()) / 2);
    // } else {
    //     hero.setEnergy(Hero.calcEnergy(hero.getEndurance()));
    // }

    public boolean isWeekend() {
        weekend = (day % 6 == 0 || day % 7 == 0);
        return weekend;
    }

    public int getHour() {
        return hour;
    }

    public int getDay() {
        return day;
    }

    public void addHour(int timeAdd) {
        if (hourAfterMidnight == 0) {
            if (hour + timeAdd < 24) {
                hour += timeAdd;
            } else if (hour + timeAdd >= 24 && hour + timeAdd < 27) {
                hour = hour + timeAdd - 24;
                hourAfterMidnight = hour;
            } else {
                hour = 3;
                hourAfterMidnight = 3;
            }
        } else if (hourAfterMidnight + timeAdd < 3) {
            hour = hourAfterMidnight + timeAdd;
            hourAfterMidnight = hour;
        } else {
            hour = 3;
            hourAfterMidnight = 3;
        }

        verifHour();
    }

    public void verifHour() {
        if (this.hourAfterMidnight>= 3) {
            this.hourAfterMidnight = 3;
            game.setScreen(new NightScreen(game, hero));
            this.day += 1;
            this.hour = 10;        // réveil à 10h car fatigue
            //     hero.setEnergy(Hero.calcEnergy(hero.getEndurance()) / 2); //Energie divisé par 2 le jour suivant
            hourAfterMidnight = 0;
        }
    }
}
