package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.combat.Entreprise;
import com.internshipquest.model.combat.Entretien;
import com.internshipquest.model.combat.Question;
import com.internshipquest.model.combat.RH;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.utils.SoundManager;


public class EntretienScreen implements Screen {
    

    private enum InterviewState{
        QUESTIONING,
        FEEDBACK,
        SHOWING_RESULT,
        FINISHED
    }
    

    private InternshipQuestGame game;
    private AHero hero;
    private RH rh;
    private Entreprise entreprise;
    private Entretien entretien;
    private Screen returnScreen;


    private Texture background;
    private Texture rhNeutral;
    private Texture rhHappy;
    private Texture rhSad;
    private Texture rhTexture;


    private InterviewState currentstate;
    private Question currentQuestion;
    private String[] responseOptions;
    private String resultMessage;
    private String feedbackMessage;
    private float timer = 0f;
    private int hoveredChoice = -1;


    public EntretienScreen(InternshipQuestGame game, Entreprise entreprise, Screen returnScreen) {
        this.game = game;
        this.hero = game.getHero();
        this.entreprise = entreprise;
        this.returnScreen = returnScreen;
        
        this.rh = entreprise.createRh();
        
        this.entretien = new Entretien(rh, hero);
        

            this.background = new Texture(Gdx.files.internal("assets/images/office_background.png"));

        
            this.rhTexture = new Texture(Gdx.files.internal("assets/RH_Neutral.png"));


        this.responseOptions = new String[2]; 
        this.feedbackMessage = "";
    }

    @Override
    public void show() {
        SoundManager.playMusic("office", true, 0.5f);
        nextQuestion();

        rhNeutral = new Texture(Gdx.files.internal("assets/RH_Neutral.png"));
        rhHappy   = new Texture(Gdx.files.internal("assets/RH_Happy.png"));
        rhSad     = new Texture(Gdx.files.internal("assets/RH_Angry.png"));
    }

    private void nextQuestion() {
        if (entretien.verifFinEntretien()) {
            
            if (entretien.isAccepted()) {
                resultMessage = "FELICITATIONS ! Vous etes embauche !\n\nSalaire: +" + entreprise.getSalaire() + " euros";
                try {
                    SoundManager.playSound("hired", 1.0f);
                } catch (Exception e) {
                    System.out.println("[ENTRETIEN] Erreur son hired: " + e.getMessage());
                }
                
                hero.setMoney(hero.getMoney() + entreprise.getSalaire());
                hero.setEndurance(Math.min(100, hero.getEndurance() + 5));
                hero.setSocial(Math.min(100, hero.getSocial() + 3));

            } else {
                resultMessage = "Desole, votre profil ne correspond pas a nos attentes...\n\nStress: +10";

                try {
                    SoundManager.playSound("rejected", 1.0f);
                } catch (Exception e) {
                    System.out.println("[ENTRETIEN] Erreur son rejected: " + e.getMessage());
                }
                
                hero.setStress(Math.min(100, hero.getStress() + 10));
                
                System.out.println("[ENTRETIEN] Refusé. Stress: +10");
            }

            entreprise.setDejaPostule(true);
            currentstate = InterviewState.SHOWING_RESULT;
            timer = 0f;
        } else {
            currentQuestion = entretien.poserProchaineQuestion();
            updateResponseOptions();
            currentstate = InterviewState.QUESTIONING;
        }
    }

    private void updateResponseOptions() {
        if (currentQuestion == null) return;
        
        responseOptions[0] = "[TECH] " + currentQuestion.getOptionTech();
        responseOptions[1] = "[SOFT] " + currentQuestion.getOptionSoft();
    }
    

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();

        if (background != null) {
            game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }
        
        if (rhTexture != null) {
            game.batch.draw(rhTexture, 600, 0, 760, 760);

        }

        game.font.setColor(1f, 0.8f, 0f, 1f);
        game.font.getData().setScale(1.2f);
        game.font.draw(game.batch, "Entretien chez : " + entreprise.getName(), 50, 900);
        game.font.getData().setScale(1.0f);


