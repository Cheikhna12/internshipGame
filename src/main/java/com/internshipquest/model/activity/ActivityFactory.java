package com.internshipquest.model.activity;

import java.util.ArrayList;
import java.util.List;

import com.internshipquest.model.Day;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.model.location.*;
import com.internshipquest.InternshipQuestGame;

public class ActivityFactory {

    public static List<AActivity> getFitnessActivities(ALieuVisitable lieu, AHero hero) {
        List<AActivity> list = new ArrayList<>();
        if (hero.isLicence() == false) {
            list.add(new PayLicence(lieu));
            list.add(new SnackDispenserGym());
            list.add(new AskOpeningHours(lieu));
            return list;
        } else {
            list.add(new PushUps());
            list.add(new DeadLifts());
            list.add(new SnackDispenserGym());
            list.add(new AskOpeningHours(lieu));
            return list;
        }
    }


    public static List<AActivity> getMaisonActivities(InternshipQuestGame game) {
        List<AActivity> list = new ArrayList<>();
        list.add(new Sleep(game));
        list.add(new Wait());
        list.add(new PersonalProject());
        list.add(new DevWait16h());
        list.add(new EatFood());
        list.add(new LookFridge());
        list.add(new ListenRadio());
        list.add(new DisplayStat());
        return list;
    }

    public static List<AActivity> getCloverFieldActivities() {
        List<AActivity> list = new ArrayList<>();
        list.add(new FindClover());
        list.add(new Rest());
        return list;
    }

    public static List<AActivity> getEpitechActivities(ALieuVisitable lieu, Day day) {
        List<AActivity> list = new ArrayList<>();
        list.add(new Study());
        list.add(new ChessClub());
        if (day.getCodeEvent()==1){list.add(new MeetUp(lieu,day));}
        list.add(new SnackDispenserEpitech());
        list.add(new AskOpeningHours(lieu));
        return list;
    }

    public static List<AActivity> getBarActivities(ALieuVisitable lieu, InternshipQuestGame game) {
        List<AActivity> list = new ArrayList<>();
        list.add(new DrinkAlcool());
        list.add(new TalkToStranger(game));
        list.add(new AskOpeningHours(lieu));
        return list;
    }

    public static List<AActivity> getShopActivities(ALieuVisitable lieu, Day day) {
        List<AActivity> list = new ArrayList<>();
        if (day.getCodeEvent()==2){list.add(new BuyFoodPromotion());}
                list.add(new StoreWork());
        list.add(new BuyFood());
        list.add(new AskOpeningHours(lieu));
        return list;
    }

    public static List<AActivity> getSorcererActivities(ALieuVisitable lieu) {
        List<AActivity> list = new ArrayList<>();
        list.add(new Bewitchment());
        list.add(new AskOpeningHours(lieu));
        return list;
    }

}