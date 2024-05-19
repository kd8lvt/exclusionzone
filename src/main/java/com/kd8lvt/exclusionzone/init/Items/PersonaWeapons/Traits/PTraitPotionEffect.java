package com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

public class PTraitPotionEffect extends PTrait {
    boolean doesTimeStack = false;
    boolean doesAmplifierStack = false;
    StatusEffect status;

    public void applyStatus(LivingEntity entity, int duration, int amplifier) {
        RegistryEntry<StatusEffect> statusReg = RegistryEntry.of(this.status);
        StatusEffectInstance entityStatus = entity.getStatusEffect(statusReg);
        if (entityStatus != null) {
            if (doesTimeStack) duration += entityStatus.getDuration();
            if (doesAmplifierStack) amplifier += entityStatus.getAmplifier();
        }
        entity.addStatusEffect(new StatusEffectInstance(statusReg, duration, amplifier - 1));
    }
}
