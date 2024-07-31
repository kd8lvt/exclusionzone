package com.kd8lvt.exclusionzone.init.registries;

import com.kd8lvt.exclusionzone.init.RegistryUtil;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

public class ModSoundRegistry {
    public static RegistryEntry<SoundEvent> DOLL_SQUEAK;
    public static RegistryEntry<SoundEvent> DOLL_CHICKEN;
    public static RegistryEntry<SoundEvent> CARO_INVICTUS_MUSIC;
    public static RegistryEntry<SoundEvent> EZ_DRONE_TS_SLOWED;
    public static RegistryEntry<SoundEvent> EZ_DRONE_TS_SLOWED_GEIGER;
    public static RegistryEntry<SoundEvent> EZ_DRONE_TS_SLOWED_VOICES;

    public static void register() {
        DOLL_SQUEAK = RegistryUtil.register("item.doll.squeak");
        DOLL_CHICKEN = RegistryUtil.register("item.doll.chicken");
        CARO_INVICTUS_MUSIC = RegistryUtil.register("mob.caro_invictus.music");
        EZ_DRONE_TS_SLOWED = RegistryUtil.register("music.drones.throat_singing_slowed"); //Sound Effect modified from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848
        EZ_DRONE_TS_SLOWED_GEIGER = RegistryUtil.register("music.drones.throat_singing_slowed_geiger"); //Sound Effect from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848 and  https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=62113
        EZ_DRONE_TS_SLOWED_VOICES = RegistryUtil.register("music.drones.throat_singing_slowed_whispers"); //Sound Effect from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848 and https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=94457
    }
}
