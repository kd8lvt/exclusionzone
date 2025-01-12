package com.kd8lvt.exclusionzone.api;

import com.kd8lvt.exclusionzone.api.types.CachedValue;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.biome.Biome;

import static com.kd8lvt.exclusionzone.ExclusionZone.id;

public final class CommonConstants {
    public static final String MOD_ID = "exclusionzone";
    public static MinecraftServer SERVER;
    public static final CachedValue<RegistryEntry<Biome>> BIOME = new CachedValue<>(
            ()-> SERVER.getOverworld().getRegistryManager().get(RegistryKeys.BIOME).getEntry(id("exclusion_zone")).get()
    );
}