package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.Day;
import com.internshipquest.model.location.*;
import com.internshipquest.model.hero.*;
import com.internshipquest.utils.Constants;
import com.internshipquest.graphics.CityMapRenderer;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;
import com.internshipquest.utils.SoundManager;

import java.util.ArrayList;
import java.util.List;

public class WorldMapScreen implements Screen {

    private InternshipQuestGame game;
    private List<Location> locations;
    private LocationFactory locationFactory;
    private CityMapRenderer cityMap;
    private Location hoveredLocation;
    private Location lastLocationVisited;


    private SpriteBatch heroBatch;
    private AHero hero;
    private Day day;

    
    private Stage stage;
    private Skin skin;
    private TextButton settingsButton;

    private String temporaryMessage = null;
    private float messageTimer = 0f;

    private Texture whitePixel;

    public WorldMapScreen(InternshipQuestGame game) {
        this.game = game;
        this.day = game.getDay();
        this.hero = game.getHero();
        this.locations = new ArrayList<>();
        this.cityMap = new CityMapRenderer(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        this.locationFactory = new LocationFactory(game);
        this.locations = locationFactory.createAllLocations();
    }

    @Override
    public void render(float delta) {
        hero.update(delta);

        //gestion du niveau de darkness
        float hour = day.getHour();
        float darkness = 0f;
        if (hour >= 7 && hour < 18) {
            darkness = 0f;
        } else if (hour >= 18 && hour < 22) {
            darkness = 0.3f;
        } else {
            darkness = 0.6f;
        }

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cityMap.render(game.batch, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);

        game.batch.begin();

        game.batch.setColor(0, 0, 0, darkness);
        game.batch.draw(whitePixel, 0, 0, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        game.batch.setColor(1, 1, 1, 1);
        game.batch.end();
        game.batch.begin();


        // Affichage de la date et l'heure
        game.font.getData().setScale(1f);
        game.font.setColor(1f, 1f, 1f, 1f);
        game.font.draw(game.batch, "Day: " + day.getDay() + " - Hour: " + day.getHour(), 40, 940);

        
        if (hoveredLocation != null) {
            game.font.getData().setScale(1.0f);
            game.font.setColor(1f, 0.8f, 0f, 1f);

            String message;
            if (hero.getCurrentLocation() == hoveredLocation) {
                message = hoveredLocation.getName() + " - Click to enter";
            } else if (hero.isMoving()) {
                message = "While traveling...";
            } else {
                message = hoveredLocation.getName() + " - Click to move";
            }


            game.font.draw(game.batch, message, 20, 40);
        }

        game.batch.end();

        if (temporaryMessage != null) {
            messageTimer += delta;
            game.batch.begin();
            GlyphLayout layout = new GlyphLayout(game.font, temporaryMessage);
            float x = (Gdx.graphics.getWidth() - layout.width-32f);
            game.font.setColor(1f, 0.3f, 0.3f, 1f);
            game.font.draw(game.batch, temporaryMessage, x, 80);
            game.font.setColor(1f, 0.8f, 0f, 1f);
            game.batch.end();

            if (messageTimer > 3.5f) { 
                temporaryMessage = null;
                messageTimer = 0f;
            }
        }

        heroBatch.begin();
        hero.render(heroBatch);
        heroBatch.end();

        checkMouse();
        stage.act(delta);
        stage.draw();
    }

    private void checkMouse() {
        Vector2 mouse = new Vector2(Gdx.input.getX(), Constants.WINDOW_HEIGHT - Gdx.input.getY());
        hoveredLocation = null;

        for (Location loc : locations) {
            if (loc.contains(mouse.x, mouse.y)) {
                hoveredLocation = loc;

                if (Gdx.input.justTouched()) {
                    System.out.println("[CLICK] " + loc.getName());

                    
                    ALieuVisitable lieu = loc.getLieu();

                    if (hero.getCurrentLocation() == loc) {
                        System.out.println("[WORLDMAP] Entrée dans " + loc.getName());
                        if (lieu != null && !lieu.isOpen(day)) {
                            temporaryMessage = "The " + loc.getName() + " is currently closed.";
                            messageTimer = 0f;
                        } else {
                            lastLocationVisited = loc;
                            game.setScreen(new LocationScreen(game, loc, this));
                        }
                    } else if (!hero.isMoving()) {
                        System.out.println("[WORLDMAP] Déplacement vers " + loc.getName());
                        hero.moveTo(loc);
                    }
                }
                break;
            }
        }
    }


    public void show() {
        heroBatch = new SpriteBatch();

        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        whitePixel = new Texture(pixmap);
        pixmap.dispose();

        if (day.getHour()>6 && day.getHour()<18) {
            SoundManager.playMusic("village", true, 0.6f);
        } else { SoundManager.playMusic("village-evening", true, 0.6f);}

        Location initialLoc = lastLocationVisited != null ? lastLocationVisited : getLocationByName("Your House");
        if (initialLoc != null) {
            hero.setInitialLocation(initialLoc);
            System.out.println("[WORLDMAP] Héros placé à " + initialLoc.getName());
        }

        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        Texture buttonTexture = new Texture(Gdx.files.internal("assets/buttonTexture.png"));
        TextureRegionDrawable drawable = new TextureRegionDrawable(new TextureRegion(buttonTexture));
        ImageButton imageButton = new ImageButton(drawable);

        LabelStyle labelStyle = new LabelStyle(game.font, Color.WHITE);
        Label label = new Label("Settings", labelStyle);
        label.setFontScale(1.0f);
        label.setSize(100, 100);
        label.setAlignment(Align.center);

        Stack buttonStack = new Stack();
        buttonStack.setSize(150, 80);
        buttonStack.add(imageButton);
        buttonStack.add(label);

        buttonStack.setPosition(Constants.WINDOW_WIDTH - 200, Constants.WINDOW_HEIGHT - 100);

        buttonStack.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("[WORLDMAP] Settings button clicked!");
                game.setScreen(new SettingsScreen(game, WorldMapScreen.this));
            }
        });

        stage.addActor(buttonStack);
    }


    public Location getLocationByName(String name) {
        for (Location loc : locations) {
            if (loc.getName().equals(name)) return loc;
        }
        return null;
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {SoundManager.stopMusic();}

    @Override
    public void dispose() {
        stage.dispose();
        if (skin != null) skin.dispose();
        cityMap.dispose();
        heroBatch.dispose();
    }
}
