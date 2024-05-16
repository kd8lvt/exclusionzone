package com.kd8lvt.exclusionzone.init.Blocks;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Blocks.bases.DispenserCloneBase;
import com.kd8lvt.exclusionzone.init.Blocks.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.init.Blocks.entity.BlockBreakerBE;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.network.packet.c2s.play.PlayerActionC2SPacket;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("deprecation")
public class BlockBreaker extends DispenserCloneBase {
    public int size = 9;
    public Text displayName = Text.of("Mining Simulator");

    public BlockBreaker() {
        super(ExclusionZone.id("mining_simulator"),Text.of("Mining Simulator"));
    }

    public BlockBreaker(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntityType<? extends DispenserCloneBaseBE> getBEType() {
        return ModBlocks.BLOCK_BREAKER_BE;
    }

    @Override
    public MapCodec<? extends DispenserCloneBase> getCodec() {
        return BlockBreaker.createCodec(BlockBreaker::new);
    }

    @Override
    public ItemStack onDispense(BlockPointer pointer, ItemStack stack1) {
        if (pointer.world().isClient) return stack1;
        ItemStack stack = stack1.copy();
        BlockBreakerBE be = (BlockBreakerBE) pointer.blockEntity();
        if (!be.playerExists()) be.setupFakePlayer(pointer.world());
        Vec3d targetPos = pointer.pos().offset(pointer.state().get(FACING),1).toCenterPos();
        be.player.teleport(pointer.world(),targetPos.x,targetPos.y,targetPos.z,0,0);
        be.player.interactionManager.changeGameMode(GameMode.SURVIVAL);
        be.player.setCurrentHand(Hand.MAIN_HAND);
        be.player.setInvulnerable(true);
        for (int i=0;i<size;i++) {
            be.player.getInventory().setStack(i,be.getStack(i));
            if (be.getStack(i).canBreak(new CachedBlockPosition(pointer.world(),pointer.pos().offset(pointer.state().get(FACING),1),false))) {
                be.player.getInventory().selectedSlot = 1;
                break;
            }
        }
        be.player.getInventory().updateItems();
        if (!be.breaking) be.startBreaking(pointer,stack);
        return stack;
    }

    @Override
    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        super.neighborUpdate(state,world,pos,sourceBlock,sourcePos,notify);
        if (world.isClient) return;
        BlockBreakerBE be = (BlockBreakerBE) world.getBlockEntity(pos);
        ItemStack item = be.player.getStackInHand(Hand.MAIN_HAND);
        for (int i=0;i<be.size;i++) {
            if (be.getStack(i).getItem().equals(item.getItem()) && !be.getStack(i).equals(item)) {
                be.setStack(i,be.player.getStackInHand(Hand.MAIN_HAND));
                break;
            }
        }
    }
}
