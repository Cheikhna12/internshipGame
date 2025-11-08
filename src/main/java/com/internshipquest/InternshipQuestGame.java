package com.internshipquest;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.internshipquest.screens.Menu;
import com.internshipquest.model.*;


public class InternshipQuestGame extends Game {
    public SpriteBatch batch;
    public BitmapFont font;
    private AHero hero;
    protected Day day;

    @Override
    public void create() {
        batch = new SpriteBatch();
        font = new BitmapFont();
        hero = null;
        day = null ;
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

}
