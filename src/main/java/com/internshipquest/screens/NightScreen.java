package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import java.util.Random;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.event.*;
import com.internshipquest.model.Day;

public class NightScreen implements Screen {

    private final InternshipQuestGame game;
    private final AHero hero;
    private final Stage stage;
    private final Texture background;
    private final Label messageLabel;
    private float timeElapsed = 0f;

    public NightScreen(InternshipQuestGame game, AHero hero, Day day) {
        this.game = game;
        this.hero = hero;

        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("assets/night.png"));

        // --- Configuration du texte ---
        LabelStyle style = new LabelStyle(game.font, Color.WHITE);
        messageLabel = new Label("", style);
        messageLabel.setAlignment(Align.center);
        messageLabel.setWrap(true);

        // --- Placement du texte ---
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(messageLabel).width(Gdx.graphics.getWidth() * 0.8f);
        stage.addActor(table);

        Random random = new Random();
        boolean triggerEvent = random.nextBoolean(); // true = événement, false = pas d'événement

        if (triggerEvent) {
            EventFactory eventFactory = new EventFactory(hero,day);
            AEvent todayEvent = eventFactory.getRandomEvent();
            if (todayEvent != null) {
                todayEvent.applyEffect(hero,day);
                messageLabel.setText(todayEvent.getMessageNight());
            } else {
                messageLabel.setText("After a good night, you regenerate your energy proportional to your endurance.");
            }
        } else {
            messageLabel.setText("After a good night, you regenerate your energy proportional to your endurance.");
        }

        // Démarre invisible
        messageLabel.getColor().a = 0f;

        // --- Animation de fondu (fade-in) ---
        messageLabel.addAction(Actions.sequence(
                Actions.delay(0.5f),         
                Actions.fadeIn(1.5f)         
        ));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = (SpriteBatch) stage.getBatch();
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

        
        timeElapsed += delta;
        if (timeElapsed > 8f) {
            game.setScreen(new WorldMapScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
