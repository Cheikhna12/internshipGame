# dossier com.intershipquest

## graphics/CityMapRenderer

### cityMapRenderer
tiledMap = new TmxMapLoader().load("assets/city_map.tmx");
**TmxMapLoader()** charge l'ensemble des maps 

mapRenderer = new OrthogonalTiledMapRenderer(tiledMap);
**OrthogonalTiledMapRenderer** affiche la map en lisant le fichier **tiledMap**

Gestion de la partie visible par le joueur:

      camera = new OrthographicCamera();
        camera.setToOrtho(false, width, height); // definit la zone visible false = axe  Y vers le haut
        camera.position.set(width / 2f, height / 2f, 0); // centre la caméra au milieu
        camera.update(); // active les modifications

### dispose

    public void dispose() {
        tiledMap.dispose();
        mapRenderer.dispose();
    }

Libère la mémoire des textures et objets GPU quand tu quittes le jeu.

### render

    public void render(SpriteBatch batch, int width, int height) {
        mapRenderer.setView(camera);
        mapRenderer.render();
    }

??? pas fini