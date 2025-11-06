package com.internshipquest.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Hero extends AbstractHero{

    //attribute
    private final String name;
    private final Texture texture = new Texture("assets/Hero.png");

    // constructor
    // template : public Hero(int endurance,  int social, int luck,
    // int skills, int motivation, int money, int energy, Texture texture)
    public Hero(){
        super(100, 20, 5, 0, 50, 500, 100, new Texture("assets/Hero.png"));
        this.name = "Zeldo";
    }

    //methods
    public void render(SpriteBatch batch){
        batch.draw(texture,x,y,40,40);
    }

    //getter
    public String getName() {return name;}
}