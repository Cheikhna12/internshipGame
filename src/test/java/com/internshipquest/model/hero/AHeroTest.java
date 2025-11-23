package com.internshipquest.model.hero;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class AHeroTest {

    private TestHero hero;


    private static class TestHero extends AHero {
        public TestHero(String name, int endurance, int social, int luck, int skills, int stress, int money, int energy) {
            super(name, endurance, social, luck, skills, stress, money, energy, null);
        }
    }
    @BeforeEach
    void setUp() {
        hero = new TestHero("TestHero", 50, 50,50, 50,50,100,80);
    }
    @Test
    @DisplayName("Test setter endurance - valeur normal")
    void testSetEndurance_Normalvalue() {
        hero.setEndurance(75);
        assertEquals(75, hero.getEndurance(), "L'endurance devrait être 75");
    }

    @Test
    @DisplayName("Test setter endurance - valeur supérieur à 100 doit être plafonnée")
    void testSetEndurance_AboveMax_ShouldCapAt100() {
        hero.setEndurance(150);
        assertEquals(100, hero.getEndurance(), "L'endurance ne devrait pas depasser 100");
    }
    @Test
    @DisplayName("Test setter endurance - Valeur négative doit être à 0")
    void testSetEndurance_Negative_ShouldBeZero() {
        hero.setEndurance(-20);
        assertEquals(0, hero.getEndurance(), "L'endurance négative devrait être ramenée à 0");
    }
    @Test
    @DisplayName("Test setter social - Valeur supérieure à 100 doit être plafonnée")
    void testSetSocial_AboveMax_ShouldCapAt100() {
        hero.setSocial(120);
        assertEquals(100, hero.getSocial(), "Le social ne devrait pas dépasser 100");
    }
    @Test
    @DisplayName("Test setter social - Valeur négative doit être à 0")
    void testSetSocial_Negative_ShouldBeZero() {
        hero.setSocial(-10);
        assertEquals(0, hero.getSocial(), "Le social négatif devrait être ramené à 0");
    }
    @Test
    @DisplayName("Test setter luck - Valeur supérieure à 100 doit être plafonnée")
    void testSetLuck_AboveMax_ShouldCapAt100() {
        hero.setLuck(200);
        assertEquals(100, hero.getLuck(), "La chance ne devrait pas dépasser 100");
    }
    @Test
    @DisplayName("Test setter codingSkills - Valeur supérieure à 100 doit être plafonnée")
    void testSetCodingSkills_AboveMax_ShouldCapAt100() {
        hero.setCodingSkills(150);
        assertEquals(100, hero.getCodingSkills(), "Les compétences de code ne devraient pas dépasser 100");
    }
    @Test
    @DisplayName("Test setter stress - Valeur négative doit être à 0")
    void testSetStress_Negative_ShouldBeZero() {
        hero.setStress(-30);
        assertEquals(0, hero.getStress(), "Le stress négatif devrait être ramené à 0");
    }
    @Test
    @DisplayName("Test setter money - Valeur négative doit être à 0")
    void testSetMoney_Negative_ShouldBeZero() {
        hero.setMoney(-50);
        assertEquals(0, hero.getMoney(), "L'argent négatif devrait être ramené à 0");
    }

    @Test
    @DisplayName("Test setter nbFood - Valeur négative doit être à 0")
    void testSetNbFood_Negative_ShouldBeZero() {
        hero.setNbFood(-5);
        assertEquals(0, hero.getNbFood(), "Le nombre de nourriture négatif devrait être ramené à 0");
    }

    @Test
    @DisplayName("Test setter satiety - Valeur supérieure à 100 doit être plafonnée")
    void testSetSatiety_AboveMax_ShouldCapAt100() {
        hero.setSatiety(150);
        assertEquals(100, hero.getSatiety(), "La satiété ne devrait pas dépasser 100");
    }

    @Test
    @DisplayName("Test setter energy - Valeur négative doit être à 0")
    void testSetEnergy_Negative_ShouldBeZero() {
        hero.setEnergy(-20);
        assertEquals(0, hero.getEnergy(), "L'énergie négative devrait être ramenée à 0");
    }

    @Test
    @DisplayName("Test méthode newEnergy - Calcul de la nouvelle énergie")
    void testNewEnergy_Calculation() {
        hero.setEndurance(60);
        hero.setStress(20);
        hero.setSatiety(50);
        hero.newEnergy(60);
        // Expected: (60 * 1.5) - 20 = 90 - 20 = 70
        // Endurance devrait diminuer de: 5 * (100-50) / 100 = 2.5 -> 2 (arrondi)
        assertTrue(hero.getEnergy() > 0, "L'énergie devrait être positive");
        assertEquals(58, hero.getEndurance(), "L'endurance devrait avoir diminué de 2");
        assertEquals(0, hero.getSatiety(), "La satiété devrait être à 0 après newEnergy");
    }

    @Test
    @DisplayName("Test getter name")
    void testGetName() {
        assertEquals("TestHero", hero.getName(), "Le nom devrait être 'TestHero'");
    }

    @Test
    @DisplayName("Test position X et Y")
    void testSetAndGetPosition() {
        hero.setX(100.5f);
        hero.setY(200.3f);
        assertEquals(100.5f, hero.getX(), 0.01f, "La position X devrait être 100.5");
        assertEquals(200.3f, hero.getY(), 0.01f, "La position Y devrait être 200.3");
    }
}
