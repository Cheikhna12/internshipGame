package com.internshipquest.model.combat;

public class RH {
    private int noteTech;
    private int noteSoftSkill;
    private int niveauEnergie;
    private int barreAcceptation;
    private int seuilAcceptation;
    private String difficulte; 

    public RH(int noteTech, int noteSoftSkill, int barreAcceptation, int niveauEnergie, int seuilAcceptation) {
        this.noteTech = noteTech;
        this.noteSoftSkill = noteSoftSkill;
        this.barreAcceptation = barreAcceptation;
        this.niveauEnergie = niveauEnergie;
        this.seuilAcceptation = seuilAcceptation;
        this.difficulte = calculerDifficulte();
    }


    private String calculerDifficulte() {
        int moyenneStats = (noteTech + noteSoftSkill) / 2;

        if (moyenneStats < 40) {
            return "FACILE";
        } else if (moyenneStats < 70) {
            return "MOYEN";
        } else {
            return "DIFFICILE";
        }
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

    public String getDifficulte() {
        return difficulte;
    }

    
    public void setDifficulte(String difficulte) {
        this.difficulte = difficulte;
    }

    public void diminuerEnergie(int montant) {
        this.niveauEnergie = Math.max(0, this.niveauEnergie - montant);
    }
}