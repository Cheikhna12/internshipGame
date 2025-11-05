package com.internshipquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.internshipquest.screens.WorldMapScreen;

public class InternshipQuestGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font; // <- ajouté

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        setScreen(new WorldMapScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        if (batch != null) batch.dispose();
        if (font != null) font.dispose();
        if (getScreen() != null) getScreen().dispose();
    }
}
