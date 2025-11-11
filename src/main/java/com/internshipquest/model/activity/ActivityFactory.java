package com.internshipquest.model.activity;

import java.util.ArrayList;
import java.util.List;
import com.internshipquest.model.location.*;

public class ActivityFactory {

    public static List<AActivity> getFitnessActivities(ALieuVisitable lieu) {
        List<AActivity> list = new ArrayList<>();
        list.add(new PushUps());
        list.add(new DeadLifts());
        list.add(new SnackDispenser());
        list.add(new AskOpeningHours(lieu));
        return list;
    }


    public static List<AActivity> getMaisonActivities() {
        List<AActivity> list = new ArrayList<>();
        list.add(new Sleep());
        list.add(new Wait());
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

    public static List<AActivity> getEpitechActivities() {
        List<AActivity> list = new ArrayList<>();
        list.add(new Study());
        return list;
    }

    public static List<AActivity> getBarActivities() {
        List<AActivity> list = new ArrayList<>();
        return list;
    }

    public static List<AActivity> getShopActivities() {
        List<AActivity> list = new ArrayList<>();
        return list;
    }

    public static List<AActivity> getSorcererActivities() {
        List<AActivity> list = new ArrayList<>();
        return list;
    }
}