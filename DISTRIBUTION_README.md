# Internship Quest Game - Guide d'Installation

## Contenu du Package

Ce package contient tout ce dont vous avez besoin pour jouer à Internship Quest Game :

```
internship-quest-game/
├── internship-quest-game-1.0-SNAPSHOT.jar  (161 MB - Jeu exécutable)
├── lancer-jeu.sh                            (Script Linux/Mac)
├── lancer-jeu.bat                           (Script Windows)
└── DISTRIBUTION_README.md                   (Ce fichier)
```

---

##  Prérequis

**Java 17 ou supérieur** doit être installé sur votre ordinateur.

### Vérifier si Java est installé

**Windows :**
```cmd
java -version
```

**Linux/Mac :**
```bash
java -version
```

Si Java n'est pas installé, téléchargez-le ici :
- **Recommandé** : [Eclipse Temurin (Adoptium)](https://adoptium.net/)
- Alternative : [Oracle JDK](https://www.oracle.com/java/technologies/downloads/)

---

## Lancement du Jeu

### Windows

**Méthode 1 : Double-clic**
- Double-cliquez sur `lancer-jeu.bat`

**Méthode 2 : Ligne de commande**
```cmd
lancer-jeu.bat
```

**Méthode 3 : Directement avec Java**
```cmd
java -jar internship-quest-game-1.0-SNAPSHOT.jar
```

### Linux / Mac

**Méthode 1 : Script**
```bash
./lancer-jeu.sh
```

**Méthode 2 : Directement avec Java**
```bash
java -jar internship-quest-game-1.0-SNAPSHOT.jar
```

---

## Comment Jouer

### Objectif
Trouvez une alternance avant la fin du temps imparti en gérant vos statistiques et en relevant des défis !

### Contrôles
- **Déplacement** : Flèches directionnelles
- **Interaction** : Cliquez sur les lieux pour interagir
- **Menu** : Suivez les instructions à l'écran

### Statistiques à gérer
- **Endurance** : Votre résistance physique
- **Social** : Vos compétences sociales
- **Chance** : Votre facteur chance
- **Coding Skills** : Vos compétences en programmation
- **Stress** : Votre niveau de stress (à minimiser)
- **Argent** : Vos finances
- **Énergie** : Votre niveau d'énergie

### Lieux disponibles
- **Epitech** : Étudiez et améliorez vos compétences
- **Carrefour** : Achetez de la nourriture et travaillez
- **Pokécenter** : Récupérez votre énergie
- **FitnessClub** : Améliorez votre endurance
- **Maisons** : Reposez-vous et mangez

---

## Résolution de Problèmes

### Le jeu ne se lance pas

**Problème : "Java n'est pas reconnu"**
- Java n'est pas installé ou pas dans le PATH
- Solution : Installez Java 17+ depuis [Adoptium](https://adoptium.net/)

**Problème : "Unsupported class file major version"**
- Votre version de Java est trop ancienne
- Solution : Mettez à jour vers Java 17 ou supérieur

**Problème : "Could not find or load main class"**
- Le fichier JAR est corrompu
- Solution : Retéléchargez le package complet

### Le jeu est lent

**Solution 1 : Augmenter la mémoire allouée**
```bash
java -Xmx4G -jar internship-quest-game-1.0-SNAPSHOT.jar
```

**Solution 2 : Fermer les autres applications**
- Le jeu nécessite au moins 2 GB de RAM disponible

### Erreur graphique

**Problème : Écran noir ou erreurs OpenGL**
- Vos pilotes graphiques sont obsolètes
- Solution : Mettez à jour vos pilotes graphiques

---

## Configuration Système Recommandée

### Minimum
- **OS** : Windows 10, Linux (Ubuntu 20.04+), macOS 10.14+
- **CPU** : Dual-core 2.0 GHz
- **RAM** : 2 GB disponible
- **GPU** : Support OpenGL 3.0+
- **Espace disque** : 200 MB

### Recommandé
- **OS** : Windows 11, Linux (Ubuntu 22.04+), macOS 12+
- **CPU** : Quad-core 2.5 GHz+
- **RAM** : 4 GB disponible
- **GPU** : Support OpenGL 4.0+
- **Espace disque** : 500 MB

---

## Notes de Version

**Version 1.0-SNAPSHOT**
- Système de carte interactive avec TMX
- Gestion complète des statistiques du héros
- Système de combat par questions (TECH, SOFTSKILL, MIXED, PIEGE)
- Cycle jour/nuit
- Multiples activités et lieux
- Événements aléatoires (meetup, promotions)
- Système de pathfinding intelligent

---

## Support

Pour toute question ou problème :
1. Vérifiez la section "Résolution de Problèmes" ci-dessus
2. Assurez-vous d'avoir Java 17+ installé
3. Vérifiez que tous les fichiers du package sont présents

---

## Licence et Crédits

**Internship Quest Game**
Développé avec LibGDX

**Technologies utilisées :**
- Java 17
- LibGDX 1.12.1
- JUnit 5 (tests)
- Maven (build)

---

## Bon Jeu !

Bonne chance dans votre quête d'alternance ! 
