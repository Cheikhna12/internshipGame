# Comment créer un nouvel Event

Un **Event** est un événement global dont le but est de mettre de l'imprévu dans le déroulement de la partie.
Chaque nuit le jeu à 50% de chance de déclencher un evenement aléatoire.
Il en existe 3 sortes:
- direct : effet pendant la nuit sur le héro (perte ou gain de stat, de nourriture)
- lié à un lieu: permet de générer une activité supplémentaire facultative exclusive au jour.
- lié à l'entrée dans un batiment. l'évenement est obligatoirement déclenché en entrant.

# création d'un nouvelle event 

Dans le dossier/package model.event, dupliquer un event 

le constructeur sert juste à afficher le message sur le screen **NightScreen**.

## Cas de l'event direct:

l'effet est directement présent dans la methode **applyEffect()**

## Cas de l'event lié à un lieu

l'effet de applyEffect() est juste de générer le code de l'event dans la class Day, le code est repassé à 0 soit par l'action (possibilité de faire l'action qu'une seul fois, soit par la nuit, possibilité de faire l'action plusieurs fois par jour)
le code Event est alors vérifier dans la Factory de l'event associé.

```
public static List<AActivity> getEpitechActivities(ALieuVisitable lieu, Day day) {
List<AActivity> list = new ArrayList<>();
list.add(new Study());
if (day.getCodeEvent()==1){list.add(new MeetUp(lieu,day));}
```
## Cas de l'event lié à l'entrée dans un batiment
l'effet de applyEffect() est juste de générer le code de l'event dans la class Day, le code est repassé à 0 soit par l'action
le code Event est alors vérifier dans la methode *onEnter()* du lieu. 

le lieu lors de la encontre commence par lancer l'évènement si le code event correspond.
```
  public void onEnter(AHero hero,Day day) {
        // nom, loop or not, volume %
        SoundManager.playMusic("gym", true, 0.8f);
        if (day.getCodeEvent()==3) {
            if (hero.getLuck()>20){
            hero.setMoney(hero.getMoney() + 15);
            currentMessage = "You find a stolen wallet on the ground, you return it to its owner.\n He thanks you by giving you 15 euros.";
            showingMessage = true;
            messageTimer = 0f;

            day.setCodeEvent(0);}
        }
```
le message s'affichera via l'écran *LocationScreen*
le code est remis à 0 si l'event ne peux plus se déclencher dans un autre lieu


