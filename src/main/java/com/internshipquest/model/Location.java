package com.internshipquest.model;

import com.internshipquest.model.location.*;

public class Location {

    private final String name;
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private ALieuVisitable lieu;

    // Constructeur principal (tout défini)
    public Location(String name, float x, float y, float width, float height) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void setLieu(ALieuVisitable lieu) {
        this.lieu = lieu;
    }

    public ALieuVisitable getLieu() {
        return lieu;
    }

    // Getters
    public String getName() {
        return name;
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
