package com.kd8lvt.exclusionzone.datagen.damagetype;

import com.kd8lvt.exclusionzone.registry.ModDamageTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.entity.damage.DamageEffects;
import net.minecraft.entity.damage.DamageScaling;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.entity.damage.DeathMessageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

import static com.kd8lvt.exclusionzone.api.CommonConstants.MOD_ID;


public class DamageTypeGenerator extends FabricDynamicRegistryProvider {

    public DamageTypeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        register(
                entries,
                ModDamageTypes.KEYS.TOXIC_ENVIRONMENT,
                createDamageType(
                        ModDamageTypes.KEYS.TOXIC_ENVIRONMENT,
                        0.1f,
                        DamageEffects.HURT
                )
        );
    }

    public DamageType createDamageType(RegistryKey<DamageType> key, float exhaustion,DamageEffects effects) {
        return new DamageType(key.getValue().toTranslationKey(), DamageScaling.NEVER, exhaustion, effects, DeathMessageType.DEFAULT);
    }

    private static void register(Entries entries, RegistryKey<DamageType> key, DamageType damageType, ResourceCondition... conditions) {
        entries.add(key,damageType,conditions);
    }


    @Override
    public String getName() {
        return MOD_ID+"/DamageTypes";
    }
}
