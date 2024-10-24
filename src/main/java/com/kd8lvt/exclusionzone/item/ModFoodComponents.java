package com.kd8lvt.exclusionzone.item;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;

public class ModFoodComponents {
    public static final FoodComponent WARPED_MEAT = new FoodComponent.Builder()
            .nutrition(1)
            .saturationModifier(0.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA,600,0,false,false,false),1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.POISON,600,4,false,false,false),1.0f)
            .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER,600,4,false,true,true),1.0f)
            .build();
}
