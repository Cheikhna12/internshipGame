# Main.java

permet le lancement du jeu : 
pends dans les constantes la taille de l'écran, le titre et défini aussi les FPS

# Internshipquest.java
## create()

```batch = new SpriteBatch();```
→ Initialise le moteur de rendu 2D pour les sprites.
```font = loadDMSerifFont(32);```
→ créé ka police à la taile de 32 pixels
```SoundManager.loadSounds();```
charge les sons et musiques du jeu

```setScreen(new Menu(this));```
→ Le jeu commence sur l’écran de *selection des joueurs*.

## loadDMSerifFont

permet de charger une autre police que celle par défaut (qui est trop petite).
```com.badlogic.gdx.graphics.Texture.TextureFilter.Linear;``` sert à définir comment se comporte la police quand on utilise le scale


## dispose()
Libère la mémoire quand le jeu se ferme.
Dans LibGDX, tout ce qui utilise la carte graphique (textures, sons, polices…) doit être nettoyé manuellement.

