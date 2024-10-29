package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.datagen.lang.EnglishLangProvider;
import com.kd8lvt.exclusionzone.datagen.loot.ArchaeologyLootProvider;
import com.kd8lvt.exclusionzone.datagen.loot.BlockLootProvider;
import com.kd8lvt.exclusionzone.datagen.loot.EntityLootProvider;
import com.kd8lvt.exclusionzone.datagen.recipe.CraftingRecipeProvider;
import com.kd8lvt.exclusionzone.datagen.tag.ItemTagProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ExclusionZoneDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        //Languages
        pack.addProvider(EnglishLangProvider::new);
        //Tags
        pack.addProvider(ItemTagProvider::new);
        //Recipes
        pack.addProvider(CraftingRecipeProvider::new);
        //Models
        pack.addProvider(ModelProvider::new);
        //Loot Tables
        pack.addProvider(ArchaeologyLootProvider::new);
        pack.addProvider(BlockLootProvider::new);
        pack.addProvider(EntityLootProvider::new);
    }
}
