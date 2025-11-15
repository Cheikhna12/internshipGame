package com.internshipquest.model.event;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.ALieuVisitable;

public class MeetUpEvent extends AEvent {

    public MeetUpEvent() {
        super("A Meet- Up will be up today at Epitech");
    }

    @Override
    public void applyEffect(AHero hero,Day day) {
        day.setCodeEvent(1); // 1 = meetup open
    }
}