package com.internshipquest.model.hero;

import com.badlogic.gdx.graphics.Texture;


public class Chad extends AHero{
     // constructor
    // template : public Hero(name, int endurance,  int social, int luck,
    // int skills, int motivation, int money, int energy, Texture texture)
    public Chad(){
      
        super("Giovanni",35, 15, 15, 5, 30, 30, 70, new Texture("assets/Chad.png"));
        this.heroDescription= "A mountain of muscle to 'hit' the code";
    }




    




    //methods


    //getter
    public String getName() {return name;}

}