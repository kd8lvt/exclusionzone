package com.kd8lvt.exclusionzone.content.item.PersonaWeapons.Traits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

public class PTraitPotionEffect extends PTrait {
    boolean doesTimeStack = false;
    boolean doesAmplifierStack = false;

    public PTraitPotionEffect() {
        super();
    }

    public StatusEffect getStatus() {return null;}

    public void applyStatus(LivingEntity entity, int duration, int amplifier) {
        StatusEffectInstance entityStatus = entity.getStatusEffect(RegistryEntry.of(getStatus()));
        if (entityStatus != null) {
            if (doesTimeStack) duration += entityStatus.getDuration();
            if (doesAmplifierStack) amplifier += entityStatus.getAmplifier();
        }
        entity.setStatusEffect(new StatusEffectInstance(RegistryEntry.of(getStatus()), duration, amplifier - 1),entity);
    }
}
