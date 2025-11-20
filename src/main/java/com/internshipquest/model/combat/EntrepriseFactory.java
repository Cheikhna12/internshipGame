package com.internshipquest.model.combat;

import java.util.ArrayList;
import java.util.List;

public class EntrepriseFactory {

    public static List<Entreprise> createAllEntreprises() {
        List<Entreprise> entreprises = new ArrayList<>();
        entreprises.add(new Entreprise(
                "WebStart Inc.",
                "Une petite startup dynamique qui cherche des talents bruts.",
                30,
                40,
                "Facile"
        ));

        entreprises.add(new Entreprise(
                "Pixel Agency",
                "Agence web créative spécialisée dans les sites vitrines.",
                40,
                45,
                "Facile"
        ));

        entreprises.add(new Entreprise(
                "DataCorp",
                "PME en pleine croissance dans le secteur de l'analyse de données.",
                55,
                50,
                "Moyen"
        ));

        entreprises.add(new Entreprise(
                "CloudTech Solutions",
                "Fournisseur de solutions cloud pour les grandes entreprises.",
                65,
                60,
                "Moyen"
        ));

        entreprises.add(new Entreprise(
                "AI Innovations",
                "Leader dans le domaine de l'intelligence artificielle et du machine learning.",
                75,
                70,
                "Difficile"
        ));

        entreprises.add(new Entreprise(
                "CyberSec Elite",
                "Spécialistes de la cybersécurité pour les institutions financières.",
                85,
                75,
                "Difficile"
        ));

        entreprises.add(new Entreprise(
                "GAFAM Corp",
                "Le géant de la tech. Seuls les meilleurs peuvent y prétendre.",
                95,
                85,
                "Extrême"
        ));

        return entreprises;
    }
}