package com.kd8lvt.exclusionzone.init.Blocks;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.kd8lvt.exclusionzone.init.ModItems;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;
import net.minecraft.world.biome.source.BiomeCoords;

public class Enderweed extends CropBlock {
    public Enderweed() {
        super(Block.Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).notSolid().nonOpaque());
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        if (floor.isOf(Blocks.MOSS_BLOCK) || floor.isOf(ModBlocks.SUS_MOSS)) return true;
        return false;
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return false;
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.ODD_SEED;
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.random.nextBetween(1,3) == 3) {
            super.randomTick(state, world, pos, random);
            if (world.isClient) return;

            BlockState newState = world.getBlockState(pos);
            if (newState == state) return;

            int radius = this.getAge(newState);
            int yChange = radius;
            while (world.isOutOfHeightLimit(pos.getY() + yChange)) yChange--;
            while (world.isOutOfHeightLimit(pos.getY() - yChange)) yChange++;
            String cmd = ("execute in " + world.getDimensionEntry().getIdAsString() + " positioned " + pos.getX() + " " + pos.getY() + " " + pos.getZ() + " run fillbiome ~" + radius + " ~" + yChange + " ~" + radius + " ~-" + radius + " ~-" + yChange + " ~-" + radius + " exclusionzone:exclusion_zone");
            ExclusionZone.runCommand(cmd);
        }
    }



    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        int radius = this.getAge(state);
        int yChange = radius;
        while (world.isOutOfHeightLimit(pos.getY() + yChange)) yChange--;
        while (world.isOutOfHeightLimit(pos.getY() - yChange)) yChange++;
        String cmd = ("execute in " + world.getDimensionEntry().getIdAsString() + " positioned " + pos.getX() + " " + pos.getY() + " " + pos.getZ() + " run fillbiome ~" + radius + " ~" + yChange + " ~" + radius + " ~-" + radius + " ~-" + yChange + " ~-" + radius + " "+world.getGeneratorStoredBiome(pos.getX(),pos.getY(),pos.getZ()).getKey().get().getValue().toString());
        ExclusionZone.runCommand(cmd);
        return super.onBreak(world, pos, state, player);
    }
}
