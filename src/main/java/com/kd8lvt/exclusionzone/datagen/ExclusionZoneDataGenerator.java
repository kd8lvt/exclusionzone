package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.datagen.lang.LangProviders;
import com.kd8lvt.exclusionzone.datagen.loot.LootProviders;
import com.kd8lvt.exclusionzone.datagen.recipe.RecipeProviders;
import com.kd8lvt.exclusionzone.datagen.tag.TagProviders;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ExclusionZoneDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        //Languages
        LangProviders.register(pack);
        //Tags
        TagProviders.register(pack);
        //Recipes
        pack.addProvider(RecipeProviders::new);
        //Models
        pack.addProvider(ModelProvider::new);
        //Loot Tables
        LootProviders.register(pack);
        //Enchantments (Broken w/ vanilla clients, disabled for now)
        //pack.addProvider(EnchantmentGenerator::new);
    }
}
