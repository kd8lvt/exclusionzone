package com.kd8lvt.exclusionzone.init.status_effects;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class KillFocusStatusEffect extends StatusEffect {
    public KillFocusStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL,0x770000);

        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, ExclusionZone.id("killfocused_attack_speed"),0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, ExclusionZone.id("killfocused_movement_speed"),0.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, ExclusionZone.id("killfocused_damage"),1, EntityAttributeModifier.Operation.ADD_VALUE);
    }
}
