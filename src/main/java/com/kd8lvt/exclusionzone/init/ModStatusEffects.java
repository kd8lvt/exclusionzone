package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.init.registries.ModStatusEffectRegistry;
import net.minecraft.entity.effect.StatusEffect;

@SuppressWarnings("unused")
public class ModStatusEffects {
    public static StatusEffect MILK;
    public static StatusEffect KILL_FOCUS;

    public static void ready() {
        MILK = ModStatusEffectRegistry.MILK.value();
        KILL_FOCUS = ModStatusEffectRegistry.KILL_FOCUS.value();
    }
}
