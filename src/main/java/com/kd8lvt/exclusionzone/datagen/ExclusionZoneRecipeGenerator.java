package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.kd8lvt.exclusionzone.init.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class ExclusionZoneRecipeGenerator extends FabricRecipeProvider {

    public ExclusionZoneRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        shapeless(exporter,RecipeCategory.MISC,new Item[]{ModItems.OTHERWORLDLY_BONE},Items.BONE_MEAL);
        shapeless(exporter,RecipeCategory.MISC,new Item[]{ModItems.HUNK_OF_AMBER,ModItems.HUNK_OF_AMBER,ModItems.HUNK_OF_AMBER,ModItems.HUNK_OF_AMBER}, ModBlocks.AMBER_BLOCK.asItem());

        HashMap<Character,Item> blockBreakerInputs = new HashMap<>();
        ArrayList<String> blockBreakerPatternStrs = new ArrayList<>();
        blockBreakerInputs.put('p',Items.DIAMOND_PICKAXE);
        blockBreakerInputs.put('d', Blocks.DISPENSER.asItem());
        blockBreakerInputs.put('s',Blocks.COBBLESTONE.asItem());
        blockBreakerInputs.put('r',Items.REDSTONE);
        blockBreakerPatternStrs.add("sps");
        blockBreakerPatternStrs.add("sds");
        blockBreakerPatternStrs.add("srs");
        shaped(exporter,RecipeCategory.MISC,ModBlocks.BLOCK_BREAKER.asItem(),blockBreakerInputs,blockBreakerPatternStrs.toArray(new String[3]));

        HashMap<Character,TagKey<Item>> mufflerInputs = new HashMap<>();
        ArrayList<String> mufflerPatternStrs = new ArrayList<>();
        mufflerInputs.put('w', ItemTags.WOOL);
        mufflerInputs.put('p', ItemTags.PLANKS);
        mufflerPatternStrs.add("www");
        mufflerPatternStrs.add("wpw");
        mufflerPatternStrs.add("www");
        shapedTags(exporter,RecipeCategory.MISC,ModBlocks.MUFFLER.asItem(),mufflerInputs,mufflerPatternStrs.toArray(new String[3]));
    }

    private void shapeless(RecipeExporter exporter, RecipeCategory category, Item[] inputs, Item output) {
        ShapelessRecipeJsonBuilder builder = ShapelessRecipeJsonBuilder.create(category,output)
                .criterion(FabricRecipeProvider.hasItem(output),FabricRecipeProvider.conditionsFromItem(output));
        for (Item item : inputs) {
            builder = builder.input(item)
                    .criterion(FabricRecipeProvider.hasItem(item),FabricRecipeProvider.conditionsFromItem(item));
        }
        builder.offerTo(exporter);
    }

    private void shaped(RecipeExporter exporter, RecipeCategory category, Item output, HashMap<Character,Item> inputs, String[] patternStrs) {
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(category, output)
                .criterion(FabricRecipeProvider.hasItem(output),FabricRecipeProvider.conditionsFromItem(output));
        for (Map.Entry<Character, Item> entry:inputs.entrySet()) {
            builder.input(entry.getKey(),entry.getValue());
        }
        for (String patternStr:patternStrs) builder.pattern(patternStr);
        builder.offerTo(exporter);
    }

    private void shapedTags(RecipeExporter exporter, RecipeCategory category, Item output, HashMap<Character, TagKey<Item>> inputs, String[] patternStrs) {
        ShapedRecipeJsonBuilder builder = ShapedRecipeJsonBuilder.create(category, output)
                .criterion(FabricRecipeProvider.hasItem(output),FabricRecipeProvider.conditionsFromItem(output));
        for (Map.Entry<Character, TagKey<Item>> entry:inputs.entrySet()) {
            builder.input(entry.getKey(),entry.getValue());
        }
        for (String patternStr:patternStrs) builder.pattern(patternStr);
        builder.offerTo(exporter);
    }
}
