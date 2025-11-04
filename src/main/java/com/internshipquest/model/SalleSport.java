package com.internshipquest.model;

public class SalleSport {

    public SalleSport(){}

    public void Pompes(Hero hero){
        System.out.println("Votre énergie est de "+hero.getEnergie());
        System.out.println("Votre endurance est de "+hero.getEndurance());
        hero.setEndurance(hero.getEndurance()+3);
        hero.setEnergie(hero.getEnergie()-5);
        System.out.println("Après 1h de pompes, votre endurance a augmenté à "+hero.getEndurance()+" mais votre énergie a diminué à "+hero.getEnergie());
    }
}
