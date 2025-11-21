package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;

public class PersonalProject extends AActivity {
    // name, duration, cost
    public PersonalProject() {
        super("Work on a personal project", 1, 5,0);
    }

    @Override
    public void doIt(AHero hero, Day day) {
        SoundManager.playSound("keybordTyping", 0.9f);
        int newCodingSkills = hero.getCodingSkills() + 3;
        int newEnergy = hero.getEnergy() - energyUse;

        hero.setCodingSkills(newCodingSkills);
        hero.setEnergy(newEnergy);
        day.addHour(duration);

        message = "After " + duration + " hour(s) of work, your coding Skills increased \nto "
                + newCodingSkills + " and energy decreased to " + newEnergy;
        hero.hasWorkedOnPersonalProjects = true;
    }
}