package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.init.registries.ModPotionRegistry;
import net.minecraft.potion.Potion;

public class ModPotions {
    public static Potion MILK_POTION;
    public static Potion STRONG_MILK_POTION;
    public static Potion VERY_STRONG_MILK_POTION;
    public static Potion LONG_MILK_POTION;
    public static Potion LONG_STRONG_MILK_POTION;

    public static void ready() {
        MILK_POTION = ModPotionRegistry.MILK_POTION.value();
        STRONG_MILK_POTION = ModPotionRegistry.STRONG_MILK_POTION.value();
        VERY_STRONG_MILK_POTION = ModPotionRegistry.VERY_STRONG_MILK_POTION.value();
        LONG_MILK_POTION = ModPotionRegistry.LONG_MILK_POTION.value();
        LONG_STRONG_MILK_POTION = ModPotionRegistry.LONG_STRONG_MILK_POTION.value();
    }
}
