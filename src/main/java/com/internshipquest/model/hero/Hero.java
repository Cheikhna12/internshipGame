package com.internshipquest.model.hero;
import com.badlogic.gdx.graphics.Texture;


public class Hero extends AHero{
     
    
    
    public Hero(){
    
        super("zeldo",25, 15, 15, 15, 30, 60, 55, new Texture("assets/Hero.png"));
        this.heroDescription= "Mr. 'Average' : Basic Statistics";
    }




    




    


    
    public String getName() {return name;}

}