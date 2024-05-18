package com.kd8lvt.exclusionzone.init.Blocks.entity;

import com.kd8lvt.exclusionzone.init.Blocks.bases.entity.DispenserCloneBaseBE;
import com.kd8lvt.exclusionzone.init.Blocks.util.ExclusionZoneFakePlayer;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class BlockPlacerBE extends DispenserCloneBaseBE {
    public int size = 1;
    public ExclusionZoneFakePlayer player;
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
    public BlockPlacerBE(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
        setDisplayName("Interaction Simulator");
    }

    public BlockPlacerBE(BlockPos pos, BlockState state) {
        this(ModBlocks.BLOCK_PLACER_BE, pos, state);
    }
}
