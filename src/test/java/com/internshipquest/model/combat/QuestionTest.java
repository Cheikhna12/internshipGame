package com.internshipquest.model.combat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;


class QuestionTest {
 private Question techQuestion;
 private Question softSkillQuestion;
 private Question mixedQuestion;
 private Question piegeQuestion;

 @BeforeEach
 public void setUp() {
     techQuestion = new Question(
             "Qu'est-ce que qu'une API REST",
             Question.QuestionType.TECH,
             2,
             "Une interface de programmation utilisant HTTP",
             "Une méthode de communication d'équipe"
     );
     softSkillQuestion = new Question(
             "Comment gérez-vous les conflits en équipe ?",
             Question.QuestionType.SOFTSKILL,
             1,
             "J'utilise Git pour résoudre les conflits",
             "J'écoute et je cherche un compromis"
     );

     mixedQuestion = new Question(
             "Comment organisez-vous votre code ?",
             Question.QuestionType.MIXED,
             3,
             "J'utilise des design patterns",
             "Je collabore avec l'équipe"
     );

     piegeQuestion = new Question(
             "Travaillez-vous bien sous pression ?",
             Question.QuestionType.PIEGE,
             2,
             "Je code 24h/24",
             "Je gère mon stress et priorise"
     );
 }

 @Test
 @DisplayName("Test création question technique - verification du type")
 void testTechQuestion_getType() {
     assertEquals(Question.QuestionType.TECH, techQuestion.getType(), "Le type devrait être TECH");
 }
 @Test
 @DisplayName("Test de création question technique - verification de la difficulté")
 void testTechQuestion_GetDifficulty() {
     assertEquals(2,techQuestion.getDifficulty(), "La difficulté devrait être 2");
 }
 @Test
 @DisplayName("Test de création question technique - verification option technique")
 void testTechQuestion_GetOptionTech() {
     assertEquals("Une interface de programmation utilisant HTTP",techQuestion.getOptionTech(),"L'option technique devrait correspondre");
 }
 @Test
    @DisplayName("Test de création question soft - verification option soft")
    void testTechQuestion_GetOptionSoft() {
     assertEquals("Une méthode de communication d'équipe", techQuestion.getOptionSoft(),"L'option soft devrait correspondre");
 }
 @Test
    @DisplayName("Test création question softskill - verification du type")
    void testSoftSkillQuestion_GetType() {
     assertEquals(Question.QuestionType.SOFTSKILL, softSkillQuestion.getType(), "Le type devrait être SOFTSKILL");
 }
    @Test
    @DisplayName("Test question soft skill - Difficulté")
    void testSoftSkillQuestion_GetDifficulty() {
        assertEquals(1, softSkillQuestion.getDifficulty(),
                "La difficulté devrait être 1");
    }

    @Test
    @DisplayName("Test question mixte - Type correct")
    void testMixedQuestion_GetType() {
        assertEquals(Question.QuestionType.MIXED, mixedQuestion.getType(),
                "Le type devrait être MIXED");
    }

    @Test
    @DisplayName("Test question mixte - Difficulté élevée")
    void testMixedQuestion_HighDifficulty() {
        assertEquals(3, mixedQuestion.getDifficulty(),
                "La difficulté devrait être 3");
    }

    @Test
    @DisplayName("Test question piège - Type correct")
    void testPiegeQuestion_GetType() {
        assertEquals(Question.QuestionType.PIEGE, piegeQuestion.getType(),
                "Le type devrait être PIEGE");
    }

    @Test
    @DisplayName("Test question piège - Options disponibles")
    void testPiegeQuestion_BothOptionsAvailable() {
        assertNotNull(piegeQuestion.getOptionTech(),
                "L'option technique ne devrait pas être null");
        assertNotNull(piegeQuestion.getOptionSoft(),
                "L'option soft ne devrait pas être null");
    }

    @Test
    @DisplayName("Test création question avec difficulté 0")
    void testQuestion_DifficultyZero() {
        Question easyQuestion = new Question(
                "Question facile",
                Question.QuestionType.TECH,
                0,
                "Option A",
                "Option B"
        );

        assertEquals(0, easyQuestion.getDifficulty(),
                "La difficulté 0 devrait être acceptée");
    }

    @Test
    @DisplayName("Test toutes les valeurs d'enum QuestionType")
    void testQuestionType_AllEnumValues() {
        assertEquals(4, Question.QuestionType.values().length,
                "Il devrait y avoir 4 types de questions");

        assertNotNull(Question.QuestionType.valueOf("TECH"));
        assertNotNull(Question.QuestionType.valueOf("SOFTSKILL"));
        assertNotNull(Question.QuestionType.valueOf("MIXED"));
        assertNotNull(Question.QuestionType.valueOf("PIEGE"));
    }

    @Test
    @DisplayName("Test immutabilité des getters")
    void testQuestion_GettersReturnSameValue() {
        String text1 = techQuestion.getText();
        String text2 = techQuestion.getText();

        assertSame(text1, text2,
                "Les getters devraient retourner la même référence");
    }
}
