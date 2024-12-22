package com.kd8lvt.exclusionzone.datagen.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public final class LangProviders {
    public static void register(FabricDataGenerator.Pack pack) {
        pack.addProvider(EnglishLangProvider::new);
    }
}