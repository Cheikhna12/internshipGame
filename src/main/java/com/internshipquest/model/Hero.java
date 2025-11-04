package com.internshipquest.model;

public class Hero {

    // attributes
    private int endurance;
    private int energy;

    // getters
    public int getEndurance() {
        return endurance;
    }

    public int getEnergy() {
        return energy;
    }

    // setters
    public void setEndurance(int endurance) {
        this.endurance = endurance;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    // constructor
    public Hero(){
        this.endurance = 100;
        this.energy = 100;
    }
}