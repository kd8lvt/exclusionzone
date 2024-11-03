package com.kd8lvt.exclusionzone.content.block.bases.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class EZBlockEntityEntity extends BlockEntity {
    BlockEntityType<?> type;
    public EZBlockEntityEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
        this.type =  type;
    }

    public <T extends BlockEntity> void tick(World world, BlockPos blockPos, BlockState blockState, T type) {}
}
