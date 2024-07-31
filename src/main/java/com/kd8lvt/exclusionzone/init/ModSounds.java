package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.init.registries.ModSoundRegistry;
import net.minecraft.sound.SoundEvent;

@SuppressWarnings("unused")
public class ModSounds {
    public static SoundEvent DOLL_SQUEAK;
    public static SoundEvent DOLL_CHICKEN;
    public static SoundEvent CARO_INVICTUS_MUSIC;
    public static SoundEvent EZ_DRONE_TS_SLOWED;
    public static SoundEvent EZ_DRONE_TS_SLOWED_GEIGER;
    public static SoundEvent EZ_DRONE_TS_SLOWED_VOICES;
    public static float randPitch() {
        return (float)(0.75+Math.random()*(1.25-0.75));
    }

    public static void ready() {
        DOLL_SQUEAK = ModSoundRegistry.DOLL_SQUEAK.value();
        DOLL_CHICKEN = ModSoundRegistry.DOLL_SQUEAK.value();
        CARO_INVICTUS_MUSIC = ModSoundRegistry.CARO_INVICTUS_MUSIC.value();
        EZ_DRONE_TS_SLOWED = ModSoundRegistry.EZ_DRONE_TS_SLOWED_GEIGER.value();
        EZ_DRONE_TS_SLOWED_GEIGER = ModSoundRegistry.EZ_DRONE_TS_SLOWED_GEIGER.value();
        EZ_DRONE_TS_SLOWED_VOICES = ModSoundRegistry.EZ_DRONE_TS_SLOWED_GEIGER.value();
    }
}
