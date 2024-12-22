package com.kd8lvt.exclusionzone.content.block.CoordinatedDistributor;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class CoordinatedDistributorBlock extends BlockWithEntity {
    public static MapCodec<CoordinatedDistributorBlock> CODEC = createCodec(CoordinatedDistributorBlock::new);
    public CoordinatedDistributorBlock() {
        super(Settings.copy(Blocks.OAK_PLANKS).resistance(Blocks.OBSIDIAN.getBlastResistance()));
    }
    public CoordinatedDistributorBlock(Settings settings) {
        this();
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new CoordinatedDistributorEntity(pos,state);
    }

    /*@Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return CoordinatedDistributorEntity::tick;
    }*/
}
