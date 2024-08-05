package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.ExclusionZone;
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
        ExclusionZone.LOGGER.info("Recipe: Otherworldly Bone->Bone Meal");
        shapeless(exporter,RecipeCategory.MISC,new Item[]{ModItems.OTHERWORLDLY_BONE},Items.BONE_MEAL);
        ExclusionZone.LOGGER.info("Recipe: Magnet");
        shaped(
            exporter,
            RecipeCategory.MISC,
            ModItems.MAGNET,
            new HashMap<>(){{
                put('c',ModItems.MYSTERIOUS_CHUNK);
                put('e',Items.ENDER_PEARL);
                put('a',Items.AMETHYST_SHARD);
                put('s',Items.STICK);
            }},
            new ArrayList<String>(){{
                add("  s");
                add(" a ");
                add("ce ");
            }}.toArray(new String[3]));
        ExclusionZone.LOGGER.info("Recipe: Persona Monosword");
        shaped(
                exporter,
                RecipeCategory.MISC,
                ModItems.PERSONA_MONOSWORD,
                new HashMap<>(){{
                    put('g',ModItems.GLASSCUTTER);
                    put('e',Items.ENDER_EYE);
                    put('a',Items.AMETHYST_SHARD);
                }},
                new ArrayList<String>(){{
                    add("  a");
                    add("ea ");
                    add("ge ");
                }}.toArray(new String[3]));
        ExclusionZone.LOGGER.info("Recipe: Block Breaker");
        shaped(
            exporter,
            RecipeCategory.MISC,
            ModBlocks.BLOCK_BREAKER.asItem(),
            new HashMap<>(){{ //Inputs Key
                put('p',Items.DIAMOND_PICKAXE);
                put('d', Blocks.DISPENSER.asItem());
                put('s',Blocks.COBBLESTONE.asItem());
                put('r',Items.REDSTONE);
            }},
            new ArrayList<String>(){{ //Pattern
                add("sps");
                add("sds");
                add("srs");
            }}.toArray(new String[3])
        );
        ExclusionZone.LOGGER.info("Recipe: Muffler");
        shapedTags( //Muffler Recipe
            exporter,
            RecipeCategory.MISC,
            ModBlocks.MUFFLER.asItem(),
            new HashMap<>(){{ //Input Key
                put('w',ItemTags.WOOL);
                put('p',ItemTags.PLANKS);
            }},
            new ArrayList<String>(){{ //Pattern
                add("www");
                add("wpw");
                add("www");
            }}.toArray(new String[3])
        );
        ExclusionZone.LOGGER.info("Recipe: Glasscutter");
        shaped(
            exporter,
            RecipeCategory.TOOLS,
            ModItems.GLASSCUTTER,
            new HashMap<>(){{
                put('s',Items.STICK);
                put('a',Items.AMETHYST_SHARD);
            }},
            new ArrayList<String>(){{
                add("  a");
                add(" s ");
                add("s  ");
            }}.toArray(new String[3])
        );
    }

    @SuppressWarnings("SameParameterValue")
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

    @SuppressWarnings("SameParameterValue")
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
