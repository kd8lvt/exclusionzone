package com.kd8lvt.exclusionzone.datagen.recipe;

import com.kd8lvt.exclusionzone.registry.ModBlocks;
import com.kd8lvt.exclusionzone.registry.ModItems;
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

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class CraftingRecipeProvider extends FabricRecipeProvider {

    public CraftingRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        shapeless(exporter,RecipeCategory.MISC,new Item[]{ModItems.get("otherworldly_bone")},Items.BONE_MEAL);
        shaped(
            exporter,
            RecipeCategory.MISC,
            ModItems.get("magnet"),
            new HashMap<>(){{
                put('c',ModItems.get("mysterious_chunk"));
                put('e',Items.ENDER_PEARL);
                put('a',Items.AMETHYST_SHARD);
                put('s',Items.STICK);
            }},
            new String[]{
                "  s",
                " a ",
                "ce "
            }
        );
        shapeless(
            exporter,
            RecipeCategory.MISC,
            new Item[]{
                    ModItems.get("logging_axe_head"),
                    ModItems.get("reinforced_handle")
            },
            ModItems.get("logging_axe")
        );
        shaped(
            exporter,
            RecipeCategory.MISC,
            ModItems.get("logging_axe_head"),
            new HashMap<>(){{
                put('A',Items.AMETHYST_BLOCK);
                put('e',Items.ENDER_PEARL);
                put('a',Items.AMETHYST_SHARD);
            }},
            new String[]{
                "Aaa",
                "Ae ",
                "   "
            }
        );
        shaped(
            exporter,
            RecipeCategory.MISC,
            ModItems.get("reinforced_handle"),
            new HashMap<>(){{
                put('i',Items.IRON_INGOT);
            }},
            new String[]{
                "i",
                "i"
            }
        );
        shaped(
            exporter,
            RecipeCategory.MISC,
            ModItems.get("persona_monosword"),
            new HashMap<>(){{
                put('g',ModItems.get("glasscutter"));
                put('e',Items.ENDER_EYE);
                put('a',Items.AMETHYST_SHARD);
            }},
            new String[]{
                "  a",
                "ea ",
                "ge "
            }
        );
        shaped(
            exporter,
            RecipeCategory.MISC,
            ModBlocks.get("mining_simulator").asItem(),
            new HashMap<>(){{ //Inputs Key
                put('p',Items.DIAMOND_PICKAXE);
                put('d', Blocks.DISPENSER.asItem());
                put('s',Blocks.COBBLESTONE.asItem());
                put('r',Items.REDSTONE);
            }},
            new String[]{ //Pattern
                "sps",
                "sds",
                "srs"
            }
        );
        shaped(
            exporter,
            RecipeCategory.MISC,
            ModBlocks.get("interaction_simulator").asItem(),
            new HashMap<>(){{ //Inputs Key
                put('p',Blocks.DROPPER.asItem());
                put('d', Blocks.ENDER_CHEST.asItem());
                put('s',Blocks.COBBLESTONE.asItem());
                put('r',Items.REDSTONE);
            }},
            new String[]{ //Pattern
                "sps",
                "sds",
                "srs"
            }
        );
        shapedTags( //Muffler Recipe
            exporter,
            RecipeCategory.MISC,
            ModBlocks.get("muffler").asItem(),
            new HashMap<>(){{ //Input Key
                put('w',ItemTags.WOOL);
                put('p',ItemTags.PLANKS);
            }},
            new String[]{ //Pattern
                "www",
                "wpw",
                "www"
            }
        );
        shaped(
            exporter,
            RecipeCategory.TOOLS,
            ModItems.get("glasscutter"),
            new HashMap<>(){{
                put('s',Items.STICK);
                put('a',Items.AMETHYST_SHARD);
            }},
            new String[]{
                "  a",
                " s ",
                "s  "
            }
        );
        shaped(
            exporter,
            RecipeCategory.COMBAT,
            ModItems.get("omen_of_caro_invictus"),
            new HashMap<>(){{
                put('s',Items.NETHER_STAR);
                put('l',Items.LAPIS_LAZULI);
                put('c',ModItems.get("mysterious_chunk"));
                put('e',Items.ENDER_EYE);
            }},
            new String[]{
                " s ",
                "lcl",
                " e "
            }
        );
        shapeless(exporter,RecipeCategory.MISC,new Item[]{ModItems.get("glasscutter"),Items.LEATHER},ModItems.get("leather_scraps"),4);
        shaped(
                exporter,
                RecipeCategory.MISC,
                Items.BUNDLE,
                new HashMap<>(){{
                    put('l',ModItems.get("leather_scraps"));
                    put('s',Items.STRING);
                }},
                new String[]{
                        "sls",
                        "l l",
                        "lll"
                }
        );
        shapeless(
            exporter,
            RecipeCategory.MISC,
            new Item[]{
                Items.GHAST_TEAR,
                Items.ECHO_SHARD,
                Items.CRYING_OBSIDIAN
            },
            ModItems.get("void_tear")
        );
    }

    @SuppressWarnings("SameParameterValue")
    private void shapeless(RecipeExporter exporter, RecipeCategory category, Item[] inputs, Item output, int count) {
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
    private void shapeless(RecipeExporter exporter, RecipeCategory category, Item[] inputs, Item output) {
        shapeless(exporter, category, inputs, output,1);
    }

    private void shaped(RecipeExporter exporter, RecipeCategory category, Item output, HashMap<Character,Item> inputs, String[] patternStrs) {
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
