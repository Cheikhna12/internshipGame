package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.utils.SoundManager;

public class SettingsScreen implements Screen {

    private final InternshipQuestGame game;
    private final Screen previousScreen;

    private Stage stage;
    private Skin skin;
    private Slider volumeSlider;
    private Label volumeLabel;

    public SettingsScreen(InternshipQuestGame game, Screen previousScreen) {
        this.game = game;
        this.previousScreen = previousScreen;
    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        
        skin = new Skin(Gdx.files.internal("uiskin.json"));

        Table table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        Label title = new Label("Settings", skin);
        volumeLabel = new Label("Master Volume: " + (int)(SoundManager.getMasterVolume() * 100) + "%", skin);

        volumeSlider = new Slider(0f, 1f, 0.01f, false, skin);
        volumeSlider.setValue(SoundManager.getMasterVolume());

        
        volumeSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                float newVolume = volumeSlider.getValue();
                SoundManager.setMasterVolume(newVolume);
                volumeLabel.setText("Master Volume: " + (int)(newVolume * 100) + "%");
            }
        });

        TextButton backButton = new TextButton("Back", skin);
        backButton.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, com.badlogic.gdx.scenes.scene2d.Actor actor) {
                game.setScreen(previousScreen);
            }
        });

        table.add(title).padBottom(50).row();
        table.add(volumeLabel).padBottom(10).row();
        table.add(volumeSlider).width(400).padBottom(50).row();
        table.add(backButton).width(200).height(60);

        Gdx.input.setCatchKey(com.badlogic.gdx.Input.Keys.ESCAPE, true);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (Gdx.input.isKeyJustPressed(com.badlogic.gdx.Input.Keys.ESCAPE)) {
            game.setScreen(previousScreen);
        }

        stage.act(delta);
        stage.draw();
    }

    @Override public void resize(int width, int height) { stage.getViewport().update(width, height, true); }
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() { Gdx.input.setInputProcessor(null); }
    @Override public void dispose() { stage.dispose(); skin.dispose(); }
}
