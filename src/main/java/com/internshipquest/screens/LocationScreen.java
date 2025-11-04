package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.internshipquest.IntershipQuestGame;
import com.internshipquest.model.Location;

public class LocationScreen implements Screen {
    
    private final IntershipQuestGame game;
    private final Location location;
    private final WorldMapScreen mapScreen;
    
    public LocationScreen(IntershipQuestGame game, Location location, WorldMapScreen mapScreen) {
        this.game = game;
        this.location = location;
        this.mapScreen = mapScreen;
    }
    @Override
    public void render(float delta) {
        float[] color = location.getColor();
        Gdx.gl.glClearColor(color[0] * 0.3f, color[1] * 0.3f, color[2] * 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        game.batch.begin();
        
        game.font.getData().setScale(3f);
        game.font.setColor(1f, 1f, 1f, 1f);
        game.font.draw(game.batch, location.getName(), 50, 650);
        
        game.font.getData().setScale(1.8f);
        game.font.setColor(0.9f, 0.9f, 0.9f, 1f);
        game.font.draw(game.batch, "Vous etes dans : " + location.getName(), 50, 580);
        
        game.font.getData().setScale(2f);
        game.font.setColor(1f, 1f, 0.5f, 1f);
        game.font.draw(game.batch, "Actions disponibles :", 50, 480);
        
        game.font.getData().setScale(1.6f);
        game.font.setColor(0.9f, 0.9f, 0.9f, 1f);
        
        String nom = location.getName();
        int yPos = 410;
        
        if (nom.equals("Maison")) {
            game.font.draw(game.batch, "1. Dormir - Restaure energie", 70, yPos);
            game.font.draw(game.batch, "2. Manger - Restaure nourriture", 70, yPos - 50);
            game.font.draw(game.batch, "3. Se reposer", 70, yPos - 100);
            
        } else if (nom.equals("Ecole")) {
            game.font.draw(game.batch, "1. Etudier - Ameliore competences", 70, yPos);
            game.font.draw(game.batch, "2. Lire - Augmente connaissances", 70, yPos - 50);
            game.font.draw(game.batch, "3. Faire devoirs", 70, yPos - 100);
            
        } else if (nom.equals("Entreprise")) {
            game.font.draw(game.batch, "1. Travailler - Gagne argent (Coute energie)", 70, yPos);
            game.font.draw(game.batch, "2. Demander augmentation", 70, yPos - 50);
            game.font.draw(game.batch, "3. Prendre pause", 70, yPos - 100);
            
        } else if (nom.equals("Carrefour")) {
            game.font.draw(game.batch, "1. Acheter nourriture (Coute argent)", 70, yPos);
            game.font.draw(game.batch, "2. Acheter vetements", 70, yPos - 50);
            game.font.draw(game.batch, "3. Regarder produits", 70, yPos - 100);
        }

        
        game.font.getData().setScale(2f);
        game.font.setColor(1f, 0.5f, 0.5f, 1f);
        game.font.draw(game.batch, "ECHAP = Retour a la map", 50, 80);
        
        game.batch.end();
        
        handleInput();
    }
    private void handleInput() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            game.setScreen(mapScreen);
        }
    }

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}
    @Override
    public void dispose(){}

}
