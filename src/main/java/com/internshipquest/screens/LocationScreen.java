package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.Day;
import com.internshipquest.model.location.*;
import com.internshipquest.model.hero.*;
import com.internshipquest.model.activity.AActivity;


import java.util.ArrayList;
import java.util.List;

public class LocationScreen implements Screen {

    private InternshipQuestGame game;
    private Location location;
    private WorldMapScreen mapScreen;
    private AHero hero;

    private ALieuVisitable lieu;
    private Texture background;
    private List<AActivity> activities = new ArrayList<>();
    private List<ActionZone> actions = new ArrayList<>(); // actions = zone où les activités seront placé

    private Texture npcTexture;
    private String npcMessage;
    private boolean showNpcDialog = false;

    // zone "Return to world Map"
    private final float returnX = 50;
    private final float returnY = 80;
    private final float returnWidth =  400;
    private final float returnHeight = 30;

    public LocationScreen(InternshipQuestGame game, Location location, WorldMapScreen mapScreen) {
        this.game = game;
        this.location = location;
        this.mapScreen = mapScreen;
        this.hero = game.getHero();

        LocationFactory factory = new LocationFactory(game);
        this.lieu = factory.getVisitableLocation(location.getName());

        if (lieu != null && lieu.getNpcTexture() != null) {
            npcTexture = lieu.getNpcTexture();
            npcMessage = lieu.getNpcMessage();
            showNpcDialog = true;
        }
    }

    @Override
    public void show() {

        // on gère maintenant le background dans la factory :)
        background = LocationFactory.createBackground(location.getName());
        // lance la musique quand on rentre dans un lieu
        if (lieu != null) {
            lieu.onEnter();
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();

        if (background != null) {
            game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        }

        // Texte principal
        game.font.getData().setScale(1.1f);
        game.font.draw(game.batch, location.getName(), 50, 900);
        game.font.getData().setScale(1.0f);
        game.font.draw(game.batch, "Your current energy is " + hero.getEnergy() + " and you got "+hero.getMoney()+"euro.", 50, 850);

        Day day = game.getDay();
        if (day != null) {
            game.font.draw(game.batch, "It's " + day.getHour() + "h.", 50, 820);
        }

        if (lieu != null) lieu.update(delta);



        if (lieu != null && lieu.isShowingMessage()) {
            game.font.getData().setScale(1.0f);
            game.font.draw(game.batch, lieu.getCurrentMessage(), 50, 700);}
       else {
                actions.clear();
                    int yPos = 700;

            // On charge les activités qui sont défini dans les classe lieu
            activities = lieu.getActivities();


                // Boucle unique pour créer les boutons d’action
                for (int i = 0; i < activities.size(); i++) {
                    final int index = i;
                    AActivity activity = activities.get(i);
                    addAction((i + 1) + ". " + activity.getName(), 200, yPos - i * 50,() -> {
                                lieu.performActivity(index, hero, day);
                            }
                    );
                }

            game.font.getData().setScale(1.0f);
            for (ActionZone a : actions) {
                game.font.draw(game.batch, a.text, a.x, a.y);
            }

            if (showNpcDialog && npcTexture != null) {
                // Affiche le PNJ et son message
                game.batch.draw(npcTexture, 500, 0, 960, 720);
                game.font.getData().setScale(1.1f);
                game.font.setColor(0f, 0.7f, 1f, 1f);
                game.font.draw(game.batch, npcMessage, 550, 800);
                game.font.setColor(1f, 0.8f, 0f, 1f);
            }
        }

        game.batch.end();

        game.batch.begin();
        game.font.getData().setScale(1.05f);
        game.font.draw(game.batch, "Return to world Map", returnX, returnY);
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

            if (showNpcDialog) {
                showNpcDialog = false;
                return;
            }

            if (lieu != null && lieu.isShowingMessage()) {
                return;
            }

            // Zone cliquable du texte "Return to world Map avec arret direct pour éviter de pouvoir lancer une autre action pendantle chargement"
            if (clickX >= returnX && clickX <= returnX + returnWidth &&
                    clickY >= returnY - returnHeight && clickY <= returnY) {
                game.setScreen(mapScreen);
                return;
            }

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

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    public void hide() {
        // pour stopper la musique
        if (lieu != null) {
            lieu.onExit();
        }
    }

    @Override
    public void dispose() {
        if (background != null) background.dispose();
        if (npcTexture != null) npcTexture.dispose();
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
