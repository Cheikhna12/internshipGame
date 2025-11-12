package com.internshipquest.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

import java.util.HashMap;

public class SoundManager {

    private static HashMap<String, Music> musics = new HashMap<>();
    private static HashMap<String, Sound> sounds = new HashMap<>();
    private static String currentMusicKey = null;

    private static float masterVolume = 1.0f;

    public static void loadSounds() {
        musics.put("gym", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/gym-ambience.wav")));
        musics.put("house", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/house.mp3")));

        sounds.put("pushup", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/essouflé.wav")));
        sounds.put("deadlift", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/essouflé.wav")));
        sounds.put("machine_nourriture", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/MachineANourriture.wav")));
    }

    public static void setMasterVolume(float volume) {
        masterVolume = Math.max(0f, Math.min(1f, volume));
        updateCurrentMusicVolume();
    }

    public static float getMasterVolume() {
        return masterVolume;
    }

    private static void updateCurrentMusicVolume() {
        if (currentMusicKey != null && musics.containsKey(currentMusicKey)) {
            musics.get(currentMusicKey).setVolume(masterVolume);
        }
    }

    public static void playMusic(String key, boolean looping, float volume) {
        if (!musics.containsKey(key)) return;

        if (currentMusicKey != null && musics.containsKey(currentMusicKey)) {
            musics.get(currentMusicKey).stop();
        }

        Music music = musics.get(key);
        music.setLooping(looping);
        music.setVolume(volume * masterVolume); // apply master volume
        music.play();
        currentMusicKey = key;
    }

    public static void stopMusic() {
        if (currentMusicKey != null && musics.containsKey(currentMusicKey)) {
            musics.get(currentMusicKey).stop();
            currentMusicKey = null;
        }
    }

    public static void playSound(String key, float volume) {
        if (!sounds.containsKey(key)) return;
        sounds.get(key).play(volume * masterVolume); // apply master volume
    }

    public static void dispose() {
        for (Music m : musics.values()) m.dispose();
        for (Sound s : sounds.values()) s.dispose();
        musics.clear();
        sounds.clear();
        currentMusicKey = null;
    }
}
