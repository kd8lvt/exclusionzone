package com.kd8lvt.exclusionzone.content.block.ExclusionZoneBiomeMaker;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ExclusionZoneBiomeMaker extends Block implements BlockEntityProvider {
    public ExclusionZoneBiomeMaker() {
        super(Settings.create().strength(Blocks.BARRIER.getHardness(),Blocks.BARRIER.getBlastResistance()).noCollision().nonOpaque());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ExclusionZoneBiomeMakerEntity(pos,state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return ExclusionZoneBiomeMakerEntity::tick;
    }
}
