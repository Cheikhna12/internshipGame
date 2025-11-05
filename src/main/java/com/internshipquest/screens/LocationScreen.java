package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.*;

import java.util.ArrayList;
import java.util.List;

public class LocationScreen implements Screen {

    private final InternshipQuestGame game;
    private final Location location;
    private final WorldMapScreen mapScreen;
    private final Hero hero;
    private final List<ActionZone> actions = new ArrayList<>();

    private FitnessClub gym;
    private Texture gymBackground;

    public LocationScreen(InternshipQuestGame game, Location location, WorldMapScreen mapScreen, Hero hero) {
        this.game = game;
        this.location = location;
        this.mapScreen = mapScreen;
        this.hero = hero;

        if (location.getName().equals("FitnessClub")) {
            gym = new FitnessClub();
        }
    }

    @Override
    public void show() {
        if (location.getName().equals("FitnessClub")) {
            gymBackground = new Texture(Gdx.files.internal("assets/images/gym_background.png"));
        }
    }

    @Override
    public void render(float delta) {
        float[] color = location.getColor();
        Gdx.gl.glClearColor(color[0] * 0.3f, color[1] * 0.3f, color[2] * 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        if (gymBackground != null) {
            game.batch.draw(gymBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        // Texte en bleu
        game.font.getData().setScale(2f);
        game.font.draw(game.batch, "🏠 Lieu : " + location.getName(), 50, 650);

        if (gym != null) gym.update(delta);

        if (gym != null && gym.isShowingMessage()) {
            game.font.getData().setScale(1.5f);
            game.font.draw(game.batch, gym.getCurrentMessage(), 50, 400);
        } else {
            actions.clear();
            int yPos = 400;
            if (location.getName().equals("FitnessClub")) {
                addAction("1. Faire des pompes", 70, yPos, () -> gym.pushUps(hero));
            }

            game.font.getData().setScale(1.8f);
            for (ActionZone a : actions) {
                game.font.draw(game.batch, a.text, a.x, a.y);
            }
        }

        game.font.getData().setScale(1.5f);
        game.font.draw(game.batch, "[ECHAP] Retour à la carte", 50, 80);

        game.batch.end();

        handleInput();
    }

    private void addAction(String text, float x, float y, Runnable action) {
        Rectangle rect = new Rectangle(x, y - 30, 600, 40);
        actions.add(new ActionZone(text, x, y, rect, action));
    }

    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(mapScreen);
        }

        if (!actions.isEmpty() && Gdx.input.justTouched()) {
            float clickY = Gdx.graphics.getHeight() - Gdx.input.getY();
            float clickX = Gdx.input.getX();

            for (ActionZone a : actions) {
                if (a.bounds.contains(clickX, clickY)) {
                    a.action.run();
                }
            }
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override
    public void dispose() {
        if (gymBackground != null) gymBackground.dispose();
    }

    private static class ActionZone {
        String text;
        float x, y;
        Rectangle bounds;
        Runnable action;

        ActionZone(String text, float x, float y, Rectangle bounds, Runnable action) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.bounds = bounds;
            this.action = action;
        }
    }
}
