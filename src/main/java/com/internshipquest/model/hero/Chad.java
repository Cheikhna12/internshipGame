package com.internshipquest.model.hero;

import com.badlogic.gdx.graphics.Texture;


public class Chad extends AHero{
     // constructor
    // template : public Hero(name, int endurance,  int social, int luck,
    // int skills, int stress, int money, int energy, Texture texture)
    public Chad(){
      
        super("Giovanni",35, 15, 15, 5, 5, 30, 70, new Texture("assets/Chad.png"));
        this.heroDescription= "A mountain of muscle to 'hit' the code";
    }




    




    


    
    public String getName() {return name;}

}