package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.Hero;
import com.internshipquest.model.Chad;
import com.internshipquest.model.Nerd;
import com.internshipquest.model.Hero;
import com.internshipquest.model.AHero;
import com.internshipquest.model.Day;

public class Menu implements Screen {

    private final InternshipQuestGame game;
    private Texture gameLogo;
    private Texture nerdIcon;
    private Texture chadIcon;
    private Texture heroIcon;
    private Texture background;

    private AHero selectedHero;


//    // positions des icônes
//    private final int nerdX = 200;
//    private final int nerdY = 400;
//    private final int chadX = 600;
//    private final int chadY = 400;
//    private final int heroX = 1000;
//    private final int heroY = 400;

    public Menu(InternshipQuestGame game) {
        this.game = game;
        gameLogo = new Texture("assets/GameLogo.png");
        nerdIcon = new Texture("assets/Nerd.png");
        chadIcon = new Texture("assets/Chad.png");
        heroIcon = new Texture("assets/Hero.png");
        background = new Texture(Gdx.files.internal("assets/images/Menu_background.png"));

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        // Draw character icons
        game.batch.draw(nerdIcon, 170, 300, 200, 200);
        game.batch.draw(chadIcon, 540, 300, 200, 200);
        game.batch.draw(heroIcon, 910, 300, 200, 200);
        game.batch.draw(gameLogo, 430, 550, 400, 400);

        // Highlight selected hero
        if (selectedHero != null) {
            game.font.draw(game.batch, "Selected: " + selectedHero.getName(), 590, 250);
            game.font.draw(game.batch, "Click to Start Adventure", 590, 200);
        }

        game.batch.end();

        // Input detection
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (x > 170 && x < 370 && y > 300 && y < 500) {
                selectedHero = new Nerd();
            }

            if (x > 540 && x < 740 && y > 300 && y < 500) {
                selectedHero = new Chad();
            }

            if (x > 910 && x < 1100 && y > 300 && y < 500) {
                selectedHero = new Hero();
            }

            if (selectedHero != null && x > 350 && x < 1200 && y > 150 && y < 250) {
                game.setHero(selectedHero);
                game.setScreen(new WorldMapScreen(game));
            }
        }
    }

    @Override
    public void show() {
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
