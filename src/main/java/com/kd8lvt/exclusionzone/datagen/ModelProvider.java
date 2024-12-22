package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.content.block.Enderweed.Enderweed;
import com.kd8lvt.exclusionzone.registry.ModBlocks;
import com.kd8lvt.exclusionzone.registry.ModItems;
import com.kd8lvt.exclusionzone.registry.ModRegistries;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;
import net.minecraft.registry.entry.RegistryEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModelProvider extends FabricModelProvider {

    ItemModelGenerator itemGen;
    public ModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerCrop(ModBlocks.get("plant/enderweed"), Enderweed.AGE,1, 1, 2, 2, 4, 5, 5, 6);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.get("amber_block"));
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.get("muffler"));

        blockStateModelGenerator.registerBrushableBlock(ModBlocks.get("archaeology/suspicious_moss"));
        //Why did I not do this initially lol
        for (String color : ModBlocks.VANILLA_COLORS) {
            blockStateModelGenerator.registerBrushableBlock(ModBlocks.get("archaeology/suspicious_"+color+"_concrete_powder"));
        }
    }

    private static final ArrayList<String> alreadyGeneratedItems = new ArrayList<>(List.of("odd_seed"));

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemGen = itemModelGenerator;
        /*generated(ModItems.get("boy_doll"));
        generated(ModItems.get("girl_doll"));
        generated(ModItems.get("otherworldly_bone"));
        generated(ModItems.get("chipped_carapace"));
        generated(ModItems.get("enormous_tardigrade"));
        generated(ModItems.get("hunk_of_amber"));
        generated(ModItems.get("moss_sample"));
        generated(ModItems.get("mysterious_chunk"));
        generated(ModItems.get("quickmetal"));
        generated(ModItems.get("scrap_metal"));
        generated(ModItems.get("villager_doll"));
        generated(ModItems.get("warped_meat"));
        generated(ModItems.get("cito_sanitatem_caro"));
        generated(ModItems.get("omen_of_caro_invictus"));
        generated(ModItems.get("reinforced_handle"));
        generated(ModItems.get("logging_axe_head"));
        generated(ModItems.get("magnet"));*/
        handheldRod("glasscutter");
        handheldRod("persona_monosword");
        handheld("logging_axe");

        for (Map.Entry<Item, RegistryEntry<Item>> entry : ModRegistries.ITEMS.ENTRIES_BY_VALUE.entrySet()) {
            if (alreadyGeneratedItems.contains(entry.getValue().getKey().orElseThrow().getValue().getPath())) continue;
            generated(entry.getKey());
        }
    }

    public void generated(Item item) {
        itemGen.register(item, Models.GENERATED);
    }

    public void handheldRod(String id) {
        itemGen.register(ModItems.get(id),Models.HANDHELD_ROD);
        alreadyGeneratedItems.add(id);
    }

    public void handheld(String id) {
        itemGen.register(ModItems.get(id),Models.HANDHELD);
        alreadyGeneratedItems.add(id);
    }
}