package com.kd8lvt.exclusionzone.block.entity;

import com.kd8lvt.exclusionzone.block.bases.entity.EZBlockEntityEntity;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.DyeColor;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class GradientGlassBE extends EZBlockEntityEntity {
    private int ticks = 0;
    public boolean direction = true;
    public static final int ticksPerCycle = 20;//*15;
    public boolean hasReported = false;
    public int gradientStart = DyeColor.BLACK.getEntityColor();
    public int gradientEnd = DyeColor.CYAN.getEntityColor();
    protected GradientGlassBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public GradientGlassBE(BlockPos pos, BlockState state) {
        this(ModBlockEntities.get("gradient_glass"),pos,state);
    }

    public float getProgress() {
        return (float)ticks / ticksPerCycle;
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        this.ticks = nbt.getInt("progress");
        this.direction = nbt.getBoolean("direction");
        NbtCompound start = nbt.getCompound("start");
        NbtCompound end = nbt.getCompound("end");
        this.gradientStart = ColorHelper.Argb.getArgb(1,start.getInt("r"),start.getInt("g"),start.getInt("b"));
        this.gradientEnd = ColorHelper.Argb.getArgb(1,end.getInt("r"),end.getInt("g"),end.getInt("b"));
        super.readNbt(nbt, registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        nbt.putInt("progress",this.ticks);
        NbtCompound start = new NbtCompound();
        start.putInt("r",ColorHelper.Argb.getRed(gradientStart));
        start.putInt("g",ColorHelper.Argb.getGreen(gradientStart));
        start.putInt("b",ColorHelper.Argb.getBlue(gradientStart));
        nbt.put("start",start);

        NbtCompound end = new NbtCompound();
        end.putInt("r",ColorHelper.Argb.getRed(gradientStart));
        end.putInt("g",ColorHelper.Argb.getGreen(gradientStart));
        end.putInt("b",ColorHelper.Argb.getBlue(gradientStart));
        nbt.put("end",end);

        nbt.putBoolean("direction",this.direction);
        super.writeNbt(nbt, registryLookup);
    }

    public boolean getDirection() {
        return direction;
    }

    public int getColor() {
        int color;
        if (getDirection()) color = ColorHelper.Argb.lerp(getProgress(),gradientStart,gradientEnd);
        else color = ColorHelper.Argb.lerp(getProgress(),gradientEnd,gradientStart);
        return color;
    }

    @Override
    public <T extends BlockEntity> void tick(World world, BlockPos blockPos, BlockState blockState, T be) {
        if (ticks++ > ticksPerCycle) {
            ticks=0;
            direction = !direction;
        }
        int currentColor = getColor();
        int gradientY = pos.getY();
        for (int targetY = gradientY-1; targetY >= Objects.requireNonNull(this.world).getBottomY(); targetY--) {
            BlockPos newPos = pos.withY(targetY);
            if (world.getBlockEntity(newPos) instanceof BeaconBlockEntity beacon) {
                List<BeaconBlockEntity.BeamSegment> segments = beacon.getBeamSegments();
                for (BeaconBlockEntity.BeamSegment segment : segments) {
                    if (segment.getHeight()+targetY >= gradientY) segments.set(segments.indexOf(segment),new BeaconBlockEntity.BeamSegment(ColorHelper.Argb.averageArgb(currentColor,segment.getColor()==-393218?currentColor:segment.getColor())));
                }
            }
        }
        hasReported = true;
        this.markDirty();
    }
}
