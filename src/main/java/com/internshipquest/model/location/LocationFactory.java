package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

public class LocationFactory {

    private final InternshipQuestGame game;
    private Map<String, ALieuVisitable> locationMap = new HashMap<>();

    public LocationFactory(InternshipQuestGame game) {
        this.game = game;
    }

    public ALieuVisitable getVisitableLocation(String name) {
        if (locationMap.containsKey(name)) return locationMap.get(name);

        ALieuVisitable lieu = null;
        switch (name) {
            case "Your House": lieu = new Maison(game); break;
            case "FitnessClub": lieu = new FitnessClub(game); break;
            case "Clover Field": lieu = new CloverField(game); break;
            case "Epitech": lieu = new Epitech(game); break;
            default: break; // Non visitable = null
        }

        if (lieu != null) locationMap.put(name, lieu);
        return lieu;
    }

    // 🔹 Nouvelle méthode pour créer toutes les locations
    public List<Location> createAllLocations() {
        List<Location> locations = new ArrayList<>();

        locations.add(createLocation("Industrial Zone", 64, 704, 448, 256));
        locations.add(createLocation("Clover Field", 554, 720, 160, 160));
        locations.add(createLocation("Bar", 768, 720, 224, 128));
        locations.add(createLocation("Store", 96, 352, 224, 224));
        locations.add(createLocation("FitnessClub", 640, 352, 256, 256));
        locations.add(createLocation("Sorcerer", 928, 352, 256, 256));
        locations.add(createLocation("Your House", 96, 128, 160, 160));
        locations.add(createLocation("Epitech", 992, 720, 256, 128));

        return locations;
    }

    private Location createLocation(String name, float x, float y, float width, float height) {
        Location loc = new Location(name, x, y, width, height);
        loc.setLieu(getVisitableLocation(name));
        return loc;
    }
}
