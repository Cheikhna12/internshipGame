package com.internshipquest.model.combat;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class QuestionBank {

    private static final Random random = new Random();
    private static final Map<String, List<String>> questions = new HashMap<>();

    static {
        // Questions Techniques
        questions.put("TECH_FACILE", List.of(
                "Qu'est-ce qu'une variable en programmation ?",
                "Quelle est la différence entre '==' et '.equals()' en Java ?",
                "Citez un avantage de la programmation orientée objet."
        ));
        questions.put("TECH_MOYEN", List.of(
                "Expliquez le concept d'héritage.",
                "Qu'est-ce qu'une API REST ?",
                "Décrivez le pattern Modèle-Vue-Contrôleur (MVC)."
        ));
        questions.put("TECH_DIFFICILE", List.of(
                "Quelle est la différence entre une classe abstraite et une interface ?",
                "Expliquez ce qu'est l'injection de dépendances.",
                "Comment fonctionne le Garbage Collector en Java ?"
        ));

        // Questions Soft Skills
        questions.put("SOFTSKILL_FACILE", List.of(
                "Aimez-vous travailler en équipe ?",
                "Comment vous organisez-vous pour respecter une deadline ?",
                "Êtes-vous curieux d'apprendre de nouvelles choses ?"
        ));
        questions.put("SOFTSKILL_MOYEN", List.of(
                "Décrivez une situation où vous avez dû gérer un conflit.",
                "Comment réagissez-vous face à la critique constructive ?",
                "Quelle est votre plus grande fierté dans un projet passé ?"
        ));
        questions.put("SOFTSKILL_DIFFICILE", List.of(
                "Comment motivez-vous les autres membres de votre équipe ?",
                "Décrivez une situation où vous avez échoué et ce que vous en avez appris.",
                "Où vous voyez-vous dans 5 ans ?"
        ));

        // Questions Pièges
        questions.put("PIEGE", List.of(
                "Quel est votre plus grand défaut ?",
                "Pourquoi devrions-nous vous choisir plutôt qu'un autre candidat ?",
                "Si vous pouviez être un animal, lequel seriez-vous et pourquoi ?"
        ));
    }


    public static String getRandomQuestion(String type, String difficulte) {
        String key = type + "_" + difficulte;
        if (type.equals("PIEGE")) {
            key = "PIEGE";
        }

        List<String> questionList = questions.get(key);
        if (questionList == null || questionList.isEmpty()) {
            return "Parlez-moi de vous.";
        }

        int index = random.nextInt(questionList.size());
        return questionList.get(index);
    }
}