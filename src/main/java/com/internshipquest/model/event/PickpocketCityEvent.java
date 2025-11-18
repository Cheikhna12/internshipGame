package com.internshipquest.model.event;

import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.ALieuVisitable;
import com.internshipquest.model.Day;

public class PickpocketCityEvent extends AEvent {

    public PickpocketCityEvent() {
        super("I heard on the news that there are pickpockets in town,\n I hope I won't have any problems tomorrow.");
    }
    @Override
    public void applyEffect(AHero hero,Day day) {
        day.setCodeEvent(3);} // 1 = meetup open 2: market promo 3 pickpoket
}