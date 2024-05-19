package com.kd8lvt.exclusionzone.init.StatusEffects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

import java.util.UUID;

public class KillFocusStatusEffect extends StatusEffect {
    public KillFocusStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL,0x770000);
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, UUID.randomUUID().toString(),1.5d*amplifier+1.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, UUID.randomUUID().toString(),1.5d*amplifier+1.5, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL);
        this.addAttributeModifier(EntityAttributes.GENERIC_ATTACK_DAMAGE, UUID.randomUUID().toString(),amplifier+1, EntityAttributeModifier.Operation.ADD_VALUE);
        return super.applyUpdateEffect(entity, amplifier);
    }
}
