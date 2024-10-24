package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.status_effects.KillFocusStatusEffect;
import com.kd8lvt.exclusionzone.status_effects.MilkStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public class ModStatusEffects {

    static void register() {
        ModRegistries.register("milk", new MilkStatusEffect());
        ModRegistries.register("kill_focus", new KillFocusStatusEffect());
    }

    public static RegistryEntry<StatusEffect> getEntry(String id) {return ModRegistries.STATUS_EFFECTS.get(id);}
    public static StatusEffect get(String id) {return getEntry(id).value();}
}
