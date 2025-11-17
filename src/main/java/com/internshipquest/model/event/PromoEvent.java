package com.internshipquest.model.event;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.ALieuVisitable;

public class PromoEvent extends AEvent {

    public PromoEvent() {
        super("Promotions will take place tomorrow at the store.");
    }

    @Override
    public void applyEffect(AHero hero,Day day) {
        day.setCodeEvent(2); // 1 = meetup open 2: market promo
    }
}