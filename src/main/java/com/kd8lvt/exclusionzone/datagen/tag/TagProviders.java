package com.kd8lvt.exclusionzone.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class TagProviders {
    public static void register(FabricDataGenerator.Pack pack) {
        pack.addProvider(ItemTagProvider::new);
        pack.addProvider(ArmorTrimTagProvider::new);
    }
}
