package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.content.block.BlockBreaker.BlockBreakerEntity;
import com.kd8lvt.exclusionzone.content.block.BlockPlacer.BlockPlacerEntity;
import com.kd8lvt.exclusionzone.content.block.ExclusionZoneBiomeMaker.ExclusionZoneBiomeMakerEntity;
import com.kd8lvt.exclusionzone.content.block.GradientGlass.GradientGlassEntity;
import com.kd8lvt.exclusionzone.content.block.Muffler.MufflerEntity;
import com.kd8lvt.exclusionzone.content.block.Rift.RiftEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.entry.RegistryEntry;

public class ModBlockEntities {
    static final ModRegistry<Block> blocks = ModRegistries.BLOCKS;
    static void register() {
        register("exclusionzonemaker", FabricBlockEntityTypeBuilder.create(ExclusionZoneBiomeMakerEntity::new, blocks.get("exclusion_zone_biome_maker").value()).build());
        register("rift", FabricBlockEntityTypeBuilder.create(RiftEntity::new, blocks.get("rift").value()).build());
        register("muffler", FabricBlockEntityTypeBuilder.create(MufflerEntity::new, blocks.get("muffler").value()).build());
        register("interaction_simulator", FabricBlockEntityTypeBuilder.create(BlockPlacerEntity::new, blocks.get("interaction_simulator").value()).build());
        register("mining_simulator", FabricBlockEntityTypeBuilder.create(BlockBreakerEntity::new, blocks.get("mining_simulator").value()).build());
        register("gradient_glass", FabricBlockEntityTypeBuilder.create(GradientGlassEntity::new, blocks.get("gradient_glass").value()).build());
        //register("coordinated_distributor", FabricBlockEntityTypeBuilder.create(CoordinatedDistributorEntity::new, blocks.get("coordinated_distributor").value()).build());
    }

    private static void register(String id, BlockEntityType<? extends BlockEntity> value) {
        ModRegistries.register(id,value);
    }


    public static RegistryEntry<BlockEntityType<?>> getEntry(String id) {return ModRegistries.BLOCK_ENTITIES.get(id);}
    public static BlockEntityType<?> get(String id) {return getEntry(id).value();}
}
