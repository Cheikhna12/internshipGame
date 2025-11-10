package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.Day;
import com.internshipquest.model.location.*;
import com.internshipquest.model.hero.*;
import com.internshipquest.utils.Constants;
import com.internshipquest.graphics.CityMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class WorldMapScreen implements Screen {

    private final InternshipQuestGame game;
    private final List<Location> locations;
    private final CityMapRenderer cityMap;
    private Location hoveredLocation;

    private SpriteBatch heroBatch;
    private AHero hero;
    private Day day;

    private String temporaryMessage = null;
    private float messageTimer = 0f;

    public WorldMapScreen(InternshipQuestGame game) {
        this.day = game.getDay();
        this.hero = game.getHero();
        this.game = game;
        this.locations = new ArrayList<>();
        this.cityMap = new CityMapRenderer(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        // x , y largeur hauteur
        locations.add(new Location("Industrial Zone", 64, 704, 448, 256));
        locations.add(new Location("Clover Field", 554, 720, 160, 160));
        locations.add(new Location("Bar", 768, 720, 224, 128));
        locations.add(new Location("Store", 96, 352, 224, 224));
        locations.add(new Location("FitnessClub", 640, 352, 256, 256));
        locations.add(new Location("Sorcerer", 928, 352, 256, 256));
        locations.add(new Location("Your House", 96, 128, 160, 160));
        locations.add(new Location("Epitech", 992, 720, 256, 128));
    }

    @Override
    public void render(float delta) {
        hero.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cityMap.render(game.batch, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        game.batch.begin();

        // Affichage de la date et l'heure
        game.font.getData().setScale(1f);
        game.font.setColor(1f, 1f, 1f, 1f);
        game.font.draw(game.batch, "Day: " + day.getDay() + " - Hour: " + day.getHour(), 40, 940);

        // Affichage du nom du lieu uniquement au survol (en bas de l'écran)
        if (hoveredLocation != null) {
            game.font.getData().setScale(1.0f);
            game.font.setColor(1f, 0.8f, 0f, 1f);

            String message;
            if (hero.getCurrentLocation() == hoveredLocation) {
                message = hoveredLocation.getName() + " - Click to enter";
            } else if (hero.isMoving()) {
                message = "While traveling...";
            } else {
                message = hoveredLocation.getName() + " - Click to move";
            }


            game.font.draw(game.batch, message, 20, 40);
        }

        game.batch.end();

        if (temporaryMessage != null) {
            messageTimer += delta;
            game.batch.begin();
            game.font.getData().setScale(1f);
            game.font.setColor(1f, 0.3f, 0.3f, 1f);
            game.font.draw(game.batch, temporaryMessage, 800, 80);
            game.batch.end();

            if (messageTimer > 3.5f) { // 3,5 secondes affichées
                temporaryMessage = null;
                messageTimer = 0f;
            }
        }

        heroBatch.begin();
        hero.render(heroBatch);
        heroBatch.end();

        checkMouse();
    }

    private void checkMouse() {
        Vector2 mouse = new Vector2(Gdx.input.getX(), Constants.WINDOW_HEIGHT - Gdx.input.getY());
        hoveredLocation = null;

        for (Location loc : locations) {
            if (loc.contains(mouse.x, mouse.y)) {
                hoveredLocation = loc;

                if (Gdx.input.justTouched()) {
                    System.out.println("[CLICK] " + loc.getName());

                    // Récupère le lieu logique associé
                    ALieuVisitable lieu = loc.getLieu();

                    if (hero.getCurrentLocation() == loc) {
                        System.out.println("[WORLDMAP] Entrée dans " + loc.getName());
                        if (lieu != null && !lieu.isOpen(day)) {
                            temporaryMessage = "The " + loc.getName() + " is currently closed.";
                            messageTimer = 0f;
                        } else {
                            game.setScreen(new LocationScreen(game, loc, this));
                        }
                    } else if (!hero.isMoving()) {
                        System.out.println("[WORLDMAP] Déplacement vers " + loc.getName());
                        hero.moveTo(loc);
                    }
                }
                break;
            }
        }
    }




    @Override
    public void show() {
        heroBatch = new SpriteBatch();

        
        if (!locations.isEmpty() && hero.getCurrentLocation() == null) {
            hero.setInitialLocation(locations.get(6));
            System.out.println("[WORLDMAP] Héros placé à " + locations.get(6).getName());
        }
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        cityMap.dispose();
        heroBatch.dispose();
    }
}
