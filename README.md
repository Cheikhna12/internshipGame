#  Sim Life - Projet Simple

##  Lancer

```bash
mvn clean install
mvn clean compile
mvn exec:java
```
```

## Code Expliqué

### WorldMapScreen.java (150 lignes)

**Charge les icônes :**
```java
iconHome = new Texture("assets/icon_home.png");
iconSchool = new Texture("assets/icon_school.png");
// etc...
```

**Affiche les icônes :**
```java
game.batch.draw(icon, x, y, size, size);
```

**Plus grande si survolée :**
```java
float size = (loc == hoveredLocation) ? 60 : 50;
```

### LocationScreen.java ()


```java
if (nom.equals("Maison")) {
    // Activités : Dormir, Manger
    game.font.draw(batch, "1. Dormir - Restaure energie", x, y);
    game.font.draw(batch, "2. Manger - Restaure nourriture", x, y);
}
```

**Prêt pour la logique :**
```java
// TODO: Implémenter avec l'alternant Mathys
if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
    // hero.dormir(); // À implémenter
}
```

## Prochaines Étapes

### 1. Créer le modèle Hero

```java
public class Hero {
    private int energie = 100;
    private int nourriture = 100;
    private int argent = 50;
    private int competences = 0;
    
    public void dormir() {
        energie = Math.min(100, energie + 50);
    }
    
    public void manger() {
        nourriture = Math.min(100, nourriture + 30);
    }
    
    public void travailler() {
        if (energie >= 20) {
            energie -= 20;
            argent += 50;
        }
    }
    
    public void etudier() {
        if (energie >= 10) {
            energie -= 10;
            competences += 5;
        }
    }
    
    public void acheterNourriture() {
        if (argent >= 10) {
            argent -= 10;
            nourriture += 20;
        }
    }
}
```

### 2. Ajouter Hero dans le jeu

Dans `IntershipQuestGame.java` :
```java
public Hero hero;

public void create() {
    hero = new Hero();
    // ...
}
```

### 3. Utiliser Hero dans LocationScreen

```java
if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
    if (location.getName().equals("Maison")) {
        game.hero.dormir();
        System.out.println("Énergie: " + game.hero.getEnergie());
    }
}
```

### 4. Afficher les stats

En haut de l'écran :
```java
game.font.draw(batch, "Energie: " + game.hero.getEnergie(), 50, 700);
game.font.draw(batch, "Nourriture: " + game.hero.getNourriture(), 250, 700);
game.font.draw(batch, "Argent: " + game.hero.getArgent(), 450, 700);
```


## Modifier

**Changer position d'un lieu** (ligne 42-45) :
```java
locations.add(new Location("Maison", "", 200, 550, ...));
                                        ↑     ↑
                                        X     Y
```

**Ajouter un 5ème lieu** :
1. Télécharger une icône PNG dans `assets/`
2. Charger : `iconBank = new Texture("assets/icon_bank.png");`
3. Ajouter : `locations.add(new Location("Banque", "", x, y, ...));`
4. Afficher : `if (nom.equals("Banque")) game.batch.draw(iconBank, ...);`

---