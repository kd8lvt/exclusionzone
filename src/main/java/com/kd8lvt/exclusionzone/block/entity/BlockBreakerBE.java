package com.kd8lvt.exclusionzone.block.entity;

import com.kd8lvt.exclusionzone.block.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.block.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.GameMode;
import org.apache.commons.lang3.tuple.Pair;

import static net.minecraft.state.property.Properties.FACING;

public class BlockBreakerBE extends DispenserCloneBaseBE {
    public final int size = 1;
    public boolean breaking;
    public BlockPointer breakingPointer;
    public ExclusionZoneFakePlayer player;
    public BlockBreakerBE(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        setDisplayName("Mining Simulator");
    }
    public void startBreaking(BlockPointer pointer) {
        if (pointer.world().isClient) return;
        if (pointer.state().getBlock().equals(Blocks.AIR)) return;
        this.breaking = true;
        this.breakingPointer=pointer;
    }

    public float updateBreakProgress() {
        if (!this.breaking) return -1;
        ServerWorld world = breakingPointer.world();
        if (this.player == null) this.player = new ExclusionZoneFakePlayer(world);
        BlockPos pos = breakingPointer.pos();
        BlockState clickedState = world.getBlockState(pos);
        if (!world.canPlayerModifyAt(player, pos)) return -1;
        if (clickedState.getOutlineShape(world, this.pos).isEmpty()) {player.blockBreakingProgress = null; return -1;}
        float progress = clickedState.calcBlockBreakingDelta(player,player.getWorld(), pos)*16;
        float before = 0;
        Pair<BlockPos,Float> blockBreakingProgress = player.blockBreakingProgress;
        if (blockBreakingProgress != null) before = blockBreakingProgress.getValue();
        progress+=before;
        player.blockBreakingProgress = Pair.of(pos,progress);
        if ((int)(before*10)!=(int)(progress*10))
            world.setBlockBreakingInfo(player.getId(), pos,(int)(progress*10));
        return progress;
    }

    public static void tryHarvestBlock(BlockBreakerBE be, BlockPos pos) {
        ExclusionZoneFakePlayer player = be.player;
        ServerWorld world = player.getServerWorld();
        BlockState blockstate = world.getBlockState(pos);
        GameMode gameMode = player.interactionManager.getGameMode();

        if (!PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(world, player, pos, world.getBlockState(pos), world.getBlockEntity(pos))) return;
        if (player.isBlockBreakingRestricted(world,pos,gameMode)) return;

        ItemStack prevHeldItem = player.getStackInHand(player.getActiveHand());
        if (world.breakBlock(pos,player.canHarvest(blockstate),player)) {
            prevHeldItem.postMine(world,blockstate,pos,player);
        }
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> tick() {
        return (world, pos, state, be) -> {
            BlockBreakerBE blockEntity = (BlockBreakerBE)be;
            if (!blockEntity.breaking) return;
            float progress = blockEntity.updateBreakProgress();
            BlockPos targetPos = pos.offset(state.get(FACING),1);
            BlockState targetState = world.getBlockState(targetPos);
            ExclusionZoneFakePlayer player = blockEntity.player;
            if (progress >= 1) {
                tryHarvestBlock(blockEntity, targetPos);
                world.setBlockBreakingInfo(player.getId(), targetPos,-1);
                player.blockBreakingProgress = null;
                blockEntity.breaking = false;
                return;
            }
            if (progress <= 0) {
                player.blockBreakingProgress = null;
                blockEntity.breaking = false;
                return;
            }
            world.playSound(null, targetPos,targetState.getSoundGroup().getHitSound(), SoundCategory.NEUTRAL,.25f,1);
        };
    }

    public BlockBreakerBE(BlockPos pos, BlockState state) {
        this(ModBlockEntities.get("mining_simulator"), pos, state);
    }
}
