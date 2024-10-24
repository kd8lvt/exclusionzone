package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.block.entity.*;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.entry.RegistryEntry;

public class ModBlockEntities {
    static final ModRegistry<Block> blocks = ModRegistries.BLOCKS;
    static void register() {
        register("exclusionzonemaker", FabricBlockEntityTypeBuilder.create(ExclusionZoneBiomeMakerBE::new, blocks.get("exclusion_zone_biome_maker").value()).build());
        register("rift", FabricBlockEntityTypeBuilder.create(RiftBE::new, blocks.get("rift").value()).build());
        register("muffler", FabricBlockEntityTypeBuilder.create(MufflerBE::new, blocks.get("muffler").value()).build());
        register("interaction_simulator", FabricBlockEntityTypeBuilder.create(BlockPlacerBE::new, blocks.get("interaction_simulator").value()).build());
        register("mining_simulator", FabricBlockEntityTypeBuilder.create(BlockBreakerBE::new, blocks.get("mining_simulator").value()).build());
        register("fluid_pipe", FabricBlockEntityTypeBuilder.create(FluidPipeBE::new, blocks.get("fluid_pipe").value()).build());
        register("gradient_glass", FabricBlockEntityTypeBuilder.create(GradientGlassBE::new, blocks.get("gradient_glass").value()).build());
    }

    private static void register(String id, BlockEntityType<? extends BlockEntity> value) {
        ModRegistries.register(id,value);
    }


    public static RegistryEntry<BlockEntityType<?>> getEntry(String id) {return ModRegistries.BLOCK_ENTITIES.get(id);}
    public static BlockEntityType<?> get(String id) {return getEntry(id).value();}
}