        switch (currentstate) {
            case QUESTIONING:
                renderQuestioningState();
                break;

            case FEEDBACK:
                renderFeedbackState(delta);
                break;
                
            case SHOWING_RESULT:
                renderResultState(delta);
                break;

            case FINISHED:
                break;
        }
        game.batch.end();
        handleInput();
        if (currentstate == InterviewState.FINISHED) {
            game.setScreen(returnScreen);
        }

    }
    
    private void renderQuestioningState() {
        
        game.font.getData().setScale(1.0f);
        game.font.setColor(0f, 0.7f, 1f, 1f);
        drawWrappedText(currentQuestion.getText(), 150, 750, 600);
        
        game.font.getData().setScale(0.9f);
        for (int i = 0; i < 2; i++) {
            int yPos = 460 - (i * 100);
            
            if (hoveredChoice == i) {
                game.font.setColor(0.3f, 1f, 0.3f, 1f);
            } else {
                if (i == 0) {
                    game.font.setColor(0.5f, 0.8f, 1f, 1f);
                } else {
                    game.font.setColor(1f, 0.8f, 0.5f, 1f);
                }
            }
            
            drawWrappedText(responseOptions[i], 70, yPos, 850);
        }
    }
    
    private void renderFeedbackState(float delta) {
        String feedbackType = entretien.getLastFeedbackType();
        Color feedbackColor = getFeedbackColor(feedbackType);

        game.font.getData().setScale(1.1f);
        game.font.setColor(1f, 1f, 0.8f, 1f);
        game.font.draw(game.batch, "Reaction du recruteur:", 50, 640);
        
        game.font.getData().setScale(1.0f);
        game.font.setColor(0.9f, 0.9f, 0.9f, 1f);
        drawWrappedText(feedbackMessage, 50, 590, 900);

        timer += delta;
        
        if (timer >= 2.0f) {
            timer = 0f;
            nextQuestion();
        }
    }
    
    private Color getFeedbackColor(String feedbackType) {
        switch (feedbackType) {
            case "POSITIVE":
                return new Color(0.3f, 1f, 0.3f, 1f);
            case "NEGATIVE":
                return new Color(1f, 0.3f, 0.3f, 1f);
            default:
                return new Color(1f, 0.9f, 0.3f, 1f);
        }
    }
    
    private void renderResultState(float delta) {
        game.font.getData().setScale(1.5f);
        
        if (entretien.isAccepted()) {
            game.font.setColor(0.3f, 1f, 0.3f, 1f);
        } else {
            game.font.setColor(1f, 0.3f, 0.3f, 1f);
        }
        
        drawWrappedText(resultMessage, 50, 650, 900);
        
        game.font.getData().setScale(1.0f);
        game.font.setColor(1f, 1f, 1f, 1f);
        game.font.draw(game.batch, "Score entretien: " + entretien.getScoreEntretien() + " / " + entretien.getTotalQuestions(), 50, 480);
        game.font.draw(game.batch, "Nombre de questions: " + entretien.getTotalQuestions(), 50, 450);
        
        timer += delta;
        
        if (timer > 4.0f) {
            currentstate = InterviewState.FINISHED;
        }
    }
    
//    private String getAmbianceColor(String ambiance) {
//        switch (ambiance) {
//            case "TENDUE":
//                return "FF5555FF";
//            case "NEUTRE":
//                return "FFAA55FF";
//            case "DETENDUE":
//                return "55FF55FF";
//            default:
//                return "FFFFFFFF";
//        }
//    }
    
    private Color getQuestionTypeColor(String type) {
        switch (type) {
            case "TECH":
                return new Color(0.5f, 0.8f, 1f, 1f); 
            case "SOFTSKILL":
                return new Color(1f, 0.8f, 0.5f, 1f); 
            case "PIEGE":
                return new Color(1f, 0.3f, 0.3f, 1f); 
            case "MIXED":
                return new Color(0.8f, 0.5f, 1f, 1f); 
            default:
                return new Color(1f, 1f, 1f, 1f); 
        }
    }

    private void handleInput() {
        if (currentstate != InterviewState.QUESTIONING) return;
        
        int choiceIndex = -1;

        int mouseX = Gdx.input.getX();
        int mouseY = Gdx.graphics.getHeight() - Gdx.input.getY();
        
        hoveredChoice = -1;
        for (int i = 0; i < 2; i++) {
            int yPos = 460 - (i * 100);
            if (mouseX > 70 && mouseX < 950 && mouseY > yPos - 70 && mouseY < yPos + 20) {
                hoveredChoice = i;
                break;
            }
        }
        
        if (Gdx.input.justTouched() && hoveredChoice != -1) {
            choiceIndex = hoveredChoice;
        }
        
        if (choiceIndex != -1) {
            System.out.println("[ENTRETIEN] Réponse choisie: " + (choiceIndex == 0 ? "TECH" : "SOFT"));
            
            entretien.repondreQuestion(choiceIndex);

            feedbackMessage = entretien.getLastFeedback();
            String feedbackType = entretien.getLastFeedbackType();
            updateRhFace();
            
            try {
                switch (feedbackType) {
                    case "POSITIVE":
                        SoundManager.playSound("correct", 0.7f);
                        break;
                    case "NEGATIVE":
                        SoundManager.playSound("wrong", 0.5f);
                        break;
                    default:
                        SoundManager.playSound("click", 0.4f);
                        break;
                }
            } catch (Exception e) {
                System.out.println("[ENTRETIEN] Erreur son feedback: " + e.getMessage());
            }
            
            currentstate = InterviewState.FEEDBACK;
            timer = 0f;
        }
    }

    private void updateRhFace() {
        String feedbackType = entretien.getLastFeedbackType();

        switch (feedbackType) {
            case "POSITIVE":
                rhTexture = rhHappy;
                break;
            case "NEGATIVE":
                rhTexture = rhSad;
                break;
            default:
                rhTexture = rhNeutral;
                break;
        }
    }
    

    private void drawWrappedText(String text, float x, float y, float maxWidth) {
        String[] words = text.split(" ");
        StringBuilder line = new StringBuilder();
        float currentY = y;
        
        for (String word : words) {
            String testLine = line + word + " ";
            float estimatedWidth = testLine.length() * 8;
            
            if (estimatedWidth > maxWidth && line.length() > 0) {
                game.font.draw(game.batch, line.toString(), x, currentY);
                currentY -= 25;
                line = new StringBuilder(word + " ");
            } else {
                line.append(word).append(" ");
            }
        }
        
        if (line.length() > 0) {
            game.font.draw(game.batch, line.toString(), x, currentY);
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose() {
        if (background != null) background.dispose();
        if (rhNeutral != null) rhNeutral.dispose();
        if (rhHappy != null) rhHappy.dispose();
        if (rhSad != null) rhSad.dispose();
    }
}
