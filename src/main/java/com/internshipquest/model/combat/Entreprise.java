package com.internshipquest.model.combat;

import com.internshipquest.model.hero.AHero;

public class Entreprise {

    private String name;
    private int noteTech;
    private int noteSoftSkill;
    private int salaire;
    private boolean dejaPostule;
    private String description;
    private String difficulte;
    public Entreprise(String name, String description, int noteTech, int noteSoftSkill, int salaire , String difficulte) {
        this.name = name;
        this.noteTech = noteTech;
        this.noteSoftSkill = noteSoftSkill;
        this.salaire = salaire;
        this.description = description;
        this.difficulte = difficulte;
    }
    public RH createRh(){
    int barreAcceptation;
    int seuilAcceptation;
    int niveauEnergie;

    switch (difficulte) {
        case "Facile":
            barreAcceptation = 50;
            seuilAcceptation = 40;
            niveauEnergie = 60 + (int)(Math.random() * 20); // 60-80
            break;

        case "Moyen":
            barreAcceptation = 70;
            seuilAcceptation = 60;
            niveauEnergie = 70 + (int)(Math.random() * 20); // 70-90
            break;

        case "Difficile":
            barreAcceptation = 85;
            seuilAcceptation = 75;
            niveauEnergie = 80 + (int)(Math.random() * 15); // 80-95
            break;

        case "Extreme":
            barreAcceptation = 95;
            seuilAcceptation = 90;
            niveauEnergie = 90 + (int)(Math.random() * 10); // 90-100
            break;

        default:
            barreAcceptation = 60;
            seuilAcceptation = 50;
            niveauEnergie = 70;
            break;
    }
    return new RH (
            this.noteTech,
            this.noteSoftSkill,
            barreAcceptation,
            niveauEnergie,
            seuilAcceptation
    );


    }

    public String getName() { return name; }
    public int getNoteTech() { return noteTech; }
    public int getNoteSoftSkill() { return noteSoftSkill; }
    public int getSalaire() { return salaire; }
    public boolean isDejaPostule() { return dejaPostule; }
    public String getDifficulte() { return difficulte; }

    // Setters
    public void setDejaPostule(boolean dejaPostule) {
        this.dejaPostule = dejaPostule;
    }
}





