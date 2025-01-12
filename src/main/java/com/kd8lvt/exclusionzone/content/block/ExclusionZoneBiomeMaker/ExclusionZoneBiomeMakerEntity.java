package com.kd8lvt.exclusionzone.content.block.ExclusionZoneBiomeMaker;

import com.kd8lvt.exclusionzone.api.helpers.BiomeHelper;
import com.kd8lvt.exclusionzone.api.CommonConstants;
import com.kd8lvt.exclusionzone.api.helpers.WorldHelper;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public class ExclusionZoneBiomeMakerEntity extends BlockEntity {
    private static final int radius = 10;
    public ExclusionZoneBiomeMakerEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.get("exclusionzonemaker"),pos,state);
    }

    public static <T extends BlockEntity> void tick(World w, BlockPos pos, BlockState state, T be) {
        if (!(w instanceof ServerWorld world)) return;
        //Make sure that a 3x3 area of chunks around the block are generated
        if (!WorldHelper.surroundingChunksGenerated(world,new ChunkPos(pos))) {
            //If they're not, make sure they get loaded at some point
            WorldHelper.loadSurroundingChunks(world,new ChunkPos(pos));
            //Wait until next tick to try again
            return;
        };
        //Fill in the biome
        BiomeHelper.fillCenteredRect(world,pos,radius, CommonConstants.BIOME.get());
        //Replace yourself with air
        world.setBlockState(pos, Blocks.AIR.getDefaultState());
    }
}
