package com.kd8lvt.exclusionzone.content.block.Muffler;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.util.math.BlockPos;

import java.util.Objects;

public class MufflerEntity extends BlockEntity {
    public MufflerEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.get("muffler"), pos, state);
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> tick() {
        return (world1, pos, state1, be) -> ExclusionZone.runCommand("execute in " + Objects.requireNonNull(be.getWorld()).getRegistryKey().getValue().toString() + " positioned " + pos.getX() + " " + pos.getY() + " " + pos.getZ() + " run stopsound @a[distance=..16]");
    }


}
