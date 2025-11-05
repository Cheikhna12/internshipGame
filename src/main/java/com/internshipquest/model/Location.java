package com.internshipquest.model;

public class Location {

    private final String name;
    private final String icon;
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final float[] color;

    // Constructeur principal (tout défini)
    public Location(String name, String icon, float x, float y, float width, float height, float[] color) {
        this.name = name;
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }

    // Constructeur simplifié : largeur/hauteur par défaut, couleur blanche
    public Location(String name, String icon, float x, float y) {
        this(name, icon, x, y, 50, 50, new float[]{1f, 1f, 1f}); // largeur=50, hauteur=50, couleur blanche
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float[] getColor() {
        return color;
    }

    // Position du centre du lieu
    public float getCenterX() {
        return x + width / 2;
    }

    public float getCenterY() {
        return y + height / 2;
    }

    // Vérifie si un point est dans le lieu
    public boolean contains(float pointX, float pointY) {
        return pointX >= x && pointX <= x + width &&
                pointY >= y && pointY <= y + height;
    }
}
