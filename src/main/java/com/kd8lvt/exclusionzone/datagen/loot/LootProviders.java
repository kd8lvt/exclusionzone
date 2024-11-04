package com.kd8lvt.exclusionzone.datagen.loot;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class LootProviders {
    public static void register(FabricDataGenerator.Pack pack) {
        pack.addProvider(ArchaeologyLootProvider::new);
        pack.addProvider(BlockLootProvider::new);
        pack.addProvider(EntityLootProvider::new);
    }
}
