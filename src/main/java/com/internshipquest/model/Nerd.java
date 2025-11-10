package com.internshipquest.model;
import com.badlogic.gdx.graphics.Texture;


public class Nerd extends AHero{
     // constructor
    // template : public Hero(name, int endurance,  int social, int luck,
    // int skills, int motivation, int money, int energy, Texture texture)
    public Nerd(){
      
        super("zeldu",15, 5, 15, 35, 30, 50, 55, new Texture("assets/Nerd.png"));
        this.heroDescription= "Actually, I am the smartest (...and the weakest).";
    }




    




    //methods


    //getter
    public String getName() {return name;}

}