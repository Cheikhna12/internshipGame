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
    public Entreprise(String name, String description, int noteTech, int noteSoftSkill, String difficulte) {
        this.name = name;
        this.noteTech = noteTech;
        this.noteSoftSkill = noteSoftSkill;
        this.salaire = this.noteTech*100+this.noteSoftSkill;
        this.description = description;
        this.difficulte = difficulte;
    }
    public RH createRh(){
    int seuilAcceptation;
    int niveauEnergie;

    switch (difficulte) {
        case "Facile":
            seuilAcceptation = 40;
            niveauEnergie = 30 + (int)(Math.random() * 10);
            break;

        case "Moyen":
            seuilAcceptation = 60;
            niveauEnergie = 40 + (int)(Math.random() * 10);
            break;

        case "Difficile":
            seuilAcceptation = 75;
            niveauEnergie = 50 + (int)(Math.random() * 10);
            break;

        case "Extreme":
            seuilAcceptation = 90;
            niveauEnergie = 60 + (int)(Math.random() * 10);
            break;

        default:

            seuilAcceptation = 50;
            niveauEnergie = 70;
            break;
    }
    return new RH (
            this.noteTech,
            this.noteSoftSkill,
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

    public void setDejaPostule(boolean dejaPostule) {
        this.dejaPostule = dejaPostule;
    }
}





