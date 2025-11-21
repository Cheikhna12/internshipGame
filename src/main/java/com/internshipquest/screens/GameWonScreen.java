package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.utils.SoundManager;
import com.internshipquest.model.hero.*;
import com.internshipquest.model.Day;
import com.internshipquest.model.combat.*;

public class GameWonScreen implements Screen{

    private final InternshipQuestGame game;

    private Stage stage;
    private Skin skin;
    private Label gameOverLabel;
    private AHero hero;
    private Day day;
    private final Texture background;
    private int bonusScore=0;
    private int Score = 0;

    public GameWonScreen(InternshipQuestGame game) {
        this.game = game;
        this.hero = game.getHero();
        this.day= game.getDay();
        background = new Texture(Gdx.files.internal("assets/images/won.png"));
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }


    public int calculateScore() {
        this.bonusScore=0;
        if(hero.hasGoneToChessClub){
            this.bonusScore += 45;
        }
        if(hero.hasHadFunTime){
            this.bonusScore += 350;
        }
        if(hero.hasPaidLicence){
            this.bonusScore += 35;
        }
        if(hero.hasGoneToMeetUp){
            this.bonusScore += 50;
        }
        if(hero.hasFoundClover){
            this.bonusScore += 100;
        }
        if(hero.hasBeenBewitched){
            this.bonusScore += 180;
        }
        if(hero.hasStudied){
            this.bonusScore +=  50;
        }
        if(hero.hasHadSNacks){
            this.bonusScore +=  15;
        }
        if(hero.hasWorkedOnPersonalProjects){
            this.bonusScore +=  25;
        }
        if(hero.hasDrunk){
            this.bonusScore +=  5;
        }
        if(hero.hasCarrefoured){
            this.bonusScore += 50;
        }
        if(hero.hasListenedToRadio){
            this.bonusScore +=  5;
        }
        if(hero.hasDeadLifted){
            this.bonusScore +=  45;
        }

        bonusScore +=(30-day.getDay())*100;


        Score = (hero.getSocial() + hero.getMoney()/3 + hero.getLuck() +
                hero.getEndurance() + hero.getCodingSkills()) + this.bonusScore;


        return Score;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

       stage.act(delta);
      stage.draw();

        game.batch.begin();
        game.font.draw(game.batch, "You win !! " + hero.getName(), 500, 500);
        game.font.draw(game.batch, "Your final stats are: " , 40, 940);
        game.font.draw(game.batch, "Money: " + hero.getMoney()/3, 40, 890);
        game.font.draw(game.batch, "Endurance: " + hero.getEndurance(), 40, 840);
        game.font.draw(game.batch, "Coding Skills: " + hero.getCodingSkills(), 40, 790);
        game.font.draw(game.batch, "Luck: " + hero.getLuck(), 40, 740);
        game.font.draw(game.batch, "Social: " + hero.getSocial(), 40, 690);
        game.font.draw(game.batch, "Bonus Score: " + this.bonusScore, 40, 640);
        game.font.draw(game.batch, "Your final score is: " + calculateScore() , 40, 590);


        SpriteBatch batch = (SpriteBatch) stage.getBatch();
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
        game.batch.end();

    }

    @Override public void resize(int width, int height){}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}

}