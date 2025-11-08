package com.internshipquest.model;
import com.badlogic.gdx.graphics.Texture;


public class Nerd extends AHero{
     // constructor
    // template : public Hero(name, int endurance,  int social, int luck,
    // int skills, int motivation, int money, int energy, Texture texture)
    public Nerd(){
      
        super("zeldu",35, 20, 5, 0, 50, 500, 100, new Texture("assets/Nerd.png"));
    }




    




    //methods


    //getter
    public String getName() {return name;}

}