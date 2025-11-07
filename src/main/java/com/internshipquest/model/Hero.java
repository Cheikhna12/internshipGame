package com.internshipquest.model;


public class Hero extends AHero{
    //attribute
    private final String name;




    // constructor
    // template : public Hero(int endurance,  int social, int luck,
    // int skills, int motivation, int money, int energy, Texture texture)
    public Hero(){
      
        super(35, 20, 5, 0, 50, 500, 100);
        this.name = "Zeldo";
    }




    




    //methods


    //getter
    public String getName() {return name;}

}