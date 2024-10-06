package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.init.blocks.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.entry.RegistryEntry;

public class ModBlockEntities {
    static final ModRegistry<Block> blocks = ModRegistries.BLOCKS;
    static void register() {
        EZ_BIOME_MAKER = ModRegistries.register("exclusion_zone_biome_maker", FabricBlockEntityTypeBuilder.create(ExclusionZoneBiomeMakerBE::new, blocks.get("exclusion_zone_biome_maker").value()).build());
        RIFT = ModRegistries.register("rift", FabricBlockEntityTypeBuilder.create(RiftBE::new, blocks.get("rift").value()).build());
        MUFFLER = ModRegistries.register("muffler", FabricBlockEntityTypeBuilder.create(MufflerBE::new, blocks.get("muffler").value()).build());
        BLOCK_PLACER = ModRegistries.register("interaction_simulator", FabricBlockEntityTypeBuilder.create(BlockPlacerBE::new, blocks.get("interaction_simulator").value()).build());
        BLOCK_BREAKER = ModRegistries.register("mining_simulator", FabricBlockEntityTypeBuilder.create(BlockBreakerBE::new, blocks.get("mining_simulator").value()).build());
        FLUID_PIPE = ModRegistries.register("fluid_pipe", FabricBlockEntityTypeBuilder.create(FluidPipeBE::new, blocks.get("fluid_pipe").value()).build());
    }


    public static RegistryEntry<BlockEntityType<?>> getEntry(String id) {return ModRegistries.BLOCK_ENTITIES.get(id);}
    public static BlockEntityType<?> get(String id) {return getEntry(id).value();}

    public static ModContent<BlockEntityType<?>> EZ_BIOME_MAKER;
    public static ModContent<BlockEntityType<?>> RIFT;
    public static ModContent<BlockEntityType<?>> MUFFLER;
    public static ModContent<BlockEntityType<?>> BLOCK_PLACER;
    public static ModContent<BlockEntityType<?>> BLOCK_BREAKER;
    public static ModContent<BlockEntityType<?>> FLUID_PIPE;
}
