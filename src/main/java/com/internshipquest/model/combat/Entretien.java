package com.internshipquest.model.combat;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.screens.GameOverScreen;
import com.internshipquest.screens.WorldMapScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Entretien {
    private int questionScore;
    private int reponseScore;
    private RH rh;
    private AHero hero;
    private boolean finEntretien;
    private int currentQuestionIndex;
    private int totalQuestions; 
    private String resultat;
    private Random random;
    private InternshipQuestGame game;
    
    
    private int rhMood; 
    private String ambiance; 
    private List<Question> questionsList;
    private List<String> feedbackList;
    private List<String> feedbackTypeList; 
    private int minDifficulty;
    private int maxDifficulty;
    private int lastResponseScore; 

    private static final double COEFF_PRINCIPAL = 0.8;
    private static final double COEFF_SECONDAIRE = 0.2;

    public Entretien(RH rh, AHero hero) {
        this.rh = rh;
        this.hero = hero;
        this.random = new Random();

        this.questionScore = 0;
        this.reponseScore = 0;
        this.currentQuestionIndex = 0;
        this.finEntretien = false;
        this.resultat = "EN_COURS";
        
        
        this.totalQuestions = determineQuestionCount();
        
        
        this.rhMood = 50 + random.nextInt(30); 
        this.ambiance = determineAmbiance();
        
        
        determineDifficultyRange();
        
        
        this.questionsList = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
        this.feedbackTypeList = new ArrayList<>();
        this.lastResponseScore = 0;
        generateQuestions();
        
        System.out.println("[ENTRETIEN] Initialisé: " + totalQuestions + " questions, Ambiance: " + ambiance);
    }
    
    private int determineQuestionCount() {
        String diff = rh.getDifficulte();
        switch (diff) {
            case "FACILE":
                return 3 + random.nextInt(2); 
            case "MOYEN":
                return 4 + random.nextInt(2); 
            case "DIFFICILE":
                return 5 + random.nextInt(2); 
            default:
                return 3;
        }
    }
    
    private String determineAmbiance() {
        if (rhMood < 60) {
            return "TENDUE";
        } else if (rhMood < 75) {
            return "NEUTRE";
        } else {
            return "DETENDUE";
        }
    }


    private void determineDifficultyRange() {
        String diff = rh.getDifficulte();
        switch (diff) {
            case "FACILE":
                minDifficulty = 1;
                maxDifficulty = 4;
                break;
            case "MOYEN":
                minDifficulty = 3;
                maxDifficulty = 6;
                break;
            case "DIFFICILE":
                minDifficulty = 5;
                maxDifficulty = 9;
                break;
            default:
                minDifficulty = 1;
                maxDifficulty = 5;
        }
    }
    
    private void generateQuestions() {
        
        List<Question.QuestionType> types = new ArrayList<>();
        
        
        int techCount = (int)(totalQuestions * 0.4);
        int softCount = (int)(totalQuestions * 0.4);
        int remaining = totalQuestions - techCount - softCount;
        
        for (int i = 0; i < techCount; i++) types.add(Question.QuestionType.TECH);
        for (int i = 0; i < softCount; i++) types.add(Question.QuestionType.SOFTSKILL);
        
        
        for (int i = 0; i < remaining; i++) {
            if (random.nextBoolean()) {
                types.add(Question.QuestionType.MIXED);
            } else {
                types.add(Question.QuestionType.PIEGE);
            }
        }
        
        
        for (int i = types.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Question.QuestionType temp = types.get(i);
            types.set(i, types.get(j));
            types.set(j, temp);
        }
        
        
        for (Question.QuestionType type : types) {
            Question q = QuestionBank.getRandomQuestion(type, minDifficulty, maxDifficulty);
            questionsList.add(q);
        }
    }

    private int calculateQuestionDifficulty(Question question) {
        int baseDifficulty = question.getDifficulty() * 10; 
        
        
        switch (question.getType()) {
            case TECH:
                baseDifficulty += rh.getNoteTech() / 5;
                break;
            case SOFTSKILL:
                baseDifficulty += rh.getNoteSoftSkill() / 5;
                break;
            case PIEGE:
                baseDifficulty += 15; 
                break;
            case MIXED:
                baseDifficulty += (rh.getNoteTech() + rh.getNoteSoftSkill()) / 10;
                break;
        }
        
        
        baseDifficulty += random.nextInt(11) - 5;
        
        return Math.max(10, Math.min(baseDifficulty, 100));
    }

    private int calculateTechResponse(Question question) {
        double codingContribution = hero.getCodingSkills() * COEFF_PRINCIPAL;
        double socialContribution = hero.getSocial() * COEFF_SECONDAIRE;
        
        int baseScore = (int) (codingContribution + socialContribution);
        
        
        if (question.getType() == Question.QuestionType.TECH || 
            question.getType() == Question.QuestionType.MIXED) {
            baseScore += 10;
        }
        
        
        baseScore += random.nextInt(11) - 5;
        
        
        baseScore += (rhMood - 65) / 10;
        
        return Math.max(0, Math.min(baseScore, 100));
    }
    
    private int calculateSoftResponse(Question question) {
        double socialContribution = hero.getSocial() * COEFF_PRINCIPAL;
        double codingContribution = hero.getCodingSkills() * COEFF_SECONDAIRE;
        
        int baseScore = (int) (socialContribution + codingContribution);
        
        
        if (question.getType() == Question.QuestionType.SOFTSKILL || 
            question.getType() == Question.QuestionType.MIXED) {
            baseScore += 10;
        }
        
        
        baseScore += random.nextInt(11) - 5;
        
        
        baseScore += (rhMood - 65) / 10;
        
        return Math.max(0, Math.min(baseScore, 100));
    }

    public boolean verifFinEntretien(){
        if (currentQuestionIndex < totalQuestions){
            return false;
        }
        this.finEntretien = true;
        
        
        int seuilReussite = questionScore + rh.getBarreAcceptation();
        
        
        int ambianceBonus = 0;
        if (ambiance.equals("DETENDUE")) {
            ambianceBonus = 5;
        } else if (ambiance.equals("TENDUE")) {
            ambianceBonus = -5;
        }
        
        int finalScore = reponseScore + ambianceBonus;

        System.out.println("\n========== RÉSULTAT ENTRETIEN ==========");
        System.out.println("Score Questions (difficulté): " + questionScore);
        System.out.println("Score Réponses (candidat): " + reponseScore);
        System.out.println("Ambiance: " + ambiance + " (" + (ambianceBonus >= 0 ? "+" : "") + ambianceBonus + ")");
        System.out.println("Score Final: " + finalScore);
        System.out.println("Barre d'acceptation: " + rh.getBarreAcceptation());
        System.out.println("Seuil de réussite: " + seuilReussite);

        if (finalScore >= seuilReussite) {
            this.resultat = "ACCEPTE";
            System.out.println("✓ Résultat: ACCEPTÉ");
            System.out.println("Marge de réussite: +" + (finalScore - seuilReussite) + " points");



            try {
                Thread.sleep( 1000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
            }

            game.setScreen(new GameOverScreen(game,  new WorldMapScreen(game)));
        } else {
            this.resultat = "REJECTE";
            System.out.println("✗ Résultat: REFUSÉ");
            System.out.println("Il manquait " + (seuilReussite - finalScore) + " points");
        }
        System.out.println("========================================\n");
        return true;
    }

    
    
    public void nextQuestion(){
        if (currentQuestionIndex < totalQuestions){
            currentQuestionIndex++;
        }
    }

    public int getProgressPercentage(){
        return (currentQuestionIndex * 100) / totalQuestions;
    }

    public Question getCurrentQuestion() {
        if (currentQuestionIndex >= questionsList.size()) {
            return null;
        }
        return questionsList.get(currentQuestionIndex);
    }
    
    public String getCurrentQuestionType() {
        Question q = getCurrentQuestion();
        if (q == null) return "TERMINE";
        return q.getType().toString();
    }

    public Question poserProchaineQuestion() {
        Question question = getCurrentQuestion();
        if (question == null) {
            return null;
        }
        
        
        int difficulty = calculateQuestionDifficulty(question);
        questionScore += difficulty;
        
        System.out.println("[ENTRETIEN] Question " + (currentQuestionIndex + 1) + "/" + totalQuestions);
        System.out.println("  Type: " + question.getType() + ", Difficulté: " + difficulty);
        System.out.println("  Question: " + question.getText());
        
        return question;
    }

    public void repondreQuestion(int choiceIndex) {
        Question question = getCurrentQuestion();
        if (question == null) return;
        
        int responseScore = 0;
        String responseType = "";
        
        switch (choiceIndex) {
            case 0: 
                responseScore = calculateTechResponse(question);
                responseType = "TECH";
                break;
            case 1: 
                responseScore = calculateSoftResponse(question);
                responseType = "SOFT";
                break;
        }
        
        this.reponseScore += responseScore;
        this.lastResponseScore = responseScore;
        
        
        String feedback = generateFeedback(question, responseType, responseScore);
        feedbackList.add(feedback);
        
        
        String feedbackType = determineFeedbackType(responseScore);
        feedbackTypeList.add(feedbackType);
        
        
        adjustRHMood(responseScore);
        
        System.out.println("[ENTRETIEN] Réponse " + responseType + " - Score: " + responseScore);
        System.out.println("[ENTRETIEN] Feedback: " + feedback);
        System.out.println("[ENTRETIEN] Humeur RH: " + rhMood + "/100\n");
        
        
        nextQuestion();
    }
    
    private String generateFeedback(Question question, String responseType, int score) {
        String[] positiveReactions = {
            "Le recruteur hoche la tête avec approbation.",
            "Vous voyez un sourire se dessiner sur son visage.",
            "Il prend des notes avec intérêt.",
            "'Intéressant...', murmure-t-il.",
            "Il semble impressionné par votre réponse."
        };
        
        String[] neutralReactions = {
            "Le recruteur reste impassible.",
            "Il prend quelques notes sans commentaire.",
            "'Je vois...', dit-il simplement.",
            "Il vous écoute attentivement.",
            "Son expression reste neutre."
        };
        
        String[] negativeReactions = {
            "Le recruteur fronce légèrement les sourcils.",
            "Il semble un peu déçu.",
            "'Hmm...', fait-il d'un air dubitatif.",
            "Il note quelque chose rapidement.",
            "Son regard devient plus critique."
        };
        
        if (score >= 70) {
            return positiveReactions[random.nextInt(positiveReactions.length)];
        } else if (score >= 50) {
            return neutralReactions[random.nextInt(neutralReactions.length)];
        } else {
            return negativeReactions[random.nextInt(negativeReactions.length)];
        }
    }
    
    private void adjustRHMood(int responseScore) {
        if (responseScore >= 70) {
            rhMood = Math.min(100, rhMood + 5);
            
            if (rhMood >= 75 && ambiance.equals("NEUTRE")) {
                ambiance = "DETENDUE";
            } else if (rhMood >= 60 && ambiance.equals("TENDUE")) {
                ambiance = "NEUTRE";
            }
        } else if (responseScore < 40) {
            rhMood = Math.max(0, rhMood - 5);
            
            if (rhMood < 60 && ambiance.equals("DETENDUE")) {
                ambiance = "NEUTRE";
            } else if (rhMood < 50 && ambiance.equals("NEUTRE")) {
                ambiance = "TENDUE";
            }
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
    
    public int getRhMood() {
        return rhMood;
    }
    
    public String getAmbiance() {
        return ambiance;
    }
    
    public String getLastFeedback() {
        if (feedbackList.isEmpty()) return "";
        return feedbackList.get(feedbackList.size() - 1);
    }
    
    public List<String> getAllFeedbacks() {
        return new ArrayList<>(feedbackList);
    }
    
    public String getLastFeedbackType() {
        if (feedbackTypeList.isEmpty()) return "NEUTRAL";
        return feedbackTypeList.get(feedbackTypeList.size() - 1);
    }
    
    public int getLastResponseScore() {
        return lastResponseScore;
    }
    
    private String determineFeedbackType(int score) {
        if (score >= 70) {
            return "POSITIVE";
        } else if (score >= 50) {
            return "NEUTRAL";
        } else {
            return "NEGATIVE";
        }
    }
}
