package com.internshipquest.model;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.screens.NightScreen;

public class Day {
    private Hero hero;
    private InternshipQuestGame game;
    private static int day = 0;
    private int hour;
    private boolean weekend = false;
    private static int hourAfterMidnigth = 0;

    public Day(InternshipQuestGame game, Hero hero) {
        this.hero = hero;
        this.game = game;
        day += 1;
        this.hour = 7;
        // hero.setEnergy(Hero.calcEnergy(hero.getEndurance()));
        hourAfterMidnigth = 0;
    }

    public Day(InternshipQuestGame game, Hero hero, int hourAfterMidnigth) {
        this.hero = hero;
        this.game = game;
        day += 1;
        this.hour = 7 + hourAfterMidnigth;
        // if (this.hour == 10) {
        //     hero.setEnergy(Hero.calcEnergy(hero.getEndurance()) / 2);
        // } else {
        //     hero.setEnergy(Hero.calcEnergy(hero.getEndurance()));
        // }
        Day.hourAfterMidnigth = hourAfterMidnigth;
    }

    public boolean isWeekend() {
        weekend = (day % 6 == 0 || day % 7 == 0);
        return weekend;
    }

    public int getHour() {
        return hour;
    }

    public static int getDay() {
        return day;
    }

    public void addHour(int timeAdd) {
        if (hourAfterMidnigth == 0) {
            if (hour + timeAdd < 24) {
                hour += timeAdd;
            } else if (hour + timeAdd >= 24 && hour + timeAdd < 27) {
                hour = hour + timeAdd - 24;
                hourAfterMidnigth = hour;
            } else {
                hour = 3;
                hourAfterMidnigth = 3;
            }
        } else if (hourAfterMidnigth + timeAdd < 3) {
            hour = hourAfterMidnigth + timeAdd;
            hourAfterMidnigth = hour;
        } else {
            hour = 3;
            hourAfterMidnigth = 3;
        }

        verifHour();
    }

    public void verifHour() {
        if (hourAfterMidnigth == 3) {
            game.setScreen(new NightScreen(game, hero));
        }
    }
}
