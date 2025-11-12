package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.AHero;

public abstract class AActivity {
    protected String name;
    protected int duration;
    protected int energyUse;
    protected String message;
    protected int cost;


    public AActivity(String name, int duration, int energyUse, int cost) {
        this.name = name;
        this.duration = duration;
        this.energyUse = energyUse;
        this.cost = cost;
    }


    public int getCost() {
        return cost;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    public String getMessage() {
        return message;
    }

    public int getEnergyUse() {
        return energyUse;
    }

    public abstract void doIt(AHero hero, Day day);
}