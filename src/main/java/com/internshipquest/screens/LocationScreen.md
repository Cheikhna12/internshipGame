# LocationScreen

## constructor 
permet de creer le lieu quand on arrive + gere la police 

## show()
gère l'image en fond du lieu

## render

delta = temps écoulé depuis le dernier frame (utile pour animations, timers…).
### nettoie l'écran
        float[] color = location.getColor();
        Gdx.gl.glClearColor(color[0] * 0.3f, color[1] * 0.3f, color[2] * 0.3f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);



###  Dessiner le fond (si présent).
    if (gymBackground != null) {
    game.batch.draw(gymBackground, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

### gère le temps avant de remettre le message de base

    if (gym != null) gym.update(delta);
 fonction présente dans les lieux 
 
### affiche les messages des fonctions  ou le message de base.

        if (gym != null && gym.isShowingMessage()) {
            font.getData().setScale(1.5f);
            font.draw(game.batch, gym.getCurrentMessage(), 50, 400);
affiche le message si le message n'est pas encore passé en false par l'update 

        } else {
            actions.clear();
            int yPos = 400;
            if (location.getName().equals("FitnessClub")) {
                addAction("1. Do Push-up", 70, yPos, () -> gym.pushUps(hero));
                addAction("2. Do Deadlift", 70, yPos - 50, () -> gym.deadlift(hero));
            }

sinon affiche les focntions à des endroits défini

## addAction
Crée un bouton invisible pour le texte d’action.

Stocke :
text → ce qu’on affiche comme texte.
x, y → position.
rectangle cliquable pour détecter le clic.
action → la fonction à exécuter si on clique dessus.

## handleInput()

Vérifie toutes les entrées utilisateur :

Clique sur le texte "Return to world Map" → change l’écran vers mapScreen.

Clique sur les actions → exécute la fonction associée (push-ups, deadlift).

Touche ESCAPE → alternative pour revenir à la carte.

!le return sert à sortir rapidement du programme sans verifier les autres actions I

## dispose() 

nettoye la mémoire


## ActionZone 

classe interne pour creer des les zones d'action