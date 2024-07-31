package com.kd8lvt.exclusionzone.init.StatusEffects;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class KillFocusStatusEffect extends StatusEffect {
    public KillFocusStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL,0x770000);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, ExclusionZone.id("killfocused_attack_speed"),1.5d*amplifier+1.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, ExclusionZone.id("killfocused_moevement_speed"),1.5d*amplifier+1.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, ExclusionZone.id("killfocused_damage"),amplifier+1, EntityAttributeModifier.Operation.ADD_VALUE);
        return super.applyUpdateEffect(entity, amplifier);
    }
}
