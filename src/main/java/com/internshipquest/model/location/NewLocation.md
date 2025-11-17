# Comment créer un nouveau *lieu*

## 1. Créer une nouvelle classe de lieu

Dans le dossier/package model.location, dupliquer un lieu existant

### dans les attributs
changer la texture NPC ou supprimer (si pas de NPC dans ce lieu);

### dans le constructeur: 
- changer le nom de la classe et du controleur
- changer les horaires d'ouvertures
```  
this.openHour = 8;
this.closedHour = 19;
```
si besoin d'aller à plus de minuit mettre 25 -28 h pour que le system de controle de capacité de réalisation d'une tache vérifie s'il reste assez de temps.

- changer le nom dans le  getNewLieuActivities()
- supprimer  ou changer l'image du NPC.

### dans la method onEnter()
- supprimer tout sauf le SoundManager (elles servent à gérer les event qui se déclanche ne entant dans un lieu)
- changer la musique du soundManager

### dans la method onExit()
- ne rien changer

### dans les autres methods.
 -si pas de NPC dans le lieu, supprimer les methods sinon changer les nom des textures et la string d'accueil

------------------------------------------------------------------------

##  2. Définir les activités du lieu

Dans `ActivityFactory.java`, ajouter une méthode getXXXActivities():
et ajouter des Activité via list.add(new YYY).

``` java
public static List<AActivity> getXXXActivities() {
    List<AActivity> list = new ArrayList<>();
    list.add(new StudyActivity());
    list.add(new ReadActivity());
    return list;
}
```

------------------------------------------------------------------------

## 3. Ajouter le lieu à la ville

Dans **LocationFactory.java** :

### dans getVisitableLocation

ajouter le lieu
``` 
lieux.add(new XXX(game));
```
### dans createAllLocations()

ajouter sa position sur la map et son nom

### dans createBackground()
ajouter un *case* spécifiant l'image de background du lieu




------------------------------------------------------------------------

## 🎨 4. Optionnel : Ajouter un NPC

Surcharger :

``` java
@Override
public Texture getNpcTexture() { ... }

@Override
public String getNpcMessage() { ... }
```

------------------------------------------------------------------------

## ✔️ Le lieu est maintenant fonctionnel !

Il apparaîtra dans la liste des lieux visitables, gérera les activités,
et utilisera automatiquement le système d'affichage de messages
d'ALieuVisitable.