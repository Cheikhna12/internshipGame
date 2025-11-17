package com.internshipquest.model.event;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.ALieuVisitable;

public abstract class AEvent {

    protected String messageNight;

    public AEvent(String messageNight) {
        this.messageNight = messageNight;
    }

    public String getMessageNight() {
        return messageNight;
    }

    // Chaque event définit son effet
    public abstract void applyEffect(AHero hero,Day day);
}

