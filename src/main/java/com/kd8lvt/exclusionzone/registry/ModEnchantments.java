package com.kd8lvt.exclusionzone.registry;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.EnchantmentLocationBasedEffect;
import net.minecraft.enchantment.effect.EnchantmentValueEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static com.kd8lvt.exclusionzone.ExclusionZone.id;

public class ModEnchantments {
    public static class KEYS {
        public static final RegistryKey<Enchantment> TOXICUS_PERDITIO = RegistryKey.of(RegistryKeys.ENCHANTMENT, id("toxicus_perditio"));
        public static final RegistryKey<Enchantment> TOXICAE_PRAESIDIUM = RegistryKey.of(RegistryKeys.ENCHANTMENT, id("toxicae_praesidium"));
    }
    public static class CODECS {
        public static final MapCodec<AttributeEnchantmentEffect> TOXICUS_PERDITIO = AttributeEnchantmentEffect.CODEC;
        public static final MapCodec<AttributeEnchantmentEffect> TOXICAE_PRAESIDIUM = AttributeEnchantmentEffect.CODEC;
    }

    public static void register() {
        registerLocationEffect(KEYS.TOXICUS_PERDITIO,CODECS.TOXICUS_PERDITIO);
        registerLocationEffect(KEYS.TOXICAE_PRAESIDIUM,CODECS.TOXICAE_PRAESIDIUM);
    }

    private static <T extends EnchantmentEntityEffect> MapCodec<T> registerEntityEffect(Identifier id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE,id,codec);
    }

    private static <T extends EnchantmentValueEffect> MapCodec<T> registerValueEffect(Identifier id, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_VALUE_EFFECT_TYPE,id,codec);
    }

    private static <T extends EnchantmentLocationBasedEffect> MapCodec<T> registerLocationEffect(RegistryKey<Enchantment> key, MapCodec<T> codec) {
        return Registry.register(Registries.ENCHANTMENT_LOCATION_BASED_EFFECT_TYPE,key.getValue(),codec);
    }
}
