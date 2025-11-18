package com.internshipquest.model.combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionBank {

    private static final Random random = new Random();
    private static final List<Question> allQuestions = new ArrayList<>();

    static {
        initializeQuestions();
    }

    private static void initializeQuestions() {
        
        allQuestions.add(new Question(
            "Qu'est-ce qu'une variable en programmation ?",
            Question.QuestionType.TECH, 2,
            "Expliquer avec des exemples de types (int, String, etc.)",
            "Faire une analogie simple et claire"
        ));
        
        allQuestions.add(new Question(
            "Quelle est la différence entre '==' et '.equals()' en Java ?",
            Question.QuestionType.TECH, 3,
            "Expliquer la comparaison de références vs contenu",
            "Donner des exemples concrets d'utilisation"
        ));
        
        allQuestions.add(new Question(
            "Citez un avantage de la programmation orientée objet.",
            Question.QuestionType.TECH, 2,
            "Parler d'encapsulation, héritage ou polymorphisme",
            "Expliquer avec un exemple de projet réel"
        ));
        
        allQuestions.add(new Question(
            "Qu'est-ce qu'un algorithme ?",
            Question.QuestionType.TECH, 1,
            "Donner une définition technique précise",
            "Utiliser une métaphore de la vie quotidienne"
        ));

        
        allQuestions.add(new Question(
            "Expliquez le concept d'héritage en POO.",
            Question.QuestionType.TECH, 5,
            "Détailler avec des exemples de classes parent/enfant",
            "Présenter les bénéfices pour le travail en équipe"
        ));
        
        allQuestions.add(new Question(
            "Qu'est-ce qu'une API REST ?",
            Question.QuestionType.TECH, 5,
            "Expliquer HTTP, endpoints et méthodes CRUD",
            "Décrire comment vous l'avez utilisée dans un projet"
        ));
        
        allQuestions.add(new Question(
            "Décrivez le pattern MVC (Modèle-Vue-Contrôleur).",
            Question.QuestionType.TECH, 6,
            "Expliquer la séparation des responsabilités",
            "Montrer comment cela facilite le travail collaboratif"
        ));
        
        allQuestions.add(new Question(
            "Comment gérez-vous les erreurs dans votre code ?",
            Question.QuestionType.MIXED, 4,
            "Parler de try-catch, exceptions personnalisées",
            "Expliquer votre processus de debugging et tests"
        ));

        
        allQuestions.add(new Question(
            "Quelle est la différence entre une classe abstraite et une interface ?",
            Question.QuestionType.TECH, 7,
            "Détailler les cas d'usage et limitations de chacune",
            "Expliquer avec des exemples de design patterns"
        ));
        
        allQuestions.add(new Question(
            "Expliquez ce qu'est l'injection de dépendances.",
            Question.QuestionType.TECH, 8,
            "Parler de couplage faible et testabilité",
            "Montrer comment cela améliore la maintenabilité"
        ));
        
        allQuestions.add(new Question(
            "Comment fonctionne le Garbage Collector en Java ?",
            Question.QuestionType.TECH, 8,
            "Expliquer les algorithmes de GC et la gestion mémoire",
            "Décrire l'impact sur les performances d'une app"
        ));
        
        allQuestions.add(new Question(
            "Qu'est-ce que la complexité algorithmique O(n) ?",
            Question.QuestionType.TECH, 7,
            "Expliquer Big O notation avec des exemples",
            "Montrer l'importance pour l'optimisation"
        ));

        
        allQuestions.add(new Question(
            "Aimez-vous travailler en équipe ?",
            Question.QuestionType.SOFTSKILL, 2,
            "Parler de méthodologies agiles et outils collaboratifs",
            "Raconter une expérience positive de travail d'équipe"
        ));
        
        allQuestions.add(new Question(
            "Comment vous organisez-vous pour respecter une deadline ?",
            Question.QuestionType.SOFTSKILL, 3,
            "Mentionner des outils de gestion de projet",
            "Expliquer votre méthode de priorisation des tâches"
        ));
        
        allQuestions.add(new Question(
            "Êtes-vous curieux d'apprendre de nouvelles technologies ?",
            Question.QuestionType.SOFTSKILL, 2,
            "Citer des technologies récentes que vous avez apprises",
            "Parler de votre passion et motivation"
        ));
        
        allQuestions.add(new Question(
            "Que savez-vous de notre entreprise ?",
            Question.QuestionType.SOFTSKILL, 3,
            "Mentionner leur stack technique",
            "Parler de leur culture et valeurs"
        ));

        
        allQuestions.add(new Question(
            "Décrivez une situation où vous avez dû gérer un conflit.",
            Question.QuestionType.SOFTSKILL, 5,
            "Expliquer comment vous avez trouvé une solution technique",
            "Raconter comment vous avez géré les émotions et la communication"
        ));
        
        allQuestions.add(new Question(
            "Comment réagissez-vous face à la critique constructive ?",
            Question.QuestionType.SOFTSKILL, 4,
            "Parler de code review et amélioration continue",
            "Montrer votre ouverture d'esprit et capacité d'adaptation"
        ));
        
        allQuestions.add(new Question(
            "Quelle est votre plus grande fierté dans un projet passé ?",
            Question.QuestionType.SOFTSKILL, 5,
            "Détailler les défis techniques surmontés",
            "Parler de l'impact du projet et du travail d'équipe"
        ));
        
        allQuestions.add(new Question(
            "Comment gérez-vous le stress et la pression ?",
            Question.QuestionType.MIXED, 5,
            "Parler de méthodologies et outils d'optimisation",
            "Expliquer vos techniques de gestion du temps et bien-être"
        ));

        
        allQuestions.add(new Question(
            "Comment motivez-vous les autres membres de votre équipe ?",
            Question.QuestionType.SOFTSKILL, 7,
            "Parler de pair programming et partage de connaissances",
            "Expliquer votre style de leadership et communication"
        ));
        
        allQuestions.add(new Question(
            "Décrivez une situation où vous avez échoué et ce que vous en avez appris.",
            Question.QuestionType.SOFTSKILL, 8,
            "Analyser les erreurs techniques et solutions trouvées",
            "Montrer votre humilité et capacité à rebondir"
        ));
        
        allQuestions.add(new Question(
            "Où vous voyez-vous dans 5 ans ?",
            Question.QuestionType.SOFTSKILL, 6,
            "Parler d'évolution technique (architecte, tech lead)",
            "Exprimer vos ambitions et valeurs professionnelles"
        ));
        
        allQuestions.add(new Question(
            "Comment gérez-vous les désaccords techniques avec vos collègues ?",
            Question.QuestionType.MIXED, 7,
            "Parler de benchmarks, tests et preuves de concept",
            "Expliquer l'importance du dialogue et du compromis"
        ));

        
        allQuestions.add(new Question(
            "Quel est votre plus grand défaut ?",
            Question.QuestionType.PIEGE, 6,
            "Mentionner un défaut technique que vous travaillez à améliorer",
            "Transformer un défaut en qualité (perfectionnisme, etc.)"
        ));
        
        allQuestions.add(new Question(
            "Pourquoi devrions-nous vous choisir plutôt qu'un autre candidat ?",
            Question.QuestionType.PIEGE, 7,
            "Mettre en avant vos compétences techniques uniques",
            "Parler de votre motivation et fit culturel"
        ));
        
        allQuestions.add(new Question(
            "Si vous pouviez être un animal, lequel seriez-vous et pourquoi ?",
            Question.QuestionType.PIEGE, 5,
            "Faire un lien avec des qualités de développeur",
            "Montrer votre créativité et sens de l'humour"
        ));
        
        allQuestions.add(new Question(
            "Pourquoi avez-vous quitté votre dernier poste/école ?",
            Question.QuestionType.PIEGE, 6,
            "Parler de recherche de défis techniques",
            "Rester positif et parler d'évolution de carrière"
        ));
        
        allQuestions.add(new Question(
            "Combien de temps comptez-vous rester chez nous ?",
            Question.QuestionType.PIEGE, 7,
            "Parler de projets techniques à long terme",
            "Montrer votre engagement tout en restant honnête"
        ));
        
        allQuestions.add(new Question(
            "Que feriez-vous si vous n'étiez pas d'accord avec votre manager ?",
            Question.QuestionType.PIEGE, 8,
            "Proposer des solutions basées sur des données",
            "Expliquer l'importance du dialogue et de la hiérarchie"
        ));

        
        allQuestions.add(new Question(
            "Parlez-moi de votre projet le plus complexe.",
            Question.QuestionType.MIXED, 6,
            "Détailler l'architecture et les technologies utilisées",
            "Raconter l'histoire du projet et les défis humains"
        ));
        
        allQuestions.add(new Question(
            "Comment restez-vous à jour avec les nouvelles technologies ?",
            Question.QuestionType.MIXED, 4,
            "Citer des ressources techniques (blogs, conférences)",
            "Parler de votre curiosité et passion pour l'apprentissage"
        ));
        
        allQuestions.add(new Question(
            "Décrivez votre workflow de développement idéal.",
            Question.QuestionType.MIXED, 5,
            "Parler de CI/CD, tests, code review",
            "Expliquer l'importance de la collaboration et communication"
        ));
    }

    public static Question getRandomQuestion(Question.QuestionType type, int minDifficulty, int maxDifficulty) {
        List<Question> filtered = new ArrayList<>();
        
        for (Question q : allQuestions) {
            if (q.getType() == type && 
                q.getDifficulty() >= minDifficulty && 
                q.getDifficulty() <= maxDifficulty) {
                filtered.add(q);
            }
        }
        
        if (filtered.isEmpty()) {
            
            for (Question q : allQuestions) {
                if (q.getType() == type) {
                    filtered.add(q);
                }
            }
        }
        
        if (filtered.isEmpty()) {
            
            return new Question(
                "Parlez-moi de vous et de votre parcours.",
                Question.QuestionType.MIXED, 3,
                "Présenter vos compétences techniques",
                "Raconter votre histoire et motivations"
            );
        }
        
        return filtered.get(random.nextInt(filtered.size()));
    }

    public static Question getRandomQuestionAny(int minDifficulty, int maxDifficulty) {
        List<Question> filtered = new ArrayList<>();
        
        for (Question q : allQuestions) {
            if (q.getDifficulty() >= minDifficulty && q.getDifficulty() <= maxDifficulty) {
                filtered.add(q);
            }
        }
        
        if (filtered.isEmpty()) {
            return allQuestions.get(random.nextInt(allQuestions.size()));
        }
        
        return filtered.get(random.nextInt(filtered.size()));
    }

    @Deprecated
    public static String getRandomQuestion(String type, String difficulte) {
        int minDiff = 1, maxDiff = 3;
        
        switch (difficulte.toUpperCase()) {
            case "FACILE":
                minDiff = 1; maxDiff = 3;
                break;
            case "MOYEN":
                minDiff = 4; maxDiff = 6;
                break;
            case "DIFFICILE":
            case "EXTREME":
                minDiff = 7; maxDiff = 9;
                break;
        }
        
        Question.QuestionType qType;
        switch (type.toUpperCase()) {
            case "TECH":
                qType = Question.QuestionType.TECH;
                break;
            case "SOFTSKILL":
                qType = Question.QuestionType.SOFTSKILL;
                break;
            case "PIEGE":
                qType = Question.QuestionType.PIEGE;
                break;
            default:
                qType = Question.QuestionType.MIXED;
        }
        
        Question q = getRandomQuestion(qType, minDiff, maxDiff);
        return q.getText();
    }
}