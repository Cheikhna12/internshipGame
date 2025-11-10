package com.internshipquest.model;
import com.badlogic.gdx.graphics.Texture;


public class Hero extends AHero{
     // constructor
    // template : public Hero(name, int endurance,  int social, int luck,
    // int skills, int motivation, int money, int energy, Texture texture)
    public Hero(){
      
        super("zeldo",35, 20, 5, 0, 50, 50, 100, new Texture("assets/Hero.png"));
    }




    




    //methods


    //getter
    public String getName() {return name;}

}