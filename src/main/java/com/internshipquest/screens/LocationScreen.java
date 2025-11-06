package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
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
    private BitmapFont font;

    // zone "Return to world Map"
    private final float returnX = 50;
    private final float returnY = 80;
    private final float returnWidth = 250;
    private final float returnHeight = 30;

    public LocationScreen(InternshipQuestGame game, Location location, WorldMapScreen mapScreen, Hero hero) {
        this.game = game;
        this.location = location;
        this.mapScreen = mapScreen;
        this.hero = game.getHero();

        if (location.getName().equals("FitnessClub")) {
            gym = new FitnessClub();
        }

        font = new BitmapFont();
        font.getRegion().getTexture().setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
        font.setColor(1f, 1f, 1f, 1f);
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

        // Texte principal
        font.getData().setScale(2f);
        font.draw(game.batch, location.getName(), 50, 900);
        font.getData().setScale(1.5f);
        font.draw(game.batch,"Your current energy is "+hero.getEnergy()+".", 50, 850);

        if (gym != null) gym.update(delta);

        if (gym != null && gym.isShowingMessage()) {
            font.getData().setScale(1.5f);
            font.draw(game.batch, gym.getCurrentMessage(), 50, 400);
        } else {
            actions.clear();
            int yPos = 650;
            if (location.getName().equals("FitnessClub")) {
                addAction("1. Do Push-up", 170, yPos, () -> gym.pushUps(hero));
                addAction("2. Do Deadlift", 170, yPos - 50, () -> gym.deadlift(hero));
            }

            font.getData().setScale(1.8f);
            for (ActionZone a : actions) {
                font.draw(game.batch, a.text, a.x, a.y);
            }
        }

        game.batch.end();

        game.batch.begin();
        font.getData().setScale(1.5f);
        font.draw(game.batch, "Return to world Map", returnX, returnY);
        game.batch.end();

        handleInput();
    }

    private void addAction(String text, float x, float y, Runnable action) {
        actions.add(new ActionZone(text, x, y, new com.badlogic.gdx.math.Rectangle(x, y - 30, 600, 40), action));
    }

    private void handleInput() {
        if (Gdx.input.justTouched()) {
            float clickX = Gdx.input.getX();
            float clickY = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (gym != null && gym.isShowingMessage()) {
                return;
            }

            // Zone cliquable du texte "Return to world Map"
            if (clickX >= returnX && clickX <= returnX + returnWidth &&
                    clickY >= returnY - returnHeight && clickY <= returnY) {
                game.setScreen(mapScreen);
                return;
            }

            // Vérification des actions normales
            for (ActionZone a : actions) {
                if (a.bounds.contains(clickX, clickY)) {
                    a.action.run();
                }
            }
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(mapScreen);
        }
    }

    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        if (gymBackground != null) gymBackground.dispose();
        if (font != null) font.dispose();
    }

    private static class ActionZone {
        String text;
        float x, y;
        com.badlogic.gdx.math.Rectangle bounds;
        Runnable action;

        ActionZone(String text, float x, float y, com.badlogic.gdx.math.Rectangle bounds, Runnable action) {
            this.text = text;
            this.x = x;
            this.y = y;
            this.bounds = bounds;
            this.action = action;
        }
    }
}
