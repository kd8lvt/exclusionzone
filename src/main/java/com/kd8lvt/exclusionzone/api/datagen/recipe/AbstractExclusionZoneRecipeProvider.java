package com.kd8lvt.exclusionzone.api.datagen.recipe;

import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.TagKey;

import java.util.HashMap;
import java.util.Map;

import static com.kd8lvt.exclusionzone.ExclusionZone.LOGGER;

public abstract class AbstractExclusionZoneRecipeProvider {
    public AbstractExclusionZoneRecipeProvider() {}

    @SuppressWarnings("SameParameterValue")
    public void shapeless(RecipeExporter exporter, RecipeCategory category, Item[] inputs, Item output, int count) {
        try {
            LOGGER.info("Shaped Recipe: %s".formatted(output.getTranslationKey()));
        } catch (Exception e) {
            LOGGER.info("Failed to get shapeless recipe output's translation key, but that's ok :)");
        }
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder.create(category,output,count)
                .criterion(FabricRecipeProvider.hasItem(output),FabricRecipeProvider.conditionsFromItem(output));
        for (Item item : inputs) {
            builder = builder.input(item)
                    .criterion(FabricRecipeProvider.hasItem(item),FabricRecipeProvider.conditionsFromItem(item));
        }
        builder.offerTo(exporter);
    }

    @SuppressWarnings("SameParameterValue")
    public void shapeless(RecipeExporter exporter, RecipeCategory category, Item[] inputs, Item output) {
        shapeless(exporter, category, inputs, output,1);
    }

    public void shaped(RecipeExporter exporter, RecipeCategory category, Item output, HashMap<Character, Item> inputs, String[] patternStrs) {
        try {
            LOGGER.info("Shaped Recipe: %s".formatted(output.getTranslationKey()));
        } catch (Exception e) {
            LOGGER.info("Failed to get shaped recipe output's translation key, but that's ok :)");
        }
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(category, output)
                .criterion(FabricRecipeProvider.hasItem(output),FabricRecipeProvider.conditionsFromItem(output));
        for (Map.Entry<Character, Item> entry:inputs.entrySet()) {
            builder.input(entry.getKey(),entry.getValue());
        }
        for (String patternStr:patternStrs) builder.pattern(patternStr);
        builder.offerTo(exporter);
    }

    @SuppressWarnings("SameParameterValue")
    public void shapedTags(RecipeExporter exporter, RecipeCategory category, Item output, HashMap<Character, TagKey<Item>> inputs, String[] patternStrs) {
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(category, output)
                .criterion(FabricRecipeProvider.hasItem(output),FabricRecipeProvider.conditionsFromItem(output));
        for (Map.Entry<Character, TagKey<Item>> entry:inputs.entrySet()) {
            builder.input(entry.getKey(),entry.getValue());
        }
        for (String patternStr:patternStrs) builder.pattern(patternStr);
        builder.offerTo(exporter);
    }
}
