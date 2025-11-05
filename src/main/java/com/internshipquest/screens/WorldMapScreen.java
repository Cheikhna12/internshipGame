package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.Hero;
import com.internshipquest.model.Location;
import com.internshipquest.utils.Constants;
import com.internshipquest.graphics.CityMapRenderer;
import com.internshipquest.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class WorldMapScreen implements Screen {

    private final InternshipQuestGame game;
    private final List<Location> locations;
    private final CityMapRenderer cityMap;
    private Location hoveredLocation;

    private SpriteBatch heroBatch;
    private Hero hero;

    private Texture iconHome;
    private Texture iconFitnessClub;

    public WorldMapScreen(InternshipQuestGame game) {
        this.game = game;
        this.hero = new Hero();
        this.locations = new ArrayList<>();
        this.cityMap = new CityMapRenderer(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        // Charger les icônes
        iconHome = new Texture("assets/icon_home.png");
        iconFitnessClub = new Texture("assets/icon_fitness.png");

        // Créer les lieux
        locations.add(new Location("Maison", "assets/icon_home.png", 200, 550));
        locations.add(new Location("FitnessClub", "assets/icon_fitness.png", 700, 150));
    }

    @Override
    public void render(float delta) {
        // Nettoyer l’écran
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cityMap.render(game.batch, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        game.batch.begin();

        // Titre principal
        game.font.getData().setScale(2.5f);
        game.font.setColor(1f, 1f, 1f, 1f);
        game.font.draw(game.batch, "SIM LIFE", 30, 690);

        // Dessiner les icônes et noms des lieux
        for (Location loc : locations) {
            Texture icon = loc.getName().equals("Maison") ? iconHome : iconFitnessClub;
            game.batch.draw(icon, loc.getX(), loc.getY(), loc.getWidth(), loc.getHeight());

            // Nom sous l’icône
            game.font.getData().setScale(1.2f);
            game.font.setColor(1f, 1f, 1f, 1f);
            game.font.draw(game.batch, loc.getName(), loc.getX(), loc.getY() - 5);
        }

        // Survol d’un lieu
        if (hoveredLocation != null) {
            game.font.getData().setScale(2f);
            game.font.setColor(1f, 0.8f, 0f, 1f);
            game.font.draw(game.batch, hoveredLocation.getName() + " - Cliquez pour entrer", 20, 40);
        }

        heroBatch.begin();
        hero.render(heroBatch);
        heroBatch.end();

        game.batch.end();

        checkMouse();
    }

    private void checkMouse() {
        Vector2 mouse = new Vector2(Gdx.input.getX(), Constants.WINDOW_HEIGHT - Gdx.input.getY());
        hoveredLocation = null;

        for (Location loc : locations) {
            if (loc.contains(mouse.x, mouse.y)) {
                hoveredLocation = loc;
                if (Gdx.input.justTouched()) {
                    game.setScreen(new com.internshipquest.screens.LocationScreen(game, loc, this, hero));
                }
                break;
            }
        }
    }

    @Override
    public void show() {
         heroBatch = new SpriteBatch();
         hero = new Hero();
    }
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}

    @Override
    public void dispose() {
        cityMap.dispose();
        iconHome.dispose();
        heroBatch.dispose();
        iconFitnessClub.dispose();
    }
}
