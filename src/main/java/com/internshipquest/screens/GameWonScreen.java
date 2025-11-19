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
import com.internshipquest.model.combat.*;

public class GameWonScreen implements Screen{

    private final InternshipQuestGame game;

    private Stage stage;
    private Skin skin;
    private Label gameOverLabel;
    private Screen previousScreen;
    private AHero hero;
    private final Texture background;

    public GameWonScreen(InternshipQuestGame game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;
        this.hero = game.getHero();
        background = new Texture(Gdx.files.internal("assets/images/won.png"));
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            game.setScreen(previousScreen);
        }

        stage.act(delta);
        stage.draw();

        game.batch.begin();
        game.font.draw(game.batch, "You win !! " + hero.getName(), 500, 500);
        game.font.draw(game.batch, "Your final stats are: " , 40, 940);
        game.font.draw(game.batch, "Money: " + hero.getMoney(), 40, 890);
        game.font.draw(game.batch, "Endurance: " + hero.getEndurance(), 40, 840);
        game.font.draw(game.batch, "Coding Skills: " + hero.getCodingSkills(), 40, 790);
        game.font.draw(game.batch, "Luck: " + hero.getLuck(), 40, 740);
        game.font.draw(game.batch, "Social: " + hero.getSocial(), 40, 690);
        game.font.draw(game.batch, "Your final score is: " + (hero.getSocial() + hero.getMoney() + hero.getLuck()
               + hero.getEndurance()) , 40, 640);


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