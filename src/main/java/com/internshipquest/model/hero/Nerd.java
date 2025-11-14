package com.internshipquest.model.hero;
import com.badlogic.gdx.graphics.Texture;


public class Nerd extends AHero{
     // constructor
    // template : public Hero(name, int endurance,  int social, int luck,
    // int skills, int stress, int money, int energy, Texture texture)
    public Nerd(){
      
        super("Elmüt",15, 5, 15, 35, 5, 50, 30, new Texture("assets/Nerd.png"));
        this.heroDescription= "Actually, I am the smartest (...and the weakest).";
    }
    //methods


    //getter
    public String getName() {return name;}

}