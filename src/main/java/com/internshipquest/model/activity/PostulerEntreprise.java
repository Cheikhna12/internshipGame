package com.internshipquest.model.activity;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.Day;
import com.internshipquest.model.combat.Entreprise;
import com.internshipquest.model.hero.AHero;
import com.internshipquest.screens.EntretienScreen;


public class PostulerEntreprise extends AActivity {
    
    private final Entreprise entreprise;
    private final InternshipQuestGame game;
    

    public PostulerEntreprise(Entreprise entreprise, InternshipQuestGame game) {
        super("Postuler chez " + entreprise.getName() + " (" + entreprise.getDifficulte() + ")",
              0, 5, 0);
        this.entreprise = entreprise;
        this.game = game;
    }
    

    @Override
    public void doIt(AHero hero, Day day) {
        if (entreprise.isDejaPostule()) {
            this.message = "Vous avez deja postule chez " + entreprise.getName() + " !";
            System.out.println("[POSTULATION] Déjà postulé chez " + entreprise.getName());
            return;
        }
        
        if (hero.getEnergy() < energyUse) {
            this.message = "Vous etes trop fatigue pour passer un entretien...";
            System.out.println("[POSTULATION] Pas assez d'énergie");
            return;
        }
        
        hero.setEnergy(hero.getEnergy() - energyUse);
        
        System.out.println("[POSTULATION] Lancement de l'entretien chez " + entreprise.getName());
        game.setScreen(new EntretienScreen(game, entreprise, game.getScreen()));
        
        this.message = "Entretien en cours chez " + entreprise.getName() + "...";
    }
}
