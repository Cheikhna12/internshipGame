package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.screens.NightScreenBar;

import java.util.Random;

public class TalkToStranger extends AActivity {
    private InternshipQuestGame game;

    // name, duration, costEnergy, costMoney
    public TalkToStranger(InternshipQuestGame game) {
        super("Talk to someone from the bar", 3, 5, 0);
        this.game=game;

    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("pushup", 0.4f); // !!!!! need to find some music
        int newEnergy = hero.getEnergy() - energyUse;
        hero.setEnergy(newEnergy);

        double chance = Math.random();
        int luck = hero.getLuck();
        int social = hero.getSocial();
        if (chance + (luck+social) / 1000 < 0.1) {
            int newSocial = hero.getSocial() - 5;
            hero.setSocial(newSocial);
            message = "You scan the room for someone to talk to.\n A pretty stranger seems to be sitting alone at a table,\n so you approach and try out your best opening line.\n But she dismisses you with a disdainful look.\n Your confidence plummets.";
        } else if (chance + (luck+social) / 1000 < 0.95 && chance + (luck+social) / 1000 >= 0.1) {
            int newSocial = hero.getSocial() + 5;
            hero.setSocial(newSocial);
            message = "You look around for someone to talk to.\n A pretty stranger seems to be sitting alone at a table,\n so you approach and try out your best opening line.\n She smiles and you spend " + duration + " hour chatting with her.\n When she gets up to leave, you give her one last knowing \nsmile before she disappears. ";
            day.addHour(duration);
        } else {
            day.addHour(duration);
            int newSocial = hero.getSocial() + 20;
            hero.setSocial(newSocial);
            day.setDay(day.getDay() + 1);
            hero.newEnergy(hero.getEndurance()+ 20);
            day.setHour(7 + day.getHourAfterMidnight());
            day.setHourAfterMidnight(0);
            day.setNightTriggered(false);
            day.setCodeEvent(0);
            game.setScreen(new NightScreenBar(game, hero));

        }
    }
}