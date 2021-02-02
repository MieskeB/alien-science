package nl.michelbijnen.minecraft.alien.science.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import nl.michelbijnen.minecraft.alien.science.Constants;

public class AlienScienceSound {
    public static final SoundEvent MUSIC_MOON = new SoundEvent(new Identifier(Constants.MODID, Constants.Sound.Music.MOON));

    public static void register() {
        Registry.register(Registry.SOUND_EVENT, new Identifier(Constants.MODID, Constants.Sound.Music.MOON), MUSIC_MOON);
    }
}
