package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;

public class ModSounds {
    public static SoundEvent DOLL_SQUEAK;
    public static SoundEvent DOLL_CHICKEN;
    public static SoundEvent CARO_INVICTUS_MUSIC;
    public static SoundEvent EZ_DRONE_TS_SLOWED;
    public static SoundEvent EZ_DRONE_TS_SLOWED_GEIGER;
    public static SoundEvent EZ_DRONE_TS_SLOWED_VOICES;

    public static void register() {
        DOLL_SQUEAK = register("item.doll.squeak");
        DOLL_CHICKEN = register("item.doll.chicken");
        CARO_INVICTUS_MUSIC = register("mob.caro_invictus.music");
        EZ_DRONE_TS_SLOWED = register("music.drones.throat_singing_slowed"); //Sound Effect modified from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848
        EZ_DRONE_TS_SLOWED_GEIGER = register("music.drones.throat_singing_slowed_geiger"); //Sound Effect from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848 and  https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=62113
        EZ_DRONE_TS_SLOWED_VOICES = register("music.drones.throat_singing_slowed_whispers"); //Sound Effect from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848 and https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=94457
    }

    public static SoundEvent register(String id) {
        Identifier identifier = ExclusionZone.id(id);
        SoundEvent soundEvent = SoundEvent.of(identifier);
        Registry.register(Registries.SOUND_EVENT,identifier,soundEvent);
        return soundEvent;
    }

    public static float randPitch() {
        return (float)(0.75+Math.random()*(1.25-0.75));
    }
}
