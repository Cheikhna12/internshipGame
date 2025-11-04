package com.internshipquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.internshipquest.screens.WorldMapScreen;

public class IntershipQuestGame extends Game {
    
    public SpriteBatch batch;

    public ShapeRenderer shapeRenderer;

    public BitmapFont font;

    @Override
    public void create() {

        batch = new SpriteBatch();

        shapeRenderer = new ShapeRenderer();

        font = new BitmapFont();

        font.getData().setScale(2f);

        this.setScreen(new WorldMapScreen(this));

        System.out.println("[GAME] Internship Quest démarré !");
    }
    @Override
    public void dispose() {

        batch.dispose();
        shapeRenderer.dispose();
        font.dispose();
    }
}
