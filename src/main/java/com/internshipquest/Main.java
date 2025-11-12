package com.internshipquest;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.internshipquest.utils.Constants;

public class Main {
    
    public static void main(String[] args) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        
        config.setTitle(Constants.GAME_TITLE);
        config.setWindowedMode(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT);
        config.setResizable(false);
        config.setForegroundFPS(60);

        new Lwjgl3Application(new InternshipQuestGame(), config);
    }
}

