package com.kd8lvt.exclusionzone.init.Blocks.bases.entity;

import com.kd8lvt.exclusionzone.init.Blocks.util.ExclusionZoneFakePlayer;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

public class DispenserCloneBaseBE extends DispenserBlockEntity {
    public int size = 9;
    public Text displayName;
    public ExclusionZoneFakePlayer player;
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
    protected DispenserCloneBaseBE(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public boolean playerExists() {
        return player != null;
    }

    public DispenserCloneBaseBE(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public void setDisplayName(String dName) {displayName = Text.of(dName);}

    public void setupFakePlayer(ServerWorld world) {
        player = new ExclusionZoneFakePlayer(world);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Text getContainerName() {
        return displayName;
    }
}
