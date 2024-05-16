package com.kd8lvt.exclusionzone.init.Blocks;

import com.kd8lvt.exclusionzone.init.Blocks.entity.MufflerBE;
import com.kd8lvt.exclusionzone.init.Blocks.entity.RiftBE;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.command.argument.BlockRotationArgumentType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class Muffler extends Block implements BlockEntityProvider {
    public Muffler() {
        super(Settings.create().strength(2f,0f).sounds(BlockSoundGroup.WOOL));
    }


    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new MufflerBE(pos,state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) return null;
        return MufflerBE.tick();
    }
}
