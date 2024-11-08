package com.kd8lvt.exclusionzone.datagen.recipe;

import com.kd8lvt.exclusionzone.api.datagen.recipe.AbstractExclusionZoneRecipeProvider;
import com.kd8lvt.exclusionzone.registry.ModBlocks;
import com.kd8lvt.exclusionzone.registry.ModItems;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.tag.ItemTags;

import java.util.HashMap;

public class ShapedRecipeProvider extends AbstractExclusionZoneRecipeProvider {
    public void generate(RecipeExporter exporter) {
        shaped(
                exporter,
                RecipeCategory.MISC,
                ModItems.get("magnet"),
                new HashMap<>(){{
                    put('c',ModItems.get("mysterious_chunk"));
                    put('e', Items.ENDER_PEARL);
                    put('a',Items.AMETHYST_SHARD);
                    put('s',Items.STICK);
                }},
                new String[]{
                        "  s",
                        " a ",
                        "ce "
                }
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
                    put('w', ItemTags.WOOL);
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
        shaped(
            exporter,
            RecipeCategory.TOOLS,
            ModItems.get("guster_jar"),
            new HashMap<>(){{
                put('t',Items.WHITE_TERRACOTTA);
                put('p',Items.DECORATED_POT);
                put('w',Items.WIND_CHARGE);
            }},
            new String[]{
                "t t",
                "twt",
                "tpt"
            }
        );
    }
}
