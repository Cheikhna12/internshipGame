# Comment creer une nouvelle activité

## créer la classe

dans le dossier/package activity: 
- dupliquer une action
- changer le nom de la classe et du controleur 
- changer le cout en energie et et temps dans le super
- créer les effets

## Affecter l'activité à un lieu 

- Dans ActvityFactory,chercher la methode getYYYYActivities de votre lieu:
- inserer list.add(new XXX()); 
- verifier que votre constructeur a bien ses parametres d'entrée lors de son appel dans le getXXXActivity(YYY)
```
   public static List<AActivity> getCloverFieldActivities() { 
   List<AActivity> list = new ArrayList<>();
   list.add(new FindClover());
   list.add(new Rest());
   return list;
   }

   public static List<AActivity> getEpitechActivities(ALieuVisitable lieu, Day day) {
   List<AActivity> list = new ArrayList<>();
   list.add(new Study());
   list.add(new ChessClub());
   if (day.getCodeEvent()==1){list.add(new MeetUp(lieu,day));}
   list.add(new SnackDispenserEpitech());
   list.add(new AskOpeningHours(lieu));
   return list;
   }
```
- si vous ajouter un parametre à getActivity, il faut penser à aller son appel dans la class correspondante
``` activities = ActivityFactory.getEpitechActivities(this,day);```