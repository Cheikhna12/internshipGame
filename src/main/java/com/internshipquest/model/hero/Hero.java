package com.internshipquest.model.hero;
import com.badlogic.gdx.graphics.Texture;


public class Hero extends AHero{
     // constructor
    // template : public Hero(name, int endurance,  int social, int luck,
    // int skills, int motivation, int money, int energy, Texture texture)
    public Hero(){
    //endurance,social,luck, Codingskills, motivation, money, energy
        super("zeldo",25, 15, 15, 15, 30, 60, 50, new Texture("assets/Hero.png"));
        this.heroDescription= "Mr. 'Average' : Basic Statistics";
    }




    




    //methods


    //getter
    public String getName() {return name;}

}