package com.kd8lvt.exclusionzone.content.block.bases;

import com.kd8lvt.exclusionzone.content.block.bases.entity.EZBlockEntityEntity;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.function.Supplier;

public abstract class EZBlockEntityBlock<BE extends EZBlockEntityEntity> extends BlockWithEntity {
    Supplier<BlockEntityType<BE>> beTypeSupplier;
    BE be;
    boolean doesTick;
    protected EZBlockEntityBlock(Settings settings, Supplier<BlockEntityType<BE>> beType, @Nullable Boolean doesTick) {
        super(settings);
        this.beTypeSupplier = beType;
        if (Objects.isNull(doesTick)) this.doesTick = false;
        else this.doesTick = doesTick;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return null;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        this.be = beTypeSupplier.get().instantiate(pos,state);
        return be;
    }

    @SuppressWarnings("unchecked")
    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (!doesTick) return null;
        return (World _world,BlockPos pos,BlockState _state,T be)->{
            if (Objects.isNull(this.be)) this.be = (BE)be;
            ((EZBlockEntityEntity)be).tick(_world,pos,_state,be);
        };
    }

    @Nullable
    public BE getBe() {
        if (Objects.isNull(be)) return null;
        return be;
    }
}
