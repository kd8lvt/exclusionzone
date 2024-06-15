package com.kd8lvt.exclusionzone.init;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static final Identifier DOLL_SQUEAK = new Identifier("exclusionzone:item.doll.squeak");
    public static SoundEvent DOLL_SQUEAK_EVENT = SoundEvent.of(DOLL_SQUEAK);

    public static final Identifier DOLL_CHICKEN = new Identifier("exclusionzone:item.doll.chicken");
    public static SoundEvent DOLL_CHICKEN_EVENT = SoundEvent.of(DOLL_CHICKEN);

    public static final Identifier CARO_INVICTUS_MUSIC = new Identifier("exclusionzone:mob.caro_invictus.music");
    public static SoundEvent CARO_INVICTUS_MUSIC_EVENT = SoundEvent.of(CARO_INVICTUS_MUSIC);
    public static void register() {
        Registry.register(Registries.SOUND_EVENT,DOLL_SQUEAK,DOLL_SQUEAK_EVENT);
        Registry.register(Registries.SOUND_EVENT,DOLL_CHICKEN,DOLL_CHICKEN_EVENT);
        Registry.register(Registries.SOUND_EVENT,CARO_INVICTUS_MUSIC,CARO_INVICTUS_MUSIC_EVENT);
    }

    public static float randPitch() {
        return (float)(0.75+Math.random()*(1.25-0.75));
    }
}
