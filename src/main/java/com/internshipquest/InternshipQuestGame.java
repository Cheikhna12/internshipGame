package com.internshipquest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.internshipquest.screens.Menu;
import com.internshipquest.model.hero.*;
import com.internshipquest.model.Day;
import com.internshipquest.utils.SoundManager;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.Color;

public class InternshipQuestGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    private AHero hero;
    protected Day day;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = loadDMSerifFont(32);
        hero = null;
        day = null ;
        SoundManager.loadSounds();
        setScreen(new Menu(this));

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

    public AHero getHero() {
        return hero;
    }

    public Day getDay() {
        return day;
    }

    public void setHero(AHero hero) {
        this.hero = hero;
        this.day = new Day(this, hero);
    }

    public BitmapFont loadDMSerifFont(int size) {
        
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
                Gdx.files.internal("assets/fonts/DMSerifText-Regular.ttf")
        );
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.color = Color.WHITE;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        parameter.minFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;
        parameter.magFilter = com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;

        BitmapFont font = generator.generateFont(parameter);
        generator.dispose(); 
        return font;
    }

}
