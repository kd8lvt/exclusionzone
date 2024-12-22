package com.kd8lvt.exclusionzone.content.block.Enderweed;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.api.helpers.BiomeHelper;
import com.kd8lvt.exclusionzone.registry.ModBlocks;
import com.kd8lvt.exclusionzone.registry.ModItems;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class Enderweed extends CropBlock {
    public Enderweed() {
        super(Settings.create().mapColor(MapColor.DARK_GREEN).noCollision().ticksRandomly().breakInstantly().sounds(BlockSoundGroup.CROP).pistonBehavior(PistonBehavior.DESTROY).notSolid().nonOpaque());
    }

    @Override
    protected boolean canPlantOnTop(BlockState floor, BlockView world, BlockPos pos) {
        return floor.isOf(Blocks.MOSS_BLOCK) || floor.isOf(ModBlocks.get("suspicious_moss"));
    }

    @Override
    public boolean isFertilizable(WorldView world, BlockPos pos, BlockState state) {
        return false;
    }
    @Override
    protected ItemConvertible getSeedsItem() {
        return ModItems.get("odd_seed");
    }

    @SuppressWarnings({"unchecked","DataFlowIssue"})
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.random.nextBetween(1,3) == 3) {
//            super.randomTick(state, world, pos, random);
            this.superRandomTick(state,world,pos,random);
            if (world.isClient) return;

            BlockState newState = world.getBlockState(pos);
            if (newState == state) return;

            int radius = this.getAge(newState);
            int yChange = radius;
            while (world.isOutOfHeightLimit(pos.getY() + yChange)) yChange--;
            while (world.isOutOfHeightLimit(pos.getY() - yChange)) yChange++;



            BiomeHelper.fill(
                    world,
                    pos.south(radius).west(radius).down(radius),
                    pos.north(radius).east(radius).up(radius),
                    ExclusionZone.BIOME,
                    (biome)->true);
        }
    }

    private void superRandomTick(BlockState state, World world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = getAge(state);
            if (i < getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                }
            }
        }
    }

    protected static float getAvailableMoisture(Block block, BlockView world, BlockPos pos) {
        float moisture = 1F;
        BlockPos blockPos = pos.down();

        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                float g = 0.0F;
                BlockState blockState = world.getBlockState(blockPos.add(x, 0, y));
                if (blockState.isOf(Blocks.MOSS_BLOCK)) {
                    g = 3.0F;
                }

                if (x != 0 || y != 0) {
                    g /= 4.0F;
                }

                moisture += g;
            }
        }

        BlockPos north = pos.north();
        BlockPos south = pos.south();
        BlockPos west = pos.west();
        BlockPos east = pos.east();
        boolean bl = world.getBlockState(north).isOf(block) || world.getBlockState(east).isOf(block);
        boolean bl2 = world.getBlockState(south).isOf(block) || world.getBlockState(west).isOf(block);
        if (bl && bl2) {
            moisture /= 2.0F;
        } else {
            boolean bl3 = world.getBlockState(west.north()).isOf(block)
                    || world.getBlockState(east.north()).isOf(block)
                    || world.getBlockState(east.south()).isOf(block)
                    || world.getBlockState(west.south()).isOf(block);
            if (bl3) {
                moisture /= 2.0F;
            }
        }

        return moisture;
    }



    @Override
    public BlockState onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        if (world.isClient) return super.onBreak(world,pos,state,player);
        int radius = this.getAge(state);
        int yChange = radius;
        while (world.isOutOfHeightLimit(pos.getY() + yChange)) yChange--; //stay within height limits
        while (world.isOutOfHeightLimit(pos.getY() - yChange)) yChange++;
        BiomeHelper.fill(
                (ServerWorld)world, //world
                pos.south(radius).west(radius).down(radius), //from here
                pos.north(radius).east(radius).up(radius), //to there
                world.getBiome(pos.north(radius+5)), //biome to fill with
                biome->true //biome filter
        );
        return super.onBreak(world, pos, state, player);
    }
}
