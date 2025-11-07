package com.internshipquest.model.activity;

import com.internshipquest.model.Day;
import com.internshipquest.model.Hero;
import com.internshipquest.model.ALieuVisitable;

public class AskOpeningHours extends AActivity {

    private ALieuVisitable lieu;
    // name, duration, cost
    public AskOpeningHours(ALieuVisitable lieu) {
        super("Ask Opening Hours", 0, 2); this.lieu=lieu;
    }

    @Override
    public void doIt(Hero hero, Day day) {
        day.addHour(duration);
        String weekendInfo = lieu.isOpenWeekend() ? "Also open on weekends." : "Closed on weekends.";
        message = "You head towards the reception desk to find out the opening hours.\n" +
                "The" + lieu.getClass().getSimpleName() +" is open from " + lieu.getOpenHour() +"h to " + lieu.getClosedHour() +"h.\n"
                + weekendInfo;
    }
}
