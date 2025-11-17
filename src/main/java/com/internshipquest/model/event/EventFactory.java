package com.internshipquest.model.event;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventFactory {

    private  List<AEvent> possibleEvents = new ArrayList<>();
    private  Random random = new Random();

    public EventFactory(AHero hero,Day day) { //add event si vous voulez tester un event, mettez les autres en commentaire
        possibleEvents.add(new EnduranceDreamEvent(hero,3));
        possibleEvents.add(new MeetUpEvent());
        possibleEvents.add(new CatRobFood(hero));
        possibleEvents.add(new StressDreamEvent(hero));
        possibleEvents.add(new PromoEvent());
        possibleEvents.add(new PickpocketCityEvent());
    }

    public AEvent getRandomEvent() {
        if (possibleEvents.isEmpty()) return null;
        int index = random.nextInt(possibleEvents.size());
        return possibleEvents.get(index);
    }
}