package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.*;
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
    private Hero hero;
    private Day day;

    private Texture iconHome;
    private Texture iconFitnessClub;

    public WorldMapScreen(InternshipQuestGame game) {
        this.day = game.getDay();
        this.hero = game.getHero();
        this.game = game;
        this.locations = new ArrayList<>();
        this.cityMap = new CityMapRenderer(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        iconHome = new Texture("assets/icon_home.png");
        iconFitnessClub = new Texture("assets/icon_fitness.png");

        locations.add(new Location("Maison", "assets/icon_home.png", 150, 230));
        locations.add(new Location("FitnessClub", "assets/icon_fitness.png", 780, 450));
    }

    @Override
    public void render(float delta) {
        hero.update(delta);

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cityMap.render(game.batch, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        game.batch.begin();
        
        game.font.getData().setScale(1.8f);
        game.font.setColor(1f, 1f, 1f, 1f);
        game.font.draw(game.batch, "Day: "+day.getDay()+" - Hour: "+day.getHour(), 40, 940);

        for (Location loc : locations) {
            float x = loc.getX();
            float y = loc.getY();
            float size = (loc == hoveredLocation) ? 60 : 50;
            float offset = (loc == hoveredLocation) ? -5 : 0;

            Texture icon = loc.getName().equals("Maison") ? iconHome : iconFitnessClub;

            if (icon != null) {
                if (hero.getCurrentLocation() == loc && !hero.isMoving()) {
                    float pulse = (float) (Math.sin(System.currentTimeMillis() / 200.0) * 0.5 + 0.5);
                    float pulseSize = size + pulse * 5;
                    float pulseOffset = offset - pulse * 2.5f;
                    game.batch.setColor(1f, 1f, 1f, 0.3f + pulse * 0.3f);
                    game.batch.draw(icon, x + pulseOffset, y + pulseOffset, pulseSize, pulseSize);
                    game.batch.setColor(1f, 1f, 1f, 1f);
                }
                game.batch.draw(icon, x + offset, y + offset, size, size);
            }

            game.font.getData().setScale(1.2f);
            game.font.setColor(0f, 0f, 0f, 0.8f);
            game.font.draw(game.batch, loc.getName(), x - 10 + 1, y - 10 - 1);
            game.font.setColor(1f, 1f, 1f, 1f);
            game.font.draw(game.batch, loc.getName(), x - 10, y - 10);
        }

        if (hoveredLocation != null) {
            game.font.getData().setScale(1.8f);
            game.font.setColor(1f, 0.8f, 0f, 1f);
            
            String message;
            if (hero.getCurrentLocation() == hoveredLocation) {
                message = hoveredLocation.getName() + " - Cliquez pour entrer";
            } else if (hero.isMoving()) {
                message = "En déplacement...";
            } else {
                message = hoveredLocation.getName() + " - Cliquez pour vous déplacer";
            }
            
            game.font.draw(game.batch, message, 20, 40);
        } else if (hero.isMoving()) {
            game.font.getData().setScale(1.5f);
            game.font.setColor(0.7f, 0.7f, 1f, 1f);
            game.font.draw(game.batch, "En déplacement...", 20, 40);
        }

        game.batch.end();

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

                    if (hero.getCurrentLocation() == loc) {
                        System.out.println("[WORLDMAP] Entrée dans " + loc.getName());
                        game.setScreen(new LocationScreen(game, loc, this));
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

        if (hero == null) {
            hero = new Hero();
        }
        
        if (!locations.isEmpty() && hero.getCurrentLocation() == null) {
            hero.setInitialLocation(locations.get(0));
            System.out.println("[WORLDMAP] Héros placé à " + locations.get(0).getName());
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
        iconHome.dispose();
        iconFitnessClub.dispose();
        heroBatch.dispose();
    }
}
