# Combat System - Système d'Entretien

Ce dossier contient le système de combat du jeu, qui simule des entretiens d'embauche sous forme de duels RPG.

## Vue d'ensemble

Le système transforme les entretiens d'embauche en combats stratégiques où le joueur doit répondre à des questions en choisissant entre des réponses techniques ou soft skills.

---

## Fichiers principaux

### 1. **Question.java**
Représente une question d'entretien.

**Points clés :**
- **4 types de questions** via l'enum `QuestionType` :
  - `TECH` : Questions techniques pures
  - `SOFTSKILL` : Questions sur les compétences relationnelles
  - `MIXED` : Questions mixtes (les deux réponses sont valables)
  - `PIEGE` : Questions pièges (choix difficile)

- **Attributs :**
  - `text` : Le texte de la question
  - `difficulty` : Niveau de difficulté (0-3)
  - `optionTech` : Réponse technique
  - `optionSoft` : Réponse soft skill

**Exemple :**
```java
new Question(
    "Qu'est-ce qu'une API REST ?",
    QuestionType.TECH,
    2,
    "Une interface de programmation utilisant HTTP",
    "Une méthode de communication d'équipe"
)
```

---

### 2. **QuestionBank.java**
Banque de questions pour les entretiens.

**Points clés :**
- Contient **toutes les questions** du jeu organisées par type
- Méthodes pour récupérer des questions aléatoires selon :
  - Le type de question souhaité
  - La difficulté
  
**Utilisation :**
- Fournit les questions à `Entretien.java` pendant le combat
- Permet de varier les entretiens à chaque partie

---

### 3. **Entreprise.java**
Représente une entreprise où le joueur peut postuler.

**Points clés :**
- **Attributs principaux :**
  - `name` : Nom de l'entreprise
  - `noteTech` : Niveau technique requis (0-100)
  - `noteSoftSkill` : Niveau soft skills requis (0-100)
  - `salaire` : Calculé automatiquement (`noteTech * 100 + noteSoftSkill`)
  - `difficulte` : "Facile", "Moyen", "Difficile", "Extreme"
  - `dejaPostule` : Empêche de postuler deux fois

- **Méthode clé : `createRh()`**
  - Crée un recruteur (RH) adapté à la difficulté
  - Plus l'entreprise est difficile, plus le RH est exigeant
  - Ajuste le seuil d'acceptation et l'énergie du RH

**Exemple de difficulté :**
```java
"Facile"   → Seuil: 40%, Énergie RH: 30-40
"Moyen"    → Seuil: 60%, Énergie RH: 40-50
"Difficile"→ Seuil: 75%, Énergie RH: 50-60
"Extreme"  → Seuil: 90%, Énergie RH: 60-70
```

---

### 4. **RH.java**
Représente le recruteur (adversaire) pendant l'entretien.

**Points clés :**
- **Attributs :**
  - `noteTech` : Attentes techniques du RH
  - `noteSoftSkill` : Attentes soft skills du RH
  - `niveauEnergie` : Points de vie du RH (comme des HP)
  - `seuilAcceptation` : Score minimum pour être embauché
  - `difficulte` : Calculée automatiquement selon les stats

- **Méthode `diminuerEnergie(int montant)`**
  - Réduit l'énergie du RH quand le joueur répond bien
  - Si l'énergie tombe à 0, le joueur gagne l'entretien

- **Calcul de difficulté :**
  - Moyenne des stats < 40 → "FACILE"
  - Moyenne entre 40-70 → "MOYEN"
  - Moyenne > 70 → "DIFFICILE"

---

### 5. **EntrepriseFactory.java**
Factory pattern pour créer des entreprises prédéfinies.

**Points clés :**
- Crée des entreprises avec des profils variés
- Simplifie l'instanciation des entreprises dans le jeu
- Permet d'ajouter facilement de nouvelles entreprises

**Exemples d'entreprises :**
- Startups (tech élevé, soft moyen)
- Grandes entreprises (équilibré)
- Agences (soft élevé, tech moyen)

---

### 6. **Entretien.java**
Gère la logique complète d'un entretien (le "combat").

**Points clés :**
- **Orchestration du combat :**
  - Tire des questions aléatoires de `QuestionBank`
  - Compare les réponses du joueur avec le type de question
  - Calcule les dégâts infligés au RH
  - Gère la progression de l'entretien

- **Système de scoring :**
  - Bonne réponse → Dégâts au RH
  - Mauvaise réponse → Pénalité pour le joueur
  - Questions MIXED → Les deux réponses sont acceptées
  - Questions PIEGE → Choix difficile avec conséquences

- **Conditions de victoire/défaite :**
  - **Victoire** : Énergie du RH tombe à 0 ET score > seuil d'acceptation
  - **Défaite** : Score trop faible ou énergie du joueur épuisée

- **Calcul du score final :**
  - Basé sur les stats du héros (codingSkills, social, luck)
  - Bonus/malus selon les réponses données
  - Comparé au `seuilAcceptation` du RH

---

## Flux d'un entretien

```
1. Le joueur choisit une Entreprise
2. L'Entreprise crée un RH adapté à sa difficulté
3. Entretien démarre avec le Héros et le RH
4. Boucle de questions :
   - QuestionBank fournit une Question aléatoire
   - Le joueur choisit : réponse Tech ou Soft
   - Entretien calcule si c'est correct
   - Énergie du RH diminue si bonne réponse
5. Fin de l'entretien :
   - Calcul du score final
   - Comparaison avec seuilAcceptation
   - Victoire → Embauché ! / Défaite → Rejeté
```

---

## Équilibrage du jeu

Le système est équilibré via plusieurs paramètres :

- **Difficulté des entreprises** : Ajuste le seuil d'acceptation
- **Stats du héros** : Influencent le score final
- **Type de questions** : Certaines favorisent tech ou soft
- **Énergie du RH** : Plus haute = plus de questions nécessaires

---

## Exemples d'utilisation

### Créer une entreprise
```java
Entreprise startup = new Entreprise(
    "TechCorp",
    "Startup innovante",
    80,  // noteTech élevée
    50,  // noteSoftSkill moyenne
    "Difficile"
);
```

### Lancer un entretien
```java
RH recruteur = startup.createRh();
Entretien entretien = new Entretien(hero, recruteur, questionBank);
entretien.demarrer();
```

### Répondre à une question
```java
Question q = questionBank.getRandomQuestion(QuestionType.TECH);
boolean correct = entretien.repondre(q, "TECH"); // ou "SOFT"
```

---

## Tests unitaires

Le fichier `QuestionTest.java` teste :
- Création des questions
- Validation des types
- Vérification des difficultés
- Intégrité des options de réponse

**Commande :** `mvn test -Dtest=QuestionTest`

---

## Points d'amélioration possibles

- Ajouter plus de types de questions (CULTURE, LOGIQUE, etc.)
- Système de combo pour réponses consécutives correctes
- Réactions dynamiques du RH selon les réponses
- Questions contextuelles selon l'entreprise
- Système de réputation affectant les entretiens futurs
