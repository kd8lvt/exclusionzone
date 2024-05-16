package com.kd8lvt.exclusionzone.init.Blocks.entity;

import com.kd8lvt.exclusionzone.init.Blocks.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.init.Blocks.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.enums.DoubleBlockHalf;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerInteractionManager;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameMode;
import net.minecraft.world.RaycastContext;
import org.apache.commons.lang3.tuple.Pair;

import static net.minecraft.state.property.Properties.DOUBLE_BLOCK_HALF;
import static net.minecraft.state.property.Properties.FACING;

public class BlockBreakerBE extends DispenserCloneBaseBE {
    public int size = 1;
    public boolean breaking;
    public BlockPointer breakingPointer;
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
    public BlockBreakerBE(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        setDisplayName("Interaction Simulator");
    }
    public void startBreaking(BlockPointer pointer, ItemStack stack) {
        if (pointer.world().isClient) return;
        player.setStackInHand(Hand.MAIN_HAND,stack);
        this.breaking = true;
        this.breakingPointer=pointer;
    }

    public void tryBreak() {
        ServerWorld world = breakingPointer.world();
        BlockPos clickedPos = breakingPointer.pos().offset(breakingPointer.state().get(FACING),1);
        //Code stolen nearly verbatim from Create Fabric. Thanks y'all, made my life WAY easier :)
        RaycastContext rayTraceContext = player.getRaycastContext(breakingPointer.state());
        BlockHitResult result = world.raycast(rayTraceContext);
        if (result.getBlockPos() != clickedPos) result = new BlockHitResult(result.getPos(),result.getSide(),clickedPos,result.isInsideBlock());
        BlockState clickedState = world.getBlockState(clickedPos);
        Direction face = result.getSide();
        if (face==null) face = breakingPointer.state().get(FACING).getOpposite();

        if (!world.canPlayerModifyAt(player,clickedPos)) return;
        if (clickedState.getOutlineShape(world,pos).isEmpty()) {player.blockBreakingProgress = null; return;}
        ActionResult ar = UseBlockCallback.EVENT.invoker().interact(player,player.getWorld(),player.getActiveHand(),result);
        float progress = clickedState.calcBlockBreakingDelta(player,player.getWorld(),clickedPos)*16;
        float before = 0;
        Pair<BlockPos,Float> blockBreakingProgress = player.blockBreakingProgress;
        if (blockBreakingProgress != null) before = blockBreakingProgress.getValue();
        progress+=before;
        world.playSound(null,clickedPos,clickedState.getSoundGroup().getHitSound(), SoundCategory.NEUTRAL,.25f,1);
        if (progress >= 1) {
            tryHarvestBlock(player,player.interactionManager,clickedPos);
            world.setBlockBreakingInfo(player.getId(),clickedPos,-1);
            player.blockBreakingProgress = null;
            return;
        }
        if (progress <= 0) {
            player.blockBreakingProgress = null;
            return;
        }
        if ((int)(before*10)!=(int)(progress*10))
            world.setBlockBreakingInfo(player.getId(),clickedPos,(int)(progress*10));
        player.blockBreakingProgress = Pair.of(clickedPos,progress);
    }

    public static boolean tryHarvestBlock(ExclusionZoneFakePlayer player, ServerPlayerInteractionManager interactionManager, BlockPos pos) {
        ServerWorld world = player.getServerWorld();
        BlockState blockstate = world.getBlockState(pos);
        GameMode gameMode = interactionManager.getGameMode();

        if (!PlayerBlockBreakEvents.BEFORE.invoker().beforeBlockBreak(world,player,pos,world.getBlockState(pos), world.getBlockEntity(pos))) return false;

        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (player.isBlockBreakingRestricted(world,pos,gameMode))
            return false;

        ItemStack prevHeldItem = player.getMainHandStack();
        ItemStack heldItem = prevHeldItem.copy();

        boolean canHarvest = player.canHarvest(blockstate) && player.canModifyBlocks();
        prevHeldItem.postMine(world,blockstate,pos,player);

        BlockPos posUp = pos.up();
        BlockState stateUp = world.getBlockState(posUp);
        if (blockstate.getBlock() instanceof TallPlantBlock && blockstate.get(DOUBLE_BLOCK_HALF) == DoubleBlockHalf.LOWER && stateUp.getBlock() == blockstate.getBlock() && stateUp.get(DOUBLE_BLOCK_HALF) == DoubleBlockHalf.UPPER) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
            world.setBlockState(posUp, Blocks.AIR.getDefaultState());
        } else {
            blockstate.getBlock().onBreak(world,pos,blockstate,player);
            if (!world.setBlockState(pos,world.getFluidState(pos).getFluid().getDefaultState().getBlockState())) return true;
        }

        blockstate.onBlockBreakStart(world,pos,player);
        if (!canHarvest) return true;

        Block.getDroppedStacks(blockstate,world,pos,blockEntity,player,prevHeldItem).forEach(player::dropStack);
        blockstate.getBlock().afterBreak(world,player,pos,blockstate,blockEntity,prevHeldItem);
        return true;
    }

    public BlockBreakerBE(BlockPos pos, BlockState state) {
        this(ModBlocks.BLOCK_BREAKER_BE, pos, state);
    }
}
