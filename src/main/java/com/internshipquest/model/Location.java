package com.internshipquest.model;

public class Location {
    
    private final String name;
    private final String icon;
    private final float x;
    private final float y;
    private final float width;
    private final float height;
    private final float[] color;

    public Location(String name, String icon, float x, float y, float width, float height, float[] color) {
        this.name = name;
        this.icon = icon;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.color = color;
    }
    

    public boolean contains(float pointX, float pointY) {
        return pointX >= x && pointX <= x + width &&
               pointY >= y && pointY <= y + height;
    }
    
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
    
    public float getCenterX() {
        return x + width / 2;
    }
    
    public float getCenterY() {
        return y + height / 2;
    }
}
