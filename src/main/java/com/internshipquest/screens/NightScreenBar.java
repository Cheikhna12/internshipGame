package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.location.*;

public class NightScreenBar implements Screen {

    private final InternshipQuestGame game;
    private final AHero hero;
    private final Stage stage;
    private final Texture background;
    private final Label messageLabel;
    private float timeElapsed = 0f;

    public NightScreenBar(InternshipQuestGame game, AHero hero) {
        this.game = game;
        this.hero = hero;

        stage = new Stage(new ScreenViewport());
        background = new Texture(Gdx.files.internal("assets/night.png"));

        
        BitmapFont font = new BitmapFont();
        font.getData().setScale(1.5f);

        LabelStyle style = new LabelStyle(font, Color.WHITE);
        messageLabel = new Label(
                " You look around for someone to talk to \n a pretty stranger seems to be sitting alone at a table,\n so you approach and try out your best opening line.\n She smiles and you spend 3 hour chatting with her. You invite her to spend the night at your place… The next morning, you wake up full of energy and self-confidence. \"",
                style
        );
        messageLabel.setAlignment(Align.center);
        messageLabel.setWrap(true);

        
        messageLabel.getColor().a = 0f;

        
        Table table = new Table();
        table.setFillParent(true);
        table.center();
        table.add(messageLabel).width(Gdx.graphics.getWidth() * 0.8f);
        stage.addActor(table);

        
        messageLabel.addAction(Actions.sequence(
                Actions.delay(0.5f),         
                Actions.fadeIn(1.5f)         
        ));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        SpriteBatch batch = (SpriteBatch) stage.getBatch();
        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();

        stage.act(delta);
        stage.draw();

        timeElapsed += delta;
        if (timeElapsed > 10f) {
            WorldMapScreen map = new WorldMapScreen(game);
            hero.setInitialLocation(map.getLocationByName("Your House"));
            game.setScreen(map);
        }
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override public void show() {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {
        stage.dispose();
        background.dispose();
    }
}
