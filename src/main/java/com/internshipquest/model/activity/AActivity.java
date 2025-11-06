package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.Hero;

public abstract class AActivity {
    protected String name;       // Nom de l'activité
    protected int duration;      // Durée en heures
    protected int energyUse;     // Energie consommée
    protected String message;    // Message afficher par l'activité

    public AActivity(String name, int duration, int energyUse) {
        this.name = name;
        this.duration = duration;
        this.energyUse = energyUse;
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

    public abstract void doIt(Hero hero, Day day);
}