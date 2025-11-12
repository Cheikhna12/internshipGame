package com.internshipquest.model.combat;

import com.internshipquest.model.AHero;
import com.internshipquest.model.Hero;

import java.util.Random;

public class Entretien {
    private int questionScore;
    private int reponseScore;
    private RH rh;
    private Hero hero;
    private boolean finEntretien;
    private int currentQuestionIndex;
    private static final int totalQuestions = 3;
    private String resultat;
    private Random random;

    private static final double COEFF_PRINCIPAL = 0.8;
    private static final double COEFF_SECONDAIRE = 0.2;


    public int questionTech(RH rh){
        double techContribution = rh.getNoteTech() * COEFF_PRINCIPAL;

        double softContribution = rh.getNoteSoftSkill() * COEFF_SECONDAIRE;

        int baseScore = (int) (techContribution + softContribution);

        int score = baseScore + random.nextInt(11) - 5;
        score = Math.max(0, Math.min(score, 100));

        this.questionScore += score;

        System.out.println("[ENTRETIEN] Question Tech posée - difficulté: " + score +
                " (Tech: " + (int)techContribution +
                " + Soft: " + (int)softContribution + ")");
        return score;
    }


    public int questionSoftSkill(RH rh){
        double softContribution = rh.getNoteSoftSkill() * COEFF_PRINCIPAL;

        double techContribution = rh.getNoteTech() * COEFF_SECONDAIRE;

        int baseScore = (int) (softContribution + techContribution);

        int score = baseScore + random.nextInt(11) - 5;
        score = Math.max(0, Math.min(score, 100));

        this.questionScore += score;

        System.out.println("[ENTRETIEN] Question SoftSkill posée - difficulté: " + score +
                " (Soft: " + (int)softContribution +
                " + Tech: " + (int)techContribution + ")");
        return score;
    }


    public int questionPiege(RH rh){
        int baseScore = (int) ((rh.getNoteTech() * COEFF_PRINCIPAL +
                rh.getNoteSoftSkill() * COEFF_SECONDAIRE) +
                (rh.getNoteSoftSkill() * COEFF_PRINCIPAL +
                        rh.getNoteTech() * COEFF_SECONDAIRE)) / 2;

        baseScore = (rh.getNoteTech() + rh.getNoteSoftSkill()) / 2;

        int bonusPiege = 15 + random.nextInt(11);

        int score = baseScore + bonusPiege;
        score = Math.max(0, Math.min(score, 150));

        this.questionScore += score;

        System.out.println("[ENTRETIEN] Question Piège posée - difficulté: " + score +
                " (Base: " + baseScore + " + Bonus: " + bonusPiege + ")");
        return score;
    }


    public int reponseTech(AHero hero){
        double codingContribution = hero.getCodingSkills() * COEFF_PRINCIPAL;

        double socialContribution = hero.getSocial() * COEFF_SECONDAIRE;

        int baseScore = (int) (codingContribution + socialContribution);

        int score = baseScore + random.nextInt(11) - 5;
        score = Math.max(0, Math.min(score, 100));

        this.reponseScore += score;

        System.out.println("[ENTRETIEN] Réponse Tech - Score: " + score +
                " (Coding: " + (int)codingContribution +
                " + Social: " + (int)socialContribution + ")");
        return score;
    }


    public int reponseSoftSkill(AHero hero){
        double socialContribution = hero.getSocial() * COEFF_PRINCIPAL;

        double codingContribution = hero.getCodingSkills() * COEFF_SECONDAIRE;

        int baseScore = (int) (socialContribution + codingContribution);

        int score = baseScore + random.nextInt(11) - 5;
        score = Math.max(0, Math.min(score, 100));

        this.reponseScore += score;

        System.out.println("[ENTRETIEN] Réponse Soft Skill - Score: " + score +
                " (Social: " + (int)socialContribution +
                " + Coding: " + (int)codingContribution + ")");
        return score;
    }


    public int reponseLuck(AHero hero){
        int baseScore = (hero.getCodingSkills() + hero.getSocial()) / 2;

        int luckBonus = random.nextInt(21) - 5; // Entre -5 et +15

        int score = baseScore + luckBonus;
        score = Math.max(0, Math.min(score, 100));

        this.reponseScore += score;

        System.out.println("[ENTRETIEN] Réponse Piège - Score: " + score +
                " (Base: " + baseScore + " + Luck: " + luckBonus + ")");
        return score;
    }

    public boolean verifFinEntretien(){
        if (currentQuestionIndex < totalQuestions){
            return false;
        }
        this.finEntretien = true;
        int seuilReussite = questionScore + rh.getBarreAcceptation();

        System.out.println("RÉSULTAT ENTRETIEN");
        System.out.println("Score Questions (difficulté): " + questionScore);
        System.out.println("Score Réponses (candidat): " + reponseScore);
        System.out.println("Barre d'acceptation: " + rh.getBarreAcceptation());
        System.out.println("Seuil de réussite: " + seuilReussite);

        if (reponseScore > seuilReussite) {
            this.resultat = "ACCEPTE";
            System.out.println("✓ Résultat: ACCEPTÉ");
            System.out.println("Marge de réussite: +" + (reponseScore - seuilReussite) + " points");
        } else {
            this.resultat = "REJECTE";
            System.out.println("✗ Résultat: REFUSÉ");
            System.out.println("Il manquait " + (seuilReussite - reponseScore) + " points");
        }
        return true;
    }

    // ici je vais écrire mes méthodes utilitaires
    public void nextQuestion(){
        if (currentQuestionIndex < totalQuestions){
            currentQuestionIndex++;
        }
    }

    public int getProgressPercentage(){
        return (currentQuestionIndex * 100) / totalQuestions;
    }

    public String getCurrentQuestionType() {
        switch (currentQuestionIndex) {
            case 0: return "TECH";
            case 1: return "SOFTSKILL";
            case 2: return "PIEGE";
            default: return "TERMINE";
        }
    }

    public int getQuestionScore() {
        return questionScore;
    }

    public int getReponseScore() {
        return reponseScore;
    }

    public RH getRh() {
        return rh;
    }

    public AHero getEtudiant() {
        return hero;
    }

    public boolean isFinEntretien() {
        return finEntretien;
    }

    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public String getResultat() {
        return resultat;
    }

    public boolean isAccepted() {
        return resultat != null && resultat.equals("ACCEPTE");
    }

    public boolean isRefused() {
        return resultat != null && resultat.equals("REJECTE");
    }
}