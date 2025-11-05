package com.internshipquest.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero {

    // attributes
    private int endurance;
    private int energy;

    // position
    private float x;
    private float y;

    //texture
    private Texture texture;

    // getters
    public int getEndurance() {
        return endurance;
    }
    public int getEnergy() {
        return energy;
    }
    public float getX() {return x;}
    public float getY() {return y;}

    // setters
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }
    public void setEnergy(int energy) {
        this.energy = energy;
    }
    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}

    // constructor
    public Hero(){

        this.endurance = 100;
        this.energy = 100;

        this.x = 100;
        this.y = 100;

        this.texture = new Texture("assets/Hero.png");
    }

    //methods
    public void render(SpriteBatch batch){
        batch.draw(texture,x,y);
    }

}