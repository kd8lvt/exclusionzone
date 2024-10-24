package com.kd8lvt.exclusionzone.block.entity;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class MufflerBE extends BlockEntity {
    public MufflerBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.get("muffler"), pos, state);
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> tick() {
        return (world1, pos, state1, be) -> ExclusionZone.runCommand("execute in " + Objects.requireNonNull(be.getWorld()).getRegistryKey().getValue().toString() + " positioned " + pos.getX() + " " + pos.getY() + " " + pos.getZ() + " run stopsound @a[distance=..16]");
    }


}
