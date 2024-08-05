package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.init.Blocks.Enderweed;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.kd8lvt.exclusionzone.init.ModItems;
import com.kd8lvt.exclusionzone.init.registries.ModBlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.Map;

public class ExclusionZoneModelGenerator extends FabricModelProvider {

    ItemModelGenerator itemGen;
    public ExclusionZoneModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ModBlocks.ENDERWEED, Enderweed.AGE,1, 1, 2, 2, 4, 5, 5, 6);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.AMBER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.MUFFLER);
        blockStateModelGenerator.registerBrushableBlock(ModBlocks.SUS_MOSS);
        //Why did I not do this initially lol
        for (Map.Entry<String, RegistryEntry<Block>> entry : ModBlockRegistry.SUS_CONCRETE_POWDERS.entrySet()) {
            blockStateModelGenerator.registerBrushableBlock(entry.getValue().value());
        }
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemGen = itemModelGenerator;
        generated(ModItems.BOY_DOLL);
        generated(ModItems.GIRL_DOLL);
        generated(ModItems.OTHERWORLDLY_BONE);
        generated(ModItems.CHIPPED_CARAPACE);
        generated(ModItems.ENORMOUS_TARDIGRADE);
        generated(ModItems.HUNK_OF_AMBER);
        generated(ModItems.MOSS_SAMPLE);
        generated(ModItems.MYSTERIOUS_CHUNK);
        generated(ModItems.QUICKMETAL);
        generated(ModItems.SCRAP_METAL);
        generated(ModItems.VILLAGER_DOLL);
        generated(ModItems.WARPED_MEAT);
        generated(ModItems.INFINITE_STEAK);
        generated(ModItems.CARO_INVICTUS_SPAWNER);
        handheldRod(ModItems.GLASSCUTTER);
        handheldRod(ModItems.PERSONA_MONOSWORD);
        generated(ModItems.MAGNET);
    }

    public void generated(Item item) {
        itemGen.register(item, Models.GENERATED);
    }

    public void handheldRod(Item item) {
        itemGen.register(item,Models.HANDHELD_ROD);
    }
}