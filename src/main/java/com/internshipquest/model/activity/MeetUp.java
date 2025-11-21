package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;
import com.internshipquest.model.location.ALieuVisitable;

public class MeetUp extends AActivity {
    // name, duration, costEnergy, costMoney
    private ALieuVisitable lieu;
    private Day day;

    public MeetUp(ALieuVisitable lieu,Day day) {
        super("Go to MeetUp", 3, 10,0);
        this.day=day;
        this.lieu=lieu;
    }

    @Override
    public void doIt(AHero hero, Day day) {
        int newSocial = hero.getSocial() + 5;
        int newCodingSkill= hero.getCodingSkills()+15;
        int newEnergy = hero.getEnergy() - energyUse;
        day.setCodeEvent(0);

        hero.setCodingSkills(newCodingSkill);
        hero.setSocial(newSocial);
        hero.setEnergy(newEnergy);
        hero.hasGoneToMeetUp = true;


        day.addHour(duration);
        lieu.reloadActivities();

        message = "You participate to the MeetUp for " + duration + " hour.\n Your coding skills increase dramatically.";
    }
}