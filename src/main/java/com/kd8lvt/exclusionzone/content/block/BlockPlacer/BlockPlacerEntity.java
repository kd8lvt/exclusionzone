package com.kd8lvt.exclusionzone.content.block.BlockPlacer;

import com.kd8lvt.exclusionzone.content.block.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.content.block.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;

public class BlockPlacerEntity extends DispenserCloneBaseBE {
    public ExclusionZoneFakePlayer player;

    public BlockPlacerEntity(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        setDisplayName("Interaction Simulator");
    }

    public BlockPlacerEntity(BlockPos pos, BlockState state) {
        this(ModBlockEntities.get("interaction_simulator"), pos, state);
    }
}
