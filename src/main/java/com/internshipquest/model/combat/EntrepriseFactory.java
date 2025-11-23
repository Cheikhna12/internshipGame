package com.internshipquest.model.combat;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseFactory {

    public static List<Entreprise> createAllEntreprises() {
        List<Entreprise> entreprises = new ArrayList<>();
        entreprises.add(new Entreprise(
                "WebStart Inc.",
                "A small start-up looking for young talents",
                30,
                40,
                "Facile"
        ));

        entreprises.add(new Entreprise(
                "Pixel Agency",
                "Web agency specialized in front-end development",
                40,
                45,
                "Facile"
        ));

        entreprises.add(new Entreprise(
                "DataCorp",
                "Small company specialized in data management",
                55,
                50,
                "Moyen"
        ));

        entreprises.add(new Entreprise(
                "CloudTech Solutions",
                "Supplier of cloud solution to large companies",
                65,
                60,
                "Moyen"
        ));

        entreprises.add(new Entreprise(
                "AI Innovations",
                "World leader in AI development and machine learning",
                75,
                70,
                "Difficile"
        ));

        entreprises.add(new Entreprise(
                "CyberSec Elite",
                "Cybersecurity expert for banking companies",
                85,
                75,
                "Difficile"
        ));

        entreprises.add(new Entreprise(
                "GAFAM Corp",
                "The biggest tech company in the world, only the world's best candidates may apply",
                95,
                85,
                "Extrême"
        ));

        return entreprises;
    }
}