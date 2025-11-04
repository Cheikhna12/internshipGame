package com.internshipquest.graphics;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class CityMapRenderer {
    private TiledMap tiledMap;
    private OrthogonalTiledMapRenderer mapRenderer;
    private OrthographicCamera camera;

    public CityMapRenderer(int width, int height) {
        tiledMap = new TmxMapLoader().load("assets/city_map.tmx");
        mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
        
        camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height);
        camera.position.set(width / 2f, height / 2f, 0);
        camera.update();
    }
    public void render(SpriteBatch batch, int width, int height) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }
    
    public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }
}
