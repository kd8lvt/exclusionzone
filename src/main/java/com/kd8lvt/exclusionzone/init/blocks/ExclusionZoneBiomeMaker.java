package com.kd8lvt.exclusionzone.init.blocks;

import com.kd8lvt.exclusionzone.init.blocks.entity.ExclusionZoneBiomeMakerBE;
import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class ExclusionZoneBiomeMaker extends Block implements BlockEntityProvider {
    public ExclusionZoneBiomeMaker() {
        super(Settings.create().strength(Blocks.BARRIER.getHardness(),Blocks.BARRIER.getBlastResistance()).noCollision().nonOpaque());
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ExclusionZoneBiomeMakerBE(pos,state);
    }
}
