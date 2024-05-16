package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.init.Blocks.Enderweed;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.kd8lvt.exclusionzone.init.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ExclusionZoneModelGenerator extends FabricModelProvider {

    public ExclusionZoneModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ModBlocks.ENDERWEED, Enderweed.AGE,1, 1, 2, 2, 4, 5, 5, 6);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AMBER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MUFFLER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_MOSS);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_WHITE_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_LIGHT_GRAY_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_GRAY_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_BLACK_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_BROWN_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_RED_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_ORANGE_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_YELLOW_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_LIME_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_GREEN_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_CYAN_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_LIGHT_BLUE_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_BLUE_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_PURPLE_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_MAGENTA_CONCRETE_POWDER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_PINK_CONCRETE_POWDER);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.BOY_DOLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.GIRL_DOLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.OTHERWORLDLY_BONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHIPPED_CARAPACE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENORMOUS_TARDIGRADE, Models.GENERATED);
        itemModelGenerator.register(ModItems.HUNK_OF_AMBER, Models.GENERATED);
        itemModelGenerator.register(ModItems.MOSS_SAMPLE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MYSTERIOUS_CHUNK, Models.GENERATED);
        itemModelGenerator.register(ModItems.QUICKMETAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.SCRAP_METAL, Models.GENERATED);
        itemModelGenerator.register(ModItems.VILLAGER_DOLL, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_MEAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.GLASSCUTTER, Models.HANDHELD_ROD);
    }
}