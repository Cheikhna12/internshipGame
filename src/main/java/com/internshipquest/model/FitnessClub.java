package com.internshipquest.model;

public class FitnessClub {

    public FitnessClub(){}

    public void pushUps(Hero hero){
        System.out.println("Your energy is at "+hero.getEnergy());
        System.out.println("Your endurance is at "+hero.getEndurance());
        hero.setEndurance(hero.getEndurance()+3);
        hero.setEnergy(hero.getEnergy()-5);
        System.out.println("After 1 hour of push-ups, your endurance has increased to"+hero.getEndurance()+" but your energy decrease to "+hero.getEnergy());
    }
}
