package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import com.internshipquest.IntershipQuestGame;
import com.internshipquest.model.Location;
import com.internshipquest.utils.Constants;

import java.util.ArrayList;
import java.util.List;


public class WorldMapScreen implements Screen {
    
    private final IntershipQuestGame game;
    private final List<Location> locations;
    
    public WorldMapScreen(IntershipQuestGame game) {
        this.game = game;
        this.locations = new ArrayList<>();

        float spacing = 100;
        float startX = (Constants.WINDOW_WIDTH - (2 * Constants.LOCATION_WIDTH + spacing)) / 2;
        float startY = (Constants.WINDOW_HEIGHT - (2 * Constants.LOCATION_HEIGHT + spacing)) / 2;
        locations.add(new Location(
            "Maison",
            "[HOME]",
            startX,
            startY + Constants.LOCATION_HEIGHT + spacing,
            Constants.LOCATION_WIDTH,
            Constants.LOCATION_HEIGHT,
            Constants.Colors.MAISON
        ));
        
        locations.add(new Location(
            "École",
            "[SCHOOL]",
            startX + Constants.LOCATION_WIDTH + spacing,
            startY + Constants.LOCATION_HEIGHT + spacing,
            Constants.LOCATION_WIDTH,
            Constants.LOCATION_HEIGHT,
            Constants.Colors.ECOLE
        ));
        
        locations.add(new Location(
            "Entreprise",
            "[COMPANY]",
            startX,
            startY,
            Constants.LOCATION_WIDTH,
            Constants.LOCATION_HEIGHT,
            Constants.Colors.ENTREPRISE
        ));
        
        locations.add(new Location(
            "Carrefour Market",
            "[MARKET]",
            startX + Constants.LOCATION_WIDTH + spacing,
            startY,
            Constants.LOCATION_WIDTH,
            Constants.LOCATION_HEIGHT,
            Constants.Colors.CARREFOUR
        ));
        
        System.out.println("[MAP] Carte du monde créée avec " + locations.size() + " lieux");
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(
            Constants.Colors.BACKGROUND[0],
            Constants.Colors.BACKGROUND[1],
            Constants.Colors.BACKGROUND[2],
            Constants.Colors.BACKGROUND[3]
        );
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Filled);
        for (Location location : locations) {
            float[] color = location.getColor();
            game.shapeRenderer.setColor(color[0], color[1], color[2], color[3]);
            game.shapeRenderer.rect(location.getX(), location.getY(), location.getWidth(), location.getHeight());
        }
        game.shapeRenderer.end();
        
        game.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(Color.WHITE);
        for (Location location : locations) {
            game.shapeRenderer.rect(location.getX(), location.getY(), location.getWidth(), location.getHeight());
        }
        game.shapeRenderer.end();
        
        game.batch.begin();
        game.font.setColor(Constants.Colors.TEXT[0], Constants.Colors.TEXT[1], Constants.Colors.TEXT[2], Constants.Colors.TEXT[3]);
        for (Location location : locations) {
            String displayText = location.getIcon() + " " + location.getName();
            float textX = location.getCenterX() - 60;
            float textY = location.getCenterY() + 10;
            game.font.draw(game.batch, displayText, textX, textY);
        }
        game.batch.end();
        
        handleInput();
    }
    private void handleInput() {
        if (Gdx.input.justTouched()) {
            Vector2 clickPos = new Vector2(Gdx.input.getX(), Constants.WINDOW_HEIGHT - Gdx.input.getY());
            
            for (Location location : locations) {
                if (location.contains(clickPos.x, clickPos.y)) {
                    System.out.println("[CLICK] Clic sur : " + location.getIcon() + " " + location.getName());
                    break;
                }
            }
        }
    }
    
    @Override
    public void show() {
        System.out.println("[SCREEN] Affichage de la carte du monde");
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
    
    @Override
    public void hide() {
    }
    
    @Override
    public void dispose() {
    }
}
