package com.internshipquest.model.combat;

public class RH {
    private int noteTech ;
    private int noteSoftSkill ;
    private int niveauEnergie;
    private int barreAcceptation;
    private int seuilAcceptation;


    public RH(int noteTech, int noteSoftSkill, int barreAcceptation, int niveauEnergie, int seuilAcceptation) {
        this.noteTech = noteTech;
        this.noteSoftSkill = noteSoftSkill;
        this.barreAcceptation = barreAcceptation;
        this.niveauEnergie = niveauEnergie;
        this.seuilAcceptation = seuilAcceptation;
    }

    public int getNoteTech() {
        return noteTech;
    }

    public int getNoteSoftSkill() {
        return noteSoftSkill;
    }

    public int getNiveauEnergie() {
        return niveauEnergie;
    }

    public int getBarreAcceptation() {
        return barreAcceptation;
    }

    public int getSeuilAcceptation() {
        return seuilAcceptation;
    }
}