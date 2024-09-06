package com.kd8lvt.exclusionzone.init.Blocks.entity;

import com.kd8lvt.exclusionzone.init.Blocks.EZBrushableBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BrushableBlockEntity;
import net.minecraft.util.math.BlockPos;

public class EZBrushableBlockEntity extends BrushableBlockEntity {
    public EZBrushableBlockEntity(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    @Override
    public boolean supports(BlockState state) {
        return state.getBlock() instanceof EZBrushableBlock;
    }
}
