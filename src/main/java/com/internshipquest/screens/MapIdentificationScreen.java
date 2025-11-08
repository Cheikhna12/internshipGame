package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.graphics.CityMapRenderer;
import com.internshipquest.utils.Constants;

public class MapIdentificationScreen implements Screen {
    
    private final InternshipQuestGame game;
    private final CityMapRenderer cityMap;
    
    private static class Zone {
        String id;
        float x, y, width, height;
        
        Zone(String id, float x, float y, float width, float height) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }
    }
    
    private final Zone[] zones = {
        new Zone("A1", 64, 704, 448, 256),
        new Zone("A2", 512, 896, 64, 64),
        new Zone("A3", 768, 800, 256, 128),
        new Zone("A4", 1024, 720, 256, 128),
        new Zone("A5", 576, 832, 128, 128),
        new Zone("A6", 600, 704, 128, 128),
        
        new Zone("B1", 96, 352, 224, 224),
        new Zone("B2", 640, 320, 256, 256),
        new Zone("B3", 928, 320, 256, 256),
        new Zone("B4", 320, 480, 160, 160),
        new Zone("B5", 480, 480, 160, 160),
        new Zone("B6", 800, 480, 160, 160),
        
        new Zone("C1", 64, 64, 512, 64),
        new Zone("C2", 64, 160, 512, 64),
        new Zone("C3", 256, 128, 160, 128),
        new Zone("C4", 64, 224, 256, 64),
        new Zone("C5", 320, 224, 256, 64),
        new Zone("C6", 576, 160, 256, 128),
        
        new Zone("D1", 32, 672, 64, 64),
        new Zone("D2", 640, 672, 64, 64),
        new Zone("D3", 32, 352, 64, 64),
        new Zone("D4", 640, 352, 64, 64),
        new Zone("D5", 32, 64, 64, 64),
        new Zone("D6", 640, 64, 64, 64),
        new Zone("D7", 320, 672, 64, 64),
        new Zone("D8", 960, 672, 64, 64),
        new Zone("D9", 320, 352, 64, 64),
    };
    
    public MapIdentificationScreen(InternshipQuestGame game) {
        this.game = game;
        this.cityMap = new CityMapRenderer(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
    }
    
    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        cityMap.render(game.batch, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        
        game.shapeRenderer.setProjectionMatrix(game.batch.getProjectionMatrix());
        game.shapeRenderer.begin(com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType.Line);
        game.shapeRenderer.setColor(1f, 0f, 0f, 1f);
        for (Zone zone : zones) {
            game.shapeRenderer.rect(zone.x, zone.y, zone.width, zone.height);
        }
        game.shapeRenderer.end();
        
        game.batch.begin();
        
        game.font.getData().setScale(2.5f);
        game.font.setColor(1f, 1f, 0f, 1f);
        game.font.draw(game.batch, "IDENTIFICATION DES BATIMENTS", 20, 940);
        
        game.font.getData().setScale(1.5f);
        game.font.setColor(1f, 1f, 1f, 1f);
        game.font.draw(game.batch, "Notez les numeros sur les batiments que vous voyez", 20, 900);
        game.font.draw(game.batch, "Appuyez sur ECHAP pour revenir au jeu", 20, 870);
        
        for (Zone zone : zones) {
            float centerX = zone.x + zone.width / 2;
            float centerY = zone.y + zone.height / 2;
            
            game.font.getData().setScale(4.0f);
            game.font.setColor(0f, 0f, 0f, 1f);
            game.font.draw(game.batch, zone.id, centerX - 30 + 3, centerY + 3);
            
            game.font.setColor(1f, 1f, 0f, 1f);
            game.font.draw(game.batch, zone.id, centerX - 30, centerY);
        }
        
        game.batch.end();
        
        if (Gdx.input.isKeyPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            game.setScreen(new WorldMapScreen(game));
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
    public void dispose() {
        cityMap.dispose();
    }
}
