package com.internshipquest.model.activity;

import java.util.ArrayList;
import java.util.List;
import com.internshipquest.model.location.*;
import com.internshipquest.InternshipQuestGame;

public class ActivityFactory {

    public static List<AActivity> getFitnessActivities(ALieuVisitable lieu) {
        List<AActivity> list = new ArrayList<>();
        list.add(new PushUps());
        list.add(new DeadLifts());
        list.add(new SnackDispenserGym());
        list.add(new AskOpeningHours(lieu));
        return list;
    }


    public static List<AActivity> getMaisonActivities() {
        List<AActivity> list = new ArrayList<>();
        list.add(new Sleep());
        list.add(new Wait());
        list.add(new PersonalProject());
        list.add(new DevWait16h());
        list.add(new DisplayStat());
        return list;
    }

    public static List<AActivity> getCloverFieldActivities() {
        List<AActivity> list = new ArrayList<>();
        list.add(new FindClover());
        list.add(new Rest());
        return list;
    }

    public static List<AActivity> getEpitechActivities(ALieuVisitable lieu) {
        List<AActivity> list = new ArrayList<>();
        list.add(new Study());
        list.add(new ChessClub());
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

    public static List<AActivity> getShopActivities(ALieuVisitable lieu) {
        List<AActivity> list = new ArrayList<>();
        list.add(new AskOpeningHours(lieu));
        return list;
    }

    public static List<AActivity> getSorcererActivities(ALieuVisitable lieu) {
        List<AActivity> list = new ArrayList<>();
        list.add(new AskOpeningHours(lieu));
        return list;
    }
}