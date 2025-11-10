package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.internshipquest.InternshipQuestGame;

public class SettingsScreen implements Screen {

    private final InternshipQuestGame game;
    private final Screen previousScreen;

    public SettingsScreen(InternshipQuestGame game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.font.getData().setScale(2f);
        game.font.draw(game.batch, "Settings Screen (Press ESC to go back)", 100, 500);
        game.batch.end();

        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            game.setScreen(previousScreen);
        }
    }

    @Override public void show() {}
    @Override public void resize(int width, int height) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
