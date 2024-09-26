package com.kd8lvt.exclusionzone.datagen;

import com.google.gson.JsonObject;
import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Blocks.Enderweed;
import com.kd8lvt.exclusionzone.init.Blocks.FluidPipeBlock;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.kd8lvt.exclusionzone.init.ModItems;
import com.kd8lvt.exclusionzone.init.registries.ModBlockRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.*;
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
        BlockStateVariantMap pipe_variants = BlockStateVariantMap.create(FluidPipeBlock.POWER,FluidPipeBlock.POWERED).register((i,b)->BlockStateVariant.create().put(VariantSettings.MODEL, ExclusionZone.id("block/fluid_pipe")));
        blockStateModelGenerator.blockStateCollector.accept(VariantsBlockStateSupplier.create(ModBlocks.FLUID_PIPE).coordinate(pipe_variants));
        blockStateModelGenerator.modelCollector.accept(ExclusionZone.id("block/fluid_pipe"),()->{
            JsonObject ret = new JsonObject();
            ret.addProperty("parent","minecraft:block/cube_all");
            JsonObject textures = new JsonObject();
            textures.addProperty("all","exclusionzone:block/fluid_pipe");
            ret.add("textures",textures);
            return ret;
        });
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