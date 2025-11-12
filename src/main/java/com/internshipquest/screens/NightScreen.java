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
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.InternshipQuestGame;

public class NightScreen implements Screen {

    private final InternshipQuestGame game;
    private final AHero hero;
    private final Stage stage;
    private final Texture background;
    private final Label messageLabel;
    private float timeElapsed = 0f;

    public NightScreen(InternshipQuestGame game, AHero hero) {
        this.game = game;
        this.hero = hero;

        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("assets/night.png"));

        // --- Configuration du texte ---
        BitmapFont font = new BitmapFont();
        font.getData().setScale(1.5f);

        LabelStyle style = new LabelStyle(font, Color.WHITE);
        messageLabel = new Label(
                "You exhausted yourself by going to bed too late,\n" +
                        "you barely reach your bed and you wake up with a drowsy head.",
                style
        );
        messageLabel.setAlignment(Align.center);
        messageLabel.setWrap(true);

        // Démarre invisible
        messageLabel.getColor().a = 0f;

        // --- Placement du texte ---
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(messageLabel).width(Gdx.graphics.getWidth() * 0.8f);
        stage.addActor(table);

        // --- Animation de fondu (fade-in) ---
        messageLabel.addAction(Actions.sequence(
                Actions.delay(0.5f),         // petit délai avant l’apparition
                Actions.fadeIn(1.5f)         // apparition progressive sur 1,5 seconde
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

        // Après 3 secondes, on passe à la world map
        timeElapsed += delta;
        if (timeElapsed > 8f) {
//            hero.setPosition(150, 230);
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
