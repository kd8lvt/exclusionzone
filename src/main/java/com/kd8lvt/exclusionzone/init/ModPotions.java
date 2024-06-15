package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.StatusEffects.KillFocusStatusEffect;
import com.kd8lvt.exclusionzone.init.StatusEffects.MilkStatusEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.recipe.BrewingRecipeRegistry;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModPotions {

    public static final StatusEffect MILK = new MilkStatusEffect();
    public static final StatusEffect KILL_FOCUS = new KillFocusStatusEffect();
    public static final Potion MILK_POTION = new Potion("Milk", new StatusEffectInstance(Registries.STATUS_EFFECT.getEntry(MILK)));

    public static void register() {
        //Status Effects
        register("milk",MILK);
        register("kill_focus",KILL_FOCUS);
        //Potions
        register("milk",MILK_POTION);
    }

    public static void register(String id, StatusEffect status) {
        Registry.register(Registries.STATUS_EFFECT, ExclusionZone.id(id),status);
    }
    public static void register(String id, Potion item) {
        BrewingRecipeRegistry.Builder.BUILD.register(builder -> builder.registerPotionRecipe(Potions.WATER,Items.MILK_BUCKET,Registries.POTION.getEntry(MILK_POTION)));
        Registry.register(Registries.POTION,ExclusionZone.id(id),item);
    }
}
