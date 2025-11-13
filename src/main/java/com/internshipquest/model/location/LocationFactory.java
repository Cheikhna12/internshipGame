package com.internshipquest.model.location;

import com.internshipquest.InternshipQuestGame;
import com.internshipquest.model.location.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

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
            case "Your House":
                lieu = new Maison(game);
                break;
            case "FitnessClub":
                lieu = new FitnessClub(game);
                break;
            case "Clover Field":
                lieu = new CloverField(game);
                break;
            case "Epitech":
                lieu = new Epitech(game);
                break;
            case "Bar":
                lieu = new Bar(game);
                break;
            case "Sorcerer":
                lieu = new Sorcerer(game);
                break;
            case "Shop":
                lieu = new Shop(game);
                break;
            default:
                break; // Non visitable = null
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
        locations.add(createLocation("Shop", 96, 352, 224, 224));
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

    public static Texture createBackground(String locationName) {
        switch (locationName) {
            case "FitnessClub":
                return new Texture(Gdx.files.internal("assets/images/gym_background.png"));
            case "Your House":
                return new Texture(Gdx.files.internal("assets/images/maison_background.png"));
            case "Clover Field":
                return new Texture(Gdx.files.internal("assets/images/clover_field_background.png"));
            case "Epitech":
                return new Texture(Gdx.files.internal("assets/images/epitech_background.png"));
            case "Bar":
                return new Texture(Gdx.files.internal("assets/images/bar_background.png"));
            case "Shop":
                return new Texture(Gdx.files.internal("assets/images/store_background.png"));
            case "Sorcerer":
                return new Texture(Gdx.files.internal("assets/images/sorcerer_background.png"));
            default:
                return new Texture(Gdx.files.internal("assets/images/default_background.png"));
        }
    }
}


