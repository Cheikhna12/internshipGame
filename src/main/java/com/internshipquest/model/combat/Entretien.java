package com.internshipquest.model.combat;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.screens.GameWonScreen;
import com.internshipquest.screens.WorldMapScreen;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Entretien {
    private int scoreEntretien;
    private int questionScore;
    private int reponseScore;
    private RH rh;
    private AHero hero;
    private boolean finEntretien;
    private int currentQuestionIndex;
    private int totalQuestions; 
    private String resultat;
    private Random random;
  
    private List<Question> questionsList;
    private List<String> feedbackList;
    private List<String> feedbackTypeList; 
    private int minDifficulty;
    private int maxDifficulty;
    private int lastResponseScore;

    private static int COUT_QUESTION = 10;

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
        

        
        this.questionsList = new ArrayList<>();
        this.feedbackList = new ArrayList<>();
        this.feedbackTypeList = new ArrayList<>();
        this.lastResponseScore = 0;
        generateQuestions();
            }

    public int getScoreEntretien() {
        return scoreEntretien;
    }

    private int determineQuestionCount() {
        return rh.getNiveauEnergie() / COUT_QUESTION;
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

    private float rand(float min, float max) {
        return min + (float)Math.random() * (max - min);
    }

    private int calculateScoreQuestion(Question.QuestionType type) {
        float result;

        switch (type) {

            case TECH:
                result = rand(0.7f, 1.0f) * rh.getNoteTech()
                        + rand(0.0f, 0.2f) * rh.getNoteSoftSkill();
                break;

            case SOFTSKILL:
                result = rand(0.7f, 1.0f) * rh.getNoteSoftSkill()
                        + rand(0.0f, 0.2f) * rh.getNoteTech();
                break;

            case MIXED:
                result = rand(0.4f, 0.6f) * rh.getNoteSoftSkill()
                        + rand(0.4f, 0.6f) * rh.getNoteTech();
                break;

            case PIEGE:
                result = rand(0.5f, 0.6f) * rh.getNoteTech()
                        + rand(0.5f, 0.6f) * rh.getNoteSoftSkill();
                break;

            default:
                result = 50;
        }

        return (int)Math.min(100, result);
    }

    private int calculateTechResponse(Question question) {
        double codingContribution = hero.getCodingSkills() * rand(0.7f, 0.9f);
        double socialContribution = hero.getSocial() * rand(0.1f, 0.3f);
        double luckContribution = hero.getLuck() * rand(0f, 0.5f);
        
        int baseScore = (int) (codingContribution + socialContribution+ luckContribution);
        
        
        if (question.getType() == Question.QuestionType.TECH || 
            question.getType() == Question.QuestionType.MIXED) {
            baseScore += random.nextInt(10);
        }
        
        baseScore += random.nextInt(6);
        System.out.println("ScoreReponse = "+ baseScore);
        
        return Math.max(0, Math.min(baseScore, 100));
    }
    
    private int calculateSoftResponse(Question question) {
        double socialContribution = hero.getSocial() * rand(0.7f, 0.9f);
        double codingContribution = hero.getCodingSkills() * rand(0.1f, 0.3f);
        double luckContribution = hero.getLuck() * rand(0f, 0.5f);

        int baseScore = (int) (codingContribution + socialContribution+ luckContribution);
        
        
        if (question.getType() == Question.QuestionType.SOFTSKILL || 
            question.getType() == Question.QuestionType.MIXED) {
            baseScore += random.nextInt(10);
        }

        baseScore += random.nextInt(6);

        System.out.println("ScoreReponse = "+ baseScore);
        return Math.max(0, Math.min(baseScore, 100));
    }

    public boolean verifFinEntretien(InternshipQuestGame game){
        if (rh.getNiveauEnergie() >= COUT_QUESTION) {
            return false;
        }
        this.finEntretien = true;


        int seuilReussite = (int)((rh.getSeuilAcceptation() / 100.0) * totalQuestions);

        if (scoreEntretien >= seuilReussite) {
            this.resultat = "ACCEPTE";
            System.out.println("✓ Résultat: ACCEPTÉ");
            game.setScreen(new GameWonScreen(game));

        } else {
            this.resultat = "REJECTE";
            System.out.println("✗ Résultat: REFUSÉ");
        }
        return true;
    }


    
    public void nextQuestion(){
        if (currentQuestionIndex < totalQuestions){
            currentQuestionIndex++;
        }
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

        if (rh.getNiveauEnergie() < COUT_QUESTION) {
            finEntretien = true;
            return null;
        }

        rh.diminuerEnergie(COUT_QUESTION);

        Question question = getCurrentQuestion();
        if (question == null) {
            return null;
        }
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
        this.questionScore=calculateScoreQuestion(question.getType());
        System.out.println("question Score = "+ questionScore );

        if (questionScore < responseScore) {
            scoreEntretien += 1;
        }
        
        
        String feedback = generateFeedback(question, responseType,questionScore, responseScore);
        feedbackList.add(feedback);
        
        
        String feedbackType = determineFeedbackType(questionScore,responseScore);
        feedbackTypeList.add(feedbackType);

        nextQuestion();
    }
    
    private String generateFeedback(Question question, String responseType, int scoreQuestion, int scoreReponse) {
        String[] positiveReactions = {
                "The recruiter nods approvingly",
                "You see a smile appear on his face.",
                "He takes notes with interest.",
                "Interesting...’ he murmurs.",
                "He seems impressed by your answer."
        };
        
        String[] neutralReactions = {
            "The recruiter remains impassive.",
            "He takes a few notes without comment.",
            "'I see...’ he simply says.",
            "He listens to you attentively.",
            "His expression remains neutral."
        };
        
        String[] negativeReactions = {
            "The recruiter frowns slightly.",
            "He seems a little disappointed.",
            "'Hmm...’ he says doubtfully.",
            "He quickly jots something down.",
            "His gaze becomes more judgemental."
        };

        if (scoreReponse-scoreQuestion >= 10) {
            return positiveReactions[random.nextInt(positiveReactions.length)];
        } else if (scoreReponse-scoreQuestion > -10 && scoreReponse-scoreQuestion < 10) {
            return neutralReactions[random.nextInt(neutralReactions.length)];
        } else {
            return negativeReactions[random.nextInt(negativeReactions.length)];
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
    
    private String determineFeedbackType(int scoreQuestion, int scoreReponse) {
        if (scoreReponse-scoreQuestion >= 10) {
            return "POSITIVE";
        } else if (scoreReponse-scoreQuestion > -10 && scoreReponse-scoreQuestion < 10) {
            return "NEUTRAL";
        } else {
            return "NEGATIVE";
        }
    }
}
