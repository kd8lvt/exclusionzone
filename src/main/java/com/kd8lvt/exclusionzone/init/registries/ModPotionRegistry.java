package com.kd8lvt.exclusionzone.init.registries;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.RegistryUtil;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

@SuppressWarnings("SameParameterValue")
public class ModPotionRegistry {
    public static RegistryEntry<Potion> MILK_POTION;
    public static RegistryEntry<Potion> STRONG_MILK_POTION;
    public static RegistryEntry<Potion> VERY_STRONG_MILK_POTION;
    public static RegistryEntry<Potion> LONG_MILK_POTION;
    public static RegistryEntry<Potion> LONG_STRONG_MILK_POTION;

    public static void register() {
        //There's probably a cleaner way to do this, but I don't really care tbh.
        MILK_POTION = registerPotionWithRecipe("milk", "milk", Potions.WATER, Items.MILK_BUCKET, new StatusEffectInstance(ModStatusEffectRegistry.MILK, 1200, 0, false, false, true));
        STRONG_MILK_POTION = strengthen("strong_milk", "milk", MILK_POTION, true);
        VERY_STRONG_MILK_POTION = strengthen("very_strong_milk", "milk", STRONG_MILK_POTION, true);
        LONG_MILK_POTION = lengthen("long_milk", "milk", 2f, MILK_POTION, true);
        LONG_STRONG_MILK_POTION = lengthen("long_strong_milk", "milk", 2f, STRONG_MILK_POTION, true);
        strengthen("long_strong_milk", "milk", LONG_MILK_POTION,true);
    }

    public static RegistryEntry<Potion> strengthen(String id, String baseName, RegistryEntry<Potion> base, boolean hideParticles) {
        if (hideParticles) return strengthen(id, baseName, base, false, false, true);
        return strengthen(id, baseName, base, false, false, true);
    }

    public static RegistryEntry<Potion> lengthen(String id, String baseName, float multiplier, RegistryEntry<Potion> base, boolean hideParticles) {
        if (hideParticles) return lengthen(id, baseName, multiplier, base, false, false, true);
        return lengthen(id, baseName, multiplier, base, false, false, true);
    }

    private static RegistryEntry<Potion> strengthen(String id, String baseName, RegistryEntry<Potion> base, boolean ambient, boolean showParticles, boolean showIcon) {
        StatusEffectInstance baseEffect = base.value().getEffects().getFirst();
        RegistryEntry<StatusEffect> baseStatus = baseEffect.getEffectType();
        int baseAmp = baseEffect.getAmplifier();
        int baseTime = baseEffect.getDuration();
        if (Registries.POTION.containsId(ExclusionZone.id(id))) {
            registerPotionRecipe(base,Items.GLOWSTONE_DUST,Registries.POTION.getEntry(ExclusionZone.id(id)).get());
            return Registries.POTION.getEntry(ExclusionZone.id(id)).get();
        }
        return registerPotionWithRecipe(id, baseName, base, Items.GLOWSTONE_DUST, new StatusEffectInstance(baseStatus, baseTime, baseAmp + 1, ambient, showParticles, showIcon));
    }

    private static RegistryEntry<Potion> lengthen(String id, String baseName, float multiplier, RegistryEntry<Potion> base, boolean ambient, boolean showParticles, boolean showIcon) {
        StatusEffectInstance baseEffect = base.value().getEffects().getFirst();
        RegistryEntry<StatusEffect> baseStatus = baseEffect.getEffectType();
        int baseAmp = baseEffect.getAmplifier();
        int baseTime = baseEffect.getDuration();
        if (Registries.POTION.containsId(ExclusionZone.id(id))) {
            registerPotionRecipe(base,Items.REDSTONE,Registries.POTION.getEntry(ExclusionZone.id(id)).get());
            return Registries.POTION.getEntry(ExclusionZone.id(id)).get();
        }
        return registerPotionWithRecipe(id, baseName, base, Items.REDSTONE, new StatusEffectInstance(baseStatus, (int) (baseTime * multiplier), baseAmp, ambient, showParticles, showIcon));
    }

    public static RegistryEntry<Potion> registerPotionWithRecipe(String id, String baseName, RegistryEntry<Potion> bottomInput, Item topInput, StatusEffectInstance effectInstance) {
        Potion result = new Potion(baseName, effectInstance);
        registerPotionRecipe(bottomInput, topInput, RegistryUtil.register(id, result));
        return Registries.POTION.getEntry(result);
    }

    public static void registerPotionRecipe(RegistryEntry<Potion> bottomInput, Item topInput, RegistryEntry<Potion> result) {
        BrewingRecipeRegistry.Builder.BUILD.register(builder -> builder.registerPotionRecipe(bottomInput, topInput, result));
    }
}
