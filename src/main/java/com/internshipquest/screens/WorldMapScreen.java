package com.internshipquest.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.internshipquest.IntershipQuestGame;
import com.internshipquest.model.Location;
import com.internshipquest.utils.Constants;
import com.internshipquest.graphics.CityMapRenderer;

import java.util.ArrayList;
import java.util.List;

public class WorldMapScreen implements Screen {
    
    private final IntershipQuestGame game;
    private final List<Location> locations;
    private final CityMapRenderer cityMap;
    private Location hoveredLocation;
    
    private Texture iconHome;
    private Texture iconSchool;
    private Texture iconCompany;

    private Texture iconMarket;
    

    public WorldMapScreen(IntershipQuestGame game) {
        this.game = game;

        this.locations = new ArrayList<>();

        this.cityMap = new CityMapRenderer(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        

        iconHome = new Texture("assets/icon_home.png");
        iconSchool = new Texture("assets/icon_school.png");
        iconCompany = new Texture("assets/icon_company.png");
        iconMarket = new Texture("assets/icon_market.png");
        

        locations.add(new Location("Maison", "", 200, 550, 50, 50, Constants.Colors.MAISON));
        locations.add(new Location("Ecole", "", 600, 450, 50, 50, Constants.Colors.ECOLE));
        locations.add(new Location("Entreprise", "", 900, 350, 50, 50, Constants.Colors.ENTREPRISE));
        locations.add(new Location("Carrefour", "", 400, 200, 50, 50, Constants.Colors.CARREFOUR));
    }
    
    // Méthode appelée à chaque frame pour dessiner l'écran
    @Override
    public void render(float delta) {

        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        

        cityMap.render(game.batch, Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        

        game.batch.begin();

        for (Location loc : locations) {
            float x = loc.getX();
            float y = loc.getY();

            float size = (loc == hoveredLocation) ? 60 : 50;

            float offset = (loc == hoveredLocation) ? -5 : 0;
            

            Texture icon = null;
            if (loc.getName().equals("Maison")) icon = iconHome;
            else if (loc.getName().equals("Ecole")) icon = iconSchool;
            else if (loc.getName().equals("Entreprise")) icon = iconCompany;
            else if (loc.getName().equals("Carrefour")) icon = iconMarket;
            

            if (icon != null) {
                game.batch.draw(icon, x + offset, y + offset, size, size);
            }

            // Dessine le nom du lieu sous l'icône
            game.font.getData().setScale(1.2f);

            game.font.setColor(0f, 0f, 0f, 0.8f);
            game.font.draw(game.batch, loc.getName(), x - 10 + 1, y - 10 - 1);

            game.font.setColor(1f, 1f, 1f, 1f);
            game.font.draw(game.batch, loc.getName(), x - 10, y - 10);
        }
        

        game.font.getData().setScale(2.5f);
        game.font.setColor(1f, 1f, 1f, 1f);
        game.font.draw(game.batch, "SIM LIFE", 30, 690);
        

        if (hoveredLocation != null) {
            game.font.getData().setScale(2f);
            game.font.setColor(1f, 0.8f, 0f, 1f);
            game.font.draw(game.batch, hoveredLocation.getName() + " - Cliquez pour entrer", 20, 40);
        }

        game.batch.end();

        checkMouse();
    }
    

    private void checkMouse() {

        Vector2 mouse = new Vector2(Gdx.input.getX(), Constants.WINDOW_HEIGHT - Gdx.input.getY());

        hoveredLocation = null;
        

        for (Location loc : locations) {

            if (mouse.x >= loc.getX() && mouse.x <= loc.getX() + 50 &&
                mouse.y >= loc.getY() && mouse.y <= loc.getY() + 50) {
                

                hoveredLocation = loc;
                

                if (Gdx.input.justTouched()) {
                    System.out.println("[CLICK] " + loc.getName());
                    // Passe à l'écran du lieu cliqué
                    game.setScreen(new LocationScreen(game, loc, this));
                }
                // Sort de la boucle car on a trouvé le lieu survolé
                break;
            }
        }
    }
    

    @Override
    public void show() {}

    @Override
    public void resize(int width, int height) {}
    

    @Override
    public void pause() {}
    

    @Override
    public void resume() {}
    

    @Override
    public void hide() {}
    

    @Override
    public void dispose() {

        cityMap.dispose();

        iconHome.dispose();
        iconSchool.dispose();
        iconCompany.dispose();
        iconMarket.dispose();
    }
}
