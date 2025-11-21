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
        super("Apply to " + entreprise.getName() + " (Salary: " + entreprise.getSalaire() + " euros/month)",
                2, 5, 0);
        this.entreprise = entreprise;
        this.game = game;
    }


    @Override
    public void doIt(AHero hero, Day day) {
        if (entreprise.isDejaPostule()) {
            this.message = "You have already applied to " + entreprise.getName() + " !";
            return;
        }
        hero.setEnergy(hero.getEnergy() - energyUse);
        game.setScreen(new EntretienScreen(game, entreprise, game.getScreen()));
    }
}
