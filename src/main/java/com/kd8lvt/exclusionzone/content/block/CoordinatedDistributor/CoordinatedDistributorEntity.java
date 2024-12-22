package com.kd8lvt.exclusionzone.content.block.CoordinatedDistributor;

import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;

public class CoordinatedDistributorEntity extends ChestBlockEntity {
    HashMap<BlockPos, DefaultedList<ItemStack>> inventoryCache = new HashMap<>();
    public CoordinatedDistributorEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.get("coordinated_distributor"), pos, state);
    }

    public <T extends BlockEntity> void tick(World world, BlockPos pos, BlockState state, BlockEntityType<T> blockEntity) {
        /*if (world.getReceivedRedstonePower(pos) > 0) return;
        for (ItemStack item : blockEntity.getHeldStacks()) {
            if (item == ItemStack.EMPTY) continue;
            BlockPos foundInventory = null;
            for (Map.Entry<BlockPos,DefaultedList<ItemStack>> cachedInventory : inventoryCache.entrySet()) {
                if (cachedInventory.getValue().stream().anyMatch(stack->{
                    return stack.streamTags().anyMatch(item::isIn) || stack.getItem() == item.getItem();
                })) {
                    if (
                        world.getBlockEntity(cachedInventory.getKey()) == null
                        || !(world.getBlockEntity(cachedInventory.getKey()) instanceof Inventory)
                        || world.getBlockEntity(cachedInventory.getKey())
                    ) continue;
                    foundInventory = cachedInventory.getKey();
                    break;
                }
            }

        }*/
    }
}
