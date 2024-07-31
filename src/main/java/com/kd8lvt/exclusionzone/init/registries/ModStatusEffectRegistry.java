package com.kd8lvt.exclusionzone.init.registries;

import com.kd8lvt.exclusionzone.init.RegistryUtil;
import com.kd8lvt.exclusionzone.init.StatusEffects.KillFocusStatusEffect;
import com.kd8lvt.exclusionzone.init.StatusEffects.MilkStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.entry.RegistryEntry;

public class ModStatusEffectRegistry {
    public static RegistryEntry<StatusEffect> MILK;
    public static RegistryEntry<StatusEffect> KILL_FOCUS;

    public static void register() {
        MILK = RegistryUtil.register("milk", new MilkStatusEffect());
        KILL_FOCUS = RegistryUtil.register("kill_focus", new KillFocusStatusEffect());
    }
}
