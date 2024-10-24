package com.kd8lvt.exclusionzone.block.entity;

import com.kd8lvt.exclusionzone.block.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.block.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
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
        this(ModBlockEntities.get("interaction_simulator"), pos, state);
    }
}
