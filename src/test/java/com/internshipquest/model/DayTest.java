package com.internshipquest.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

class DayTest {

    private Day day;

    @BeforeEach
    void setUp() {
        // Créer une instance Day avec game et hero null pour les tests de base
        // Cela teste la logique sans dépendances LibGDX
        day = new Day(null, null);
    }

    @Test
    @DisplayName("Test valeur initiale du jour - devrait commencer à 1")
    void testInitialDay() {
        // Le constructeur Day incrémente le jour de 1, donc il devrait être 1
        assertEquals(1, day.getDay(), "Le jour devrait commencer à 1");
    }

    @Test
    @DisplayName("Test valeur initiale de l'heure - devrait commencer à 7")
    void testInitialHour() {
        assertEquals(7, day.getHour(), "L'heure devrait commencer à 7");
    }

    @Test
    @DisplayName("Test valeur initiale de hourAfterMidnight - devrait être 0")
    void testInitialHourAfterMidnight() {
        assertEquals(0, day.getHourAfterMidnight(), "L'heure après minuit devrait être 0");
    }

    @Test
    @DisplayName("Test détection weekend - jour 6 devrait être un weekend")
    void testIsWeekend_Day6() {
        day.setDay(6);
        assertTrue(day.isWeekend(), "Le jour 6 devrait être un weekend");
    }

    @Test
    @DisplayName("Test détection weekend - jour 7 devrait être un weekend")
    void testIsWeekend_Day7() {
        day.setDay(7);
        assertTrue(day.isWeekend(), "Le jour 7 devrait être un weekend");
    }

    @Test
    @DisplayName("Test détection weekend - jour 5 ne devrait pas être un weekend")
    void testIsWeekend_Day5() {
        day.setDay(5);
        assertFalse(day.isWeekend(), "Le jour 5 ne devrait pas être un weekend");
    }

    @Test
    @DisplayName("Test détection weekend - jour 12 devrait être un weekend (multiple de 6)")
    void testIsWeekend_Day12() {
        day.setDay(12);
        assertTrue(day.isWeekend(), "Le jour 12 devrait être un weekend (multiple de 6)");
    }

    @Test
    @DisplayName("Test détection weekend - jour 14 devrait être un weekend (multiple de 7)")
    void testIsWeekend_Day14() {
        day.setDay(14);
        assertTrue(day.isWeekend(), "Le jour 14 devrait être un weekend (multiple de 7)");
    }

    @Test
    @DisplayName("Test setHour - devrait mettre à jour l'heure correctement")
    void testSetHour() {
        day.setHour(15);
        assertEquals(15, day.getHour(), "L'heure devrait être mise à jour à 15");
    }

    @Test
    @DisplayName("Test setDay - devrait mettre à jour le jour correctement")
    void testSetDay() {
        day.setDay(10);
        assertEquals(10, day.getDay(), "Le jour devrait être mis à jour à 10");
    }

    @Test
    @DisplayName("Test setHourAfterMidnight - devrait mettre à jour correctement")
    void testSetHourAfterMidnight() {
        day.setHourAfterMidnight(2);
        assertEquals(2, day.getHourAfterMidnight(), "L'heure après minuit devrait être 2");
    }

    @Test
    @DisplayName("Test setNightTriggered - devrait mettre à jour l'état de la nuit")
    void testSetNightTriggered() {
        day.setNightTriggered(true);
        // On ne peut pas tester directement nightTriggered car c'est privé,
        // mais on peut vérifier les changements de comportement
        day.setHour(20);
        day.setHourAfterMidnight(0);
        day.addHour(2);
        // Si la nuit est déjà déclenchée, l'heure incrémente hourAfterMidnight
        assertEquals(2, day.getHour(), "L'heure devrait être 2 (hourAfterMidnight)");
        assertEquals(2, day.getHourAfterMidnight(), "L'heure après minuit devrait être 2");
    }

    @Test
    @DisplayName("Test getter et setter de codeEvent")
    void testCodeEvent() {
        assertEquals(0, day.getCodeEvent(), "Le code événement initial devrait être 0");
        
        day.setCodeEvent(1);
        assertEquals(1, day.getCodeEvent(), "Le code événement devrait être 1 (meetup)");
        
        day.setCodeEvent(2);
        assertEquals(2, day.getCodeEvent(), "Le code événement devrait être 2 (promo nourriture)");
    }

    @Test
    @DisplayName("Test addHour - progression normale du temps")
    void testAddHour_NormalProgression() {
        day.setHour(10);
        day.setNightTriggered(false);
        day.addHour(3);
        assertEquals(13, day.getHour(), "L'heure devrait progresser de 10 à 13");
    }

    @Test
    @DisplayName("Test addHour - passage du seuil de minuit")
    void testAddHour_CrossingMidnight() {
        day.setHour(22);
        day.setNightTriggered(false);
        day.addHour(3);
        // Après avoir dépassé 24, l'heure devrait être 1 (22 + 3 = 25, 25 - 24 = 1)
        assertEquals(1, day.getHour(), "L'heure devrait être 1 après avoir passé minuit");
        assertEquals(1, day.getHourAfterMidnight(), "L'heure après minuit devrait être 1");
    }

    @Test
    @DisplayName("Test plusieurs jours de weekend")
    void testMultipleWeekendDays() {
        // Tester une plage de jours
        for (int i = 1; i <= 21; i++) {
            day.setDay(i);
            boolean expectedWeekend = (i % 6 == 0 || i % 7 == 0);
            assertEquals(expectedWeekend, day.isWeekend(), 
                "Le statut weekend du jour " + i + " devrait être " + expectedWeekend);
        }
    }
}
