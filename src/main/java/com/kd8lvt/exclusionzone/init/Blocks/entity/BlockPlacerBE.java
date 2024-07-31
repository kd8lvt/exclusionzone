package com.kd8lvt.exclusionzone.init.Blocks.entity;

import com.kd8lvt.exclusionzone.init.Blocks.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.init.Blocks.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class BlockPlacerBE extends DispenserCloneBaseBE {
    public ExclusionZoneFakePlayer player;

    public BlockPlacerBE(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        setDisplayName("Interaction Simulator");
    }

    public BlockPlacerBE(BlockPos pos, BlockState state) {
        this(ModBlocks.BLOCK_PLACER_BE, pos, state);
    }
}
