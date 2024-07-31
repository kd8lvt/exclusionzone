package com.kd8lvt.exclusionzone.init.registries;

import com.kd8lvt.exclusionzone.init.*;

import static com.kd8lvt.exclusionzone.ExclusionZone.LOGGER;

public class ModRegistry {
    public static void registerAll() {
        LOGGER.info("[ExclusionZone] Registering Sounds...");
        ModSoundRegistry.register();
        ModSounds.ready();
        LOGGER.info("[ExclusionZone] Registering Blocks...");
        ModBlockRegistry.register();
        ModBlocks.ready();
        LOGGER.info("[ExclusionZone] Registering Items...");
        ModItemRegistry.register();
        ModItems.ready();
        LOGGER.info("[ExclusionZone] Registering Status Effects...");
        ModStatusEffectRegistry.register();
        ModStatusEffects.ready();
        LOGGER.info("[ExclusionZone] Registering Potions...");
        ModPotionRegistry.register();
        ModPotions.ready();
        LOGGER.info("[ExclusionZone] Registering Entities...");
        ModEntityRegistry.register();
        ModEntities.ready();
        LOGGER.info("[ExclusionZone] Initialization complete!");
    }
}

