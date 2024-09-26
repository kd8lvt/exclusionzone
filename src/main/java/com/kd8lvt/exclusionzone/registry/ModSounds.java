package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

public class ModSounds {
    static void register() {
        register("item.doll.squeak");
        register("item.doll.chicken");
        register("mob.caro_invictus.music");
        register("music.drones.throat_singing_slowed"); //Sound Effect modified from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848
        register("music.drones.throat_singing_slowed_geiger"); //Sound Effect from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848 and  https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=62113
        register("music.drones.throat_singing_slowed_whispers"); //Sound Effect from https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=86848 and https://pixabay.com/sound-effects/?utm_source=link-attribution&utm_medium=referral&utm_campaign=music&utm_content=94457
    }

    private static void register(String id) {
        ModRegistries.SOUNDS.register(id, SoundEvent.of(ExclusionZone.id(id)));
    }


    public static RegistryEntry<SoundEvent> getEntry(String id) {return ModRegistries.SOUNDS.get(id);}
    public static SoundEvent get(String id) {return getEntry(id).value();}
}
