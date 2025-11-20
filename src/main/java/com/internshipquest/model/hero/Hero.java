package com.internshipquest.model.hero;
import com.badlogic.gdx.graphics.Texture;


public class Hero extends AHero{
     
    
    
    public Hero(){
    //endurance,social,luck, Codingskills, motivation, money, energy
        //super("zeldo",25, 15, 15, 15, 5, 60, 50, new Texture("assets/Hero.png"));
        super("zeldo",100, 100, 50, 50, 5, 60, 50, new Texture("assets/Hero.png"));
        this.heroDescription= "Mr. 'Average' : Basic Statistics";
    }




    




    


    
    public String getName() {return name;}

}