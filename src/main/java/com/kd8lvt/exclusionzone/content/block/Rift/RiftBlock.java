package com.kd8lvt.exclusionzone.content.block.Rift;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class RiftBlock extends Block implements BlockEntityProvider {
    public RiftEntity be;

    public RiftBlock() {
        super(Settings.create().pistonBehavior(PistonBehavior.BLOCK).strength(-1,Integer.MAX_VALUE).noCollision().luminance((BlockState value)->15).nonOpaque());
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) return null;
        return RiftEntity.tick();
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        be = new RiftEntity(pos,state);
        return be;
    }
}
