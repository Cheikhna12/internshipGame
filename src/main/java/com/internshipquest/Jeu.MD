# Main.java

permet le lancement du jeu : 
pends dans les constantes la taille de l'écran, le titre et défini aussi les FPS

# Internshipquest.java

batch = new SpriteBatch();
→ Initialise le moteur de rendu 2D pour les sprites.

shapeRenderer = new ShapeRenderer();
→ Prépare le moteur pour dessiner des formes (utile pour les contours, HUD, etc.).

font = new BitmapFont();
→ Crée une police par défaut pour le texte.

font.getData().setScale(2f);
→ Double la taille de la police pour la rendre plus visible.

this.setScreen(new WorldMapScreen(this));
→ Le jeu commence sur l’écran “carte du monde”.


## dispose()
Libère la mémoire quand le jeu se ferme.
Dans LibGDX, tout ce qui utilise la carte graphique (textures, sons, polices…) doit être nettoyé manuellement.

