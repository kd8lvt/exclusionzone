package com.kd8lvt.exclusionzone.registry;

import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;

import static com.kd8lvt.exclusionzone.ExclusionZone.id;

public class ModDamageTypes {
    public static class KEYS {
        public static final RegistryKey<DamageType> TOXIC_ENVIRONMENT = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, id("toxic_environment"));
        public static final RegistryKey<DamageType> TOXIC_ENTITY = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, id("toxic_entity"));
    }
}
