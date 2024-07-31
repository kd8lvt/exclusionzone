package com.kd8lvt.exclusionzone.init.StatusEffects;

import com.kd8lvt.exclusionzone.init.registries.ModStatusEffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;
import java.util.Objects;

public class MilkStatusEffect extends StatusEffect {

    public MilkStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL, 0xffffff);
    }
    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {return true;}

    @Override
    public boolean applyUpdateEffect(LivingEntity target, int amplifier) {
        Map<RegistryEntry<StatusEffect>, StatusEffectInstance> effects = target.getActiveStatusEffects();
        effects.forEach((effect,instance)->{
            if (effect.value().isBeneficial()) return;
            if (amplifier < instance.getAmplifier()) return;
            if (amplifier >= instance.getAmplifier()) {
                target.setStatusEffect(new StatusEffectInstance(effect,instance.getDuration()-(((amplifier-instance.getAmplifier())+1)*5),instance.getAmplifier()),target);
                lowerTime(target,((((instance.getAmplifier())+1)*2)-(amplifier+1))*2);
                return;
            }
            if (amplifier+1 < instance.getAmplifier()) {
                target.setStatusEffect(new StatusEffectInstance(effect,instance.getDuration(),amplifier+1),target);
                lowerTime(target,60*(((instance.getAmplifier())-(amplifier))+1));
            }
        });
        return super.applyUpdateEffect(target,amplifier);
    }

    private static void lowerTime(LivingEntity target,int amount) {
        StatusEffectInstance self = Objects.requireNonNull(target.getStatusEffect(ModStatusEffectRegistry.MILK));
        target.setStatusEffect(new StatusEffectInstance(ModStatusEffectRegistry.MILK,self.getDuration()-amount,self.getAmplifier(),self.isAmbient(),self.shouldShowParticles(),self.shouldShowIcon()),target);
    }
}
