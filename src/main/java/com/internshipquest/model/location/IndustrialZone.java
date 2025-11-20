package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.Day;
import com.internshipquest.model.combat.Entreprise;
import com.internshipquest.model.combat.EntrepriseFactory;
import com.internshipquest.model.activity.AActivity;
import com.internshipquest.model.activity.ActivityFactory;
import com.internshipquest.model.activity.PostulerEntreprise;
import com.internshipquest.model.hero.*;
import com.internshipquest.utils.SoundManager;
import com.internshipquest.model.location.ALieuVisitable;
import java.util.ArrayList;
import java.util.List;


public class IndustrialZone extends ALieuVisitable{


    public IndustrialZone (InternshipQuestGame game){
        super(game);
        this.openHour = 7;
        this.closedHour = 19;
        this.openOnWeekends = false;
        this.activities = ActivityFactory.getIndustrialZoneActivities(game);
    }

    @Override
    public void onEnter(AHero hero, Day day) {
        SoundManager.playMusic("office", true, 0.6f);
    }


    @Override
    public void onExit() {
        SoundManager.stopMusic();
    }



    @Override
    public String getNpcMessage() {
        return "Bienvenue dans la zone industrielle. De nombreuses opportunités vous attendent ici.";
    }

//    public List<Entreprise> getEntreprises() {
//        return entreprises;
//    }


}

