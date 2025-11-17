package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.screens.NightScreenExhaustion;
import com.internshipquest.model.hero.AHero;

public class Day {
    private AHero hero;
    private InternshipQuestGame game;
    private int day = 0;
    private int hour;
    private boolean weekend = false;
    private int hourAfterMidnight = 0;
    private boolean nightTriggered = false;
    private int codeEvent = 0; //1= meetup 2: food promo

    public Day(InternshipQuestGame game, AHero hero) {
        this.hero = hero;
        this.game = game;
        day += 1;
        this.hour = 7;
        hourAfterMidnight = 0;
        nightTriggered = false;
    }

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

    public int getHourAfterMidnight() {
        return hourAfterMidnight;
    }

    public void addHour(int timeAdd) {
        if (hour < 24 && !nightTriggered) {
            hour += timeAdd;
            if (hour >= 24) {
                hourAfterMidnight = hour - 24;
                hour = hourAfterMidnight;
                nightTriggered = true;
            }

        } else {
            hourAfterMidnight += timeAdd;
            hour = hourAfterMidnight;
            nightTriggered = true;
        }
        System.out.println(hourAfterMidnight);
        System.out.println(nightTriggered);
        // Bloquer après 3h du matin
        if (nightTriggered && hourAfterMidnight >= 3) {
            game.setScreen(new NightScreenExhaustion(game, hero));

            day += 1;       // jour suivant
            hour = 10;      // réveil à 10h
            hourAfterMidnight = 0;
            nightTriggered = false;
            this.setCodeEvent(0);
            hero.newEnergy(hero.getEndurance()/2);
        }
    }

    public int getCodeEvent() {
        return codeEvent;
    }

    public void setCodeEvent(int Event) {
        this.codeEvent = Event;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public void setHourAfterMidnight(int hourAfterMidnight) {
        this.hourAfterMidnight = hourAfterMidnight;
    }

    public void setNightTriggered(boolean nightTriggered) {
        this.nightTriggered = nightTriggered;
    }
}
