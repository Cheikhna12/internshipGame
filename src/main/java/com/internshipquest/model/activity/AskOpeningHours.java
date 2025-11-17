package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.ALieuVisitable;

public class AskOpeningHours extends AActivity {

    private ALieuVisitable lieu;
    
    public AskOpeningHours(ALieuVisitable lieu) {
        super("Ask Opening Hours", 0, 2, 0); this.lieu=lieu;
    }

    @Override
    public void doIt(AHero hero, Day day) {
        int closedHour = lieu.getClosedHour();
        if (closedHour >23){closedHour-=24;}
        String weekendInfo = lieu.isOpenWeekend() ? "Also open on weekends." : "Closed on weekends.";
        message = "You head towards the reception desk to find out the opening hours.\n" +
                "The" + lieu.getClass().getSimpleName() +" is open from " + lieu.getOpenHour() +"h to " + closedHour +"h.\n"
                + weekendInfo;
    }
}
