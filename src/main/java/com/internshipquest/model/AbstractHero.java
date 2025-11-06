package com.internshipquest.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

abstract public class AbstractHero {

    // attributes
    protected int endurance;
    protected int social;
    protected int luck;
    protected int skills;
    protected int motivation;
    protected int money;
    protected int energy;

    // position
    protected float x;
    protected float y;
    //texture
    protected Texture texture;

    // getters
    public int getEndurance() {return endurance;}
    public int getSocial() {return social;}
    public int getLuck() {return luck;}
    public int getSkills() {return skills;}
    public int getMotivation() {return motivation;}
    public int getMoney() {return money;}
    public int getEnergy() {return energy;}


    public float getX() {return x;}
    public float getY() {return y;}

    public Texture getTexture() {return texture;}

    // setters
    public void setEndurance(int endurance) {this.endurance = endurance;}
    public void setEnergy(int energy) {this.energy = energy;}
    public void setSocial(int social) {this.social = social;}
    public void setLuck(int luck) {this.luck = luck;}
    public void setSkills(int skills) {this.skills = skills;}
    public void setMotivation(int motivation) {this.motivation = motivation;}
    public void setMoney(int money) {this.money = money;}

    public void setX(float x) {this.x = x;}
    public void setY(float y) {this.y = y;}

    // constructor
    public AbstractHero(int endurance,  int social, int luck, int skills, int motivation, int money, int energy, Texture texture) {

        this.endurance = endurance;
        this.social = social;
        this.luck = luck;
        this.skills = skills;
        this.motivation = motivation;
        this.money = money;
        this.energy = energy;

        this.x = 100;
        this.y = 100;

        this.texture = texture;
    }

    //methods
    public void render(SpriteBatch batch){
        batch.draw(texture,x,y,40,40);
    }

    // public abstract int energyLeft(int endurance);
    // public abstract boolean hasEnoughTime(Activity activity, Day day);
    // public abstract int fatigue();
    // public abstract Object postuler(Entreprise entreprise);
    // public abstract void allerVers(Lieu lieu);


    public void dispose() {
        if (texture != null) texture.dispose();
    }

}