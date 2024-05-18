package com.kd8lvt.exclusionzone.init.Blocks.bases;

import com.kd8lvt.exclusionzone.init.Blocks.bases.entity.DispenserCloneBaseBE;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.apache.commons.lang3.NotImplementedException;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class DispenserCloneBase extends DispenserBlock implements BlockEntityProvider {
    Text displayName = Text.of("Dispenser Clone (You should never see this!)");
    Identifier id;
    public DispenserCloneBase(Identifier id, Text interfaceName) {
        super(Block.Settings.copy(Blocks.DISPENSER));
        displayName=interfaceName;
        this.id = id;
    }
    public DispenserCloneBase(Settings settings) {
        super(settings);
    }

    @Override
    public MutableText getName() {
        return Text.of(displayName).copy();
    }

    @Override
    public MapCodec<? extends DispenserBlock> getCodec() {
        return createCodec(DispenserCloneBase::new);
    }

    @Override
    protected DispenserBehavior getBehaviorForItem(World world, ItemStack stack) {
        return this::onDispense;
    }

    public BlockEntityType<? extends DispenserCloneBaseBE> getBEType() {
        throw new NotImplementedException();
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return getBEType().instantiate(pos,state);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        DispenserCloneBaseBE blockEntity = (DispenserCloneBaseBE) world.getBlockEntity(pos);
        BlockPointer pointer = new BlockPointer(world,pos,state,blockEntity);
        assert blockEntity != null;
        int slot = blockEntity.chooseNonEmptySlot(random);
        ItemStack stack = ItemStack.EMPTY;
        if (slot > -1) stack = blockEntity.getStack(slot);
        DispenserBehavior behavior = getBehaviorForItem(world,stack);
        ItemStack returnValue = behavior.dispense(pointer,stack);
        if (slot > -1) blockEntity.setStack(slot,returnValue);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof DispenserCloneBaseBE) {
            player.openHandledScreen(blockEntity.getCachedState().createScreenHandlerFactory(world,pos));
        }
        return ActionResult.CONSUME;
    }

    public ItemStack onDispense(BlockPointer pointer, ItemStack stack1) {
        throw new NotImplementedException();
    }
}
