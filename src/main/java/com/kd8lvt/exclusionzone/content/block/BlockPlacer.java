package com.kd8lvt.exclusionzone.content.block;

import com.kd8lvt.exclusionzone.content.block.bases.DispenserCloneBase;
import com.kd8lvt.exclusionzone.content.block.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.content.entity.BlockPlacerBE;
import com.kd8lvt.exclusionzone.content.block.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.Direction;
import net.minecraft.world.GameMode;
import net.minecraft.world.RaycastContext;

public class BlockPlacer extends DispenserCloneBase {
    public BlockPlacer() {
        super(Identifier.of("exclusionzone","interaction_simulator"), Text.of("Interaction Simulator"));
    }

    @SuppressWarnings("unchecked")
    @Override
    public BlockEntityType<? extends DispenserCloneBaseBE> getBEType() {
        return (BlockEntityType<? extends DispenserCloneBaseBE>) ModBlockEntities.get("interaction_simulator");
    }

    @Override
    public ItemStack onDispense(BlockPointer pointer, ItemStack stack1) {
        BlockPlacerBE be = (BlockPlacerBE) pointer.blockEntity();
        if (be.player == null) be.player = new ExclusionZoneFakePlayer(pointer.world());
        be.player.setSneaking(pointer.world().getReceivedRedstonePower(pointer.pos()) < 7);
        ItemStack stack = stack1.copy();
        be.player.changeGameMode(GameMode.SURVIVAL);
        ServerWorld world = pointer.world();
        BlockHitResult bh = be.player.getServerWorld().raycast(new RaycastContext(pointer.centerPos().offset(pointer.state().get(FACING),1),pointer.centerPos().offset(pointer.state().get(FACING),1).offset(Direction.DOWN,1), RaycastContext.ShapeType.VISUAL, RaycastContext.FluidHandling.NONE,be.player));
        ActionResult ar = be.player.interactionManager.interactBlock(be.player,world,stack,Hand.MAIN_HAND,bh);
        if (ar.isAccepted() && ar.compareTo(ActionResult.CONSUME) == 0) stack.decrement(1);
        return stack;
    }
}
