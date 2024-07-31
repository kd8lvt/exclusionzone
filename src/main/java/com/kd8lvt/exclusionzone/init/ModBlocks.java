package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.init.Blocks.entity.*;
import com.kd8lvt.exclusionzone.init.registries.ModBlockRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;

import java.util.HashMap;

@SuppressWarnings({"unchecked", "unused"})
public class ModBlocks {
    public static Block SUS_MOSS;
    public static Block AMBER_BLOCK;
    public static Block BLOCK_BREAKER;
    public static Block BLOCK_PLACER;
    public static Block MUFFLER;
    public static Block ENDERWEED;
    public static HashMap<String,Block> SUS_CONCRETE_POWDERS;
    public static Block EXCLUSION_ZONE_BIOME_MAKER;
    public static Block RIFT;
    public static BlockEntityType<ExclusionZoneBiomeMakerBE> EXCLUSION_ZONE_BIOME_MAKER_BE;
    public static BlockEntityType<RiftBE> RIFT_BE;
    public static BlockEntityType<MufflerBE> MUFFLER_BE;
    public static BlockEntityType<BlockPlacerBE> BLOCK_PLACER_BE;
    public static BlockEntityType<BlockBreakerBE> BLOCK_BREAKER_BE;

    public static void ready() {
        SUS_MOSS = ModBlockRegistry.SUS_MOSS.value();
        AMBER_BLOCK = ModBlockRegistry.AMBER_BLOCK.value();
        BLOCK_BREAKER = ModBlockRegistry.AMBER_BLOCK.value();
        BLOCK_PLACER = ModBlockRegistry.BLOCK_PLACER.value();
        MUFFLER = ModBlockRegistry.MUFFLER.value();
        ENDERWEED = ModBlockRegistry.ENDERWEED.value();
        EXCLUSION_ZONE_BIOME_MAKER = ModBlockRegistry.EXCLUSION_ZONE_BIOME_MAKER.value();
        RIFT = ModBlockRegistry.RIFT.value();
        EXCLUSION_ZONE_BIOME_MAKER_BE = (BlockEntityType<ExclusionZoneBiomeMakerBE>)ModBlockRegistry.EXCLUSION_ZONE_BIOME_MAKER_BE.value();
        RIFT_BE = (BlockEntityType<RiftBE>)ModBlockRegistry.RIFT_BE.value();
        MUFFLER_BE = (BlockEntityType<MufflerBE>)ModBlockRegistry.MUFFLER_BE.value();
        BLOCK_PLACER_BE = (BlockEntityType<BlockPlacerBE>)ModBlockRegistry.BLOCK_PLACER_BE.value();
        BLOCK_BREAKER_BE = (BlockEntityType<BlockBreakerBE>)ModBlockRegistry.BLOCK_BREAKER_BE.value();
    }
}
