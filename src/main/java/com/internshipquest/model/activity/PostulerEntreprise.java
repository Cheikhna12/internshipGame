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
        super("Postuler chez " + entreprise.getName() + " (Salaire: " + entreprise.getSalaire() + " euros/mois)",
                2, 5, 0);
        this.entreprise = entreprise;
        this.game = game;
    }


    @Override
    public void doIt(AHero hero, Day day) {
        if (entreprise.isDejaPostule()) {
            this.message = "Vous avez deja postule chez " + entreprise.getName() + " !";
            return;
        }
        hero.setEnergy(hero.getEnergy() - energyUse);
        game.setScreen(new EntretienScreen(game, entreprise, game.getScreen()));
    }
}
