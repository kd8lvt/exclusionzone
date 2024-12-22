package com.kd8lvt.exclusionzone.datagen.recipe;

import com.kd8lvt.exclusionzone.api.datagen.recipe.AbstractExclusionZoneRecipeProvider;
import com.kd8lvt.exclusionzone.registry.ModItems;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;

public class ShapelessRecipeProvider extends AbstractExclusionZoneRecipeProvider {
    public void generate(RecipeExporter exporter) {
        shapeless(exporter,RecipeCategory.MISC,new Item[]{ModItems.get("otherworldly_bone")},Items.BONE_MEAL);
        shapeless(
            exporter,
            RecipeCategory.MISC,
            new Item[]{
                    ModItems.get("logging_axe_head"),
                    ModItems.get("reinforced_handle")
            },
            ModItems.get("logging_axe")
        );
        shapeless(exporter,RecipeCategory.MISC,new Item[]{ModItems.get("glasscutter"),Items.LEATHER},ModItems.get("leather_scraps"),4);
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
        Item mini_coal = ModItems.get("mini_coal");
        Item mini_charcoal = ModItems.get("mini_charcoal");
        shapeless(exporter,RecipeCategory.MISC,new Item[]{Items.COAL},mini_coal,8);
        shapeless(exporter,RecipeCategory.MISC,new Item[]{Items.CHARCOAL},mini_charcoal,8);
        shapeless(exporter,RecipeCategory.MISC,new Item[]{mini_coal,mini_coal,mini_coal,mini_coal,mini_coal,mini_coal,mini_coal,mini_coal},Items.COAL);
        shapeless(exporter,RecipeCategory.MISC,new Item[]{mini_charcoal,mini_charcoal,mini_charcoal,mini_charcoal,mini_charcoal,mini_charcoal,mini_charcoal,mini_charcoal},Items.CHARCOAL);
    }
}
