# Main.java

permet le lancement du jeu : 
pends dans les constantes la taille de l'écran, le titre et défini aussi les FPS

# Internshipquest.java
## creat()

fonction 
batch = new SpriteBatch();
→ Initialise le moteur de rendu 2D pour les sprites.

font = new BitmapFont();
→ Crée une police par défaut pour le texte.

setScreen(new WorldMapScreen(this));
→ Le jeu commence sur l’écran “carte du monde”.


## dispose()
Libère la mémoire quand le jeu se ferme.
Dans LibGDX, tout ce qui utilise la carte graphique (textures, sons, polices…) doit être nettoyé manuellement.

