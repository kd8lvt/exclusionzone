package com.kd8lvt.exclusionzone.init.blocks.bases.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.DispenserBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;

@SuppressWarnings("unused")
public class DispenserCloneBaseBE extends DispenserBlockEntity {
    public final int size = 9;
    public Text displayName;
    protected DefaultedList<ItemStack> inventory = DefaultedList.ofSize(size, ItemStack.EMPTY);
    protected DispenserCloneBaseBE(BlockEntityType<?> blockEntityType, BlockPos blockPos, BlockState blockState) {
        super(blockEntityType, blockPos, blockState);
    }

    public DispenserCloneBaseBE(BlockPos pos, BlockState state) {
        super(pos, state);
    }

    public void setDisplayName(String dName) {displayName = Text.of(dName);}

    @Override
    public int size() {
        return size;
    }

    @Override
    protected Text getContainerName() {
        return displayName;
    }
}
