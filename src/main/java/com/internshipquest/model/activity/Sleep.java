package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.screens.NightScreen;

public class Sleep extends AActivity {
    private InternshipQuestGame game;
    // name, duration, cost
    public Sleep(InternshipQuestGame game) {
        super("Sleep until tomorrow", 0, 0,0);
        this.game=game;
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("snore", 0.4f);
        day.setDay(day.getDay()+ 1);
        hero.newEnergy(hero.getEndurance());
        day.setHour(7+day.getHourAfterMidnight());
        day.setHourAfterMidnight(0);
        day.setNightTriggered(false);
        day.setCodeEvent(0);
        game.setScreen(new NightScreen(game, hero, day));
    }
}