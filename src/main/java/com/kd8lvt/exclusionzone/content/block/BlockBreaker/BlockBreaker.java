package com.kd8lvt.exclusionzone.content.block.BlockBreaker;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.content.block.bases.DispenserCloneBase;
import com.kd8lvt.exclusionzone.content.block.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.content.block.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BlockBreaker extends DispenserCloneBase {
    //Not currently functional because I'm too dumb
    public final int size = 9;

    public BlockBreaker() {
        super(ExclusionZone.id("mining_simulator"),Text.of("Mining Simulator"));
    }

    public BlockBreaker(Settings settings) {
        super(settings);
    }

    @SuppressWarnings("unchecked")
    @Override
    public BlockEntityType<? extends DispenserCloneBaseBE> getBEType() {
        return (BlockEntityType<? extends DispenserCloneBaseBE>) ModBlockEntities.get("mining_simulator");
    }

    @Override
    public MapCodec<? extends DispenserCloneBase> getCodec() {
        return BlockBreaker.createCodec(BlockBreaker::new);
    }

    @Override
    public ItemStack onDispense(BlockPointer pointer, ItemStack stack1) {
        if (pointer.world().isClient) return stack1;
        if (pointer.state().getBlock() == Blocks.AIR) return stack1;
        ServerWorld world = pointer.world();
        BlockBreakerEntity be = (BlockBreakerEntity) pointer.blockEntity();
        if (be.breaking) return stack1;
        if (be.player == null) be.player = new ExclusionZoneFakePlayer(world);
        ExclusionZoneFakePlayer player = be.player;
        Hand hand = player.getActiveHand();
        BlockPos targetBlockPos = pointer.pos().offset(pointer.state().get(FACING),1);
        player.getInventory().selectedSlot = 0;
        for (int i=0;i<size;i++) {
            ItemStack curStack = be.getStack(i);
            if (curStack.getCount() <= 0) continue;
            player.getInventory().setStack(0,curStack);
            player.getInventory().updateItems();
            player.setStackInHand(hand,curStack);
            if (player.canHarvest(world.getBlockState(targetBlockPos))) {
                break;
            }
        }
        ItemStack handStack = player.getStackInHand(hand);
        player.setStackInHand(Hand.MAIN_HAND,handStack);
        BlockPointer breakPointer = new BlockPointer(world,targetBlockPos,world.getBlockState(targetBlockPos),be);
        if (!be.breaking) be.startBreaking(breakPointer);
        return handStack;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state,world,pos,sourceBlock,sourcePos,notify);
        if (world.isClient) return;
        BlockBreakerEntity be = (BlockBreakerEntity) world.getBlockEntity(pos);
        if (be != null && be.player == null) be.player = new ExclusionZoneFakePlayer((ServerWorld)world);
        ItemStack item = Objects.requireNonNull(be).player.getStackInHand(Hand.MAIN_HAND);
        for (int i=0;i<be.size;i++) {
            if (be.getStack(i).getItem().equals(item.getItem()) && !be.getStack(i).equals(item)) {
                be.setStack(i,be.player.getStackInHand(Hand.MAIN_HAND));
                break;
            }
        }
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        if (world.isClient()) return null;
        return BlockBreakerEntity.tick();
    }
}
