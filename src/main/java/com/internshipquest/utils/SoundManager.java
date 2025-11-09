package com.internshipquest.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundManager {

    // 🌟 Musiques d'ambiance par zone
    private static HashMap<String, Music> musics = new HashMap<>();
    private static String currentMusicKey = null;

    // 🌟 Effets courts
    private static HashMap<String, Sound> sounds = new HashMap<>();


    public static void loadSounds() {
        //  Ambiances ! les son en .wav doit être en 16bit pour libGBX
        musics.put("gym", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/gym-ambience.wav")));
        musics.put("house", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/house.mp3")));

        //  Effets courts
        sounds.put("pushup", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/essouflé.wav")));
        sounds.put("deadlift", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/essouflé.wav")));
        sounds.put("machine_nourriture", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/MachineANourriture.wav")));
    }


    // Jouer une ambiance
    public static void playMusic(String key, boolean looping, float volume) {
        if (!musics.containsKey(key)) return;

        if (currentMusicKey != null && musics.containsKey(currentMusicKey)) {
            musics.get(currentMusicKey).stop();
        }

        Music music = musics.get(key);
        music.setLooping(looping);
        music.setVolume(volume);
        music.play();
        currentMusicKey = key;
    }

    public static void stopMusic() {
        if (currentMusicKey != null && musics.containsKey(currentMusicKey)) {
            musics.get(currentMusicKey).stop();
            currentMusicKey = null;
        }
    }


    // Jouer un effet court
    public static void playSound(String key, float volume) {
        if (!sounds.containsKey(key)) return;
        sounds.get(key).play(volume);
    }

    public static void dispose() {
        for (Music m : musics.values()) m.dispose();
        for (Sound s : sounds.values()) s.dispose();
        musics.clear();
        sounds.clear();
        currentMusicKey = null;
    }
}
