package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.hero.*;
import com.internshipquest.model.Day;

public class Menu implements Screen {

    private final InternshipQuestGame game;
    private Texture gameLogo;
    private Texture nerdIcon;
    private Texture chadIcon;
    private Texture heroIcon;
    private Texture background;

    private AHero selectedHero;

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

        
        game.batch.draw(nerdIcon, 207, 425, 150, 200);
        game.batch.draw(chadIcon, 564, 425, 150, 200);
        game.batch.draw(heroIcon, 921, 425, 150, 200);

        game.batch.draw(gameLogo, 490, 650, 300, 300);
        
        if (selectedHero != null) {
            game.font.setColor(Color.WHITE);

            game.font.draw(game.batch, "Selected: " + selectedHero.getName(), 500, 375);
            
            String description = selectedHero.getHeroDescription();
            GlyphLayout layout = new GlyphLayout(game.font, description);
            float x = (Gdx.graphics.getWidth() - layout.width) / 2;
            game.font.draw(game.batch, layout, x, 325);
            game.font.getData().setScale(1.2f);
            game.font.setColor(Color.BLUE);
            game.font.draw(game.batch, "Click to Start Adventure", 400, 200);
            game.font.setColor(Color.WHITE);
        }

        game.batch.end();

        
        if (Gdx.input.justTouched()) {
            int x = Gdx.input.getX();
            int y = Gdx.graphics.getHeight() - Gdx.input.getY();

            if (x > 207 && x < 357 && y > 425 && y < 625) {
                selectedHero = new Nerd();
            }

            if (x > 564 && x < 714 && y > 425 && y < 625) {
                selectedHero = new Chad();
            }

            if (x > 921 && x < 1071 && y > 425 && y < 625) {
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
