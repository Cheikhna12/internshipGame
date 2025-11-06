package com.internshipquest.model.activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityFactory {

    public static List<AActivity> getFitnessActivities() {
        List<AActivity> list = new ArrayList<>();
        list.add(new PushUps());
        list.add(new DeadLifts());
        return list;
    }
}