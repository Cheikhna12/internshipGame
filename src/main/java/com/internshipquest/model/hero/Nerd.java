package com.internshipquest.model.hero;
import com.badlogic.gdx.graphics.Texture;


public class Nerd extends AHero{
     
    
    
    public Nerd(){
      
        super("Elmüt",15, 5, 15, 35, 30, 50, 55, new Texture("assets/Nerd.png"));
        this.heroDescription= "Actually, I am the smartest (...and the weakest).";
    }




    




    


    
    public String getName() {return name;}

}