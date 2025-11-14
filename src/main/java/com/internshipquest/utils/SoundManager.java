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
        musics.put("bar", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/BarJazz.mp3")));
        musics.put("cloverField", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/FairyClover.mp3")));
        musics.put("epitech", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/EpitechMusic.mp3")));
        musics.put("shop", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/GroceryMusic.mp3")));
        musics.put("sorcerer", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/wizard.mp3")));
        musics.put("village", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/village.mp3")));
        musics.put("village-evening", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/village-evening.mp3")));
        musics.put("intro", Gdx.audio.newMusic(Gdx.files.internal("assets/sounds/intro.mp3")));

        sounds.put("pushup", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/essouflé.wav")));
        sounds.put("deadlift", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/essouflé.wav")));
        sounds.put("keybordTyping", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/keyboard_typing.mp3")));
        sounds.put("cloverDiscover", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/cloverDiscovery.wav")));
        sounds.put("cloverSearch", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/cloverSearch.wav")));
        sounds.put("SpellReverse", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/Spellreverse.wav")));
        sounds.put("SpellCough", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/spellToux.wav")));
        sounds.put("SpellWork", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/SpellWork.wav")));
        sounds.put("chess", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/chess-pieces.wav")));
        sounds.put("StoreWork", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/grocery_activity.wav")));
        sounds.put("GlassDrink", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/GlassDrink.wav")));
        sounds.put("GlassDrinkV", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/GlassDrinkV.wav")));
        sounds.put("snore", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/snore.wav")));
        sounds.put("waiting", Gdx.audio.newSound(Gdx.files.internal("assets/sounds/waiting.wav")));
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
