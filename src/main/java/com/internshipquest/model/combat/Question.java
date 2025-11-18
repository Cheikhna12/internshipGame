package com.internshipquest.model.combat;

public class Question {
    private String text;
    private QuestionType type;
    private int difficulty; 
    private String optionTech;
    private String optionSoft;
    
    public enum QuestionType {
        TECH,           
        SOFTSKILL,      
        MIXED,          
        PIEGE           
    }
    
    public Question(String text, QuestionType type, int difficulty, String optionTech, String optionSoft) {
        this.text = text;
        this.type = type;
        this.difficulty = difficulty;
        this.optionTech = optionTech;
        this.optionSoft = optionSoft;
    }
    
    public String getText() {
        return text;
    }
    
    public QuestionType getType() {
        return type;
    }
    
    public int getDifficulty() {
        return difficulty;
    }
    
    public String getOptionTech() {
        return optionTech;
    }
    
    public String getOptionSoft() {
        return optionSoft;
    }
}
