package com.internshipquest.utils;

public class Constants {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public static final String GAME_TITLE = "Sim Life - Simulation de Vie";
    
    public static final class Colors {
        public static final float[] BACKGROUND = {0.2f, 0.2f, 0.3f, 1f};

        public static final float[] MAISON = {0.95f, 0.65f, 0.25f, 1f};
        public static final float[] ECOLE = {0.3f, 0.7f, 0.95f, 1f};
        public static final float[] ENTREPRISE = {0.75f, 0.3f, 0.85f, 1f};
        public static final float[] CARREFOUR = {1f, 1f, 1f, 1f};
        public static final float[] FITNESSCLUB = {0.5f, 0.9f, 0.4f, 1f};
        
        public static final float[] TEXT = {1f, 1f, 1f, 1f};
        public static final float[] TEXT_HOVER = {1f, 1f, 0.3f, 1f};
    }
    
    public static final int LOCATION_WIDTH = 200;
    public static final int LOCATION_HEIGHT = 150;
    private Constants() {
    }
}
