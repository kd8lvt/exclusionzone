package com.kd8lvt.exclusionzone.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ExclusionZoneDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(ExclusionZoneEnglishProvider::new);
        pack.addProvider(ExclusionZoneRecipeGenerator::new);
        pack.addProvider(ExclusionZoneModelGenerator::new);
    }
}
