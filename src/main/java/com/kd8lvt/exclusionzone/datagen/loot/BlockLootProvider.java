package com.kd8lvt.exclusionzone.datagen.loot;

import com.kd8lvt.exclusionzone.block.Enderweed;
import com.kd8lvt.exclusionzone.registry.ModBlocks;
import com.kd8lvt.exclusionzone.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class BlockLootProvider extends FabricBlockLootTableProvider {
    public BlockLootProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.get("amber_block"), LootTable.builder().pool(LootPool.builder().rolls(UniformLootNumberProvider.create(4f,8f)).with(ItemEntry.builder(ModItems.get("hunk_of_amber")))));
        addDrop(ModBlocks.get("mining_simulator"));
        addDrop(ModBlocks.get("interaction_simulator"));
        addDrop(ModBlocks.get("muffler"));
        LootCondition.Builder enderweedGrown = CommonLootValues.Conditions.fullyGrown((Enderweed)ModBlocks.get("plant/enderweed"));
        addDrop(ModBlocks.get("plant/enderweed"),
                LootTable.builder()
                .pool(LootPool.builder()
                    .with(ItemEntry.builder(ModItems.get("odd_seed"))) //Always drop one odd seed
                    .rolls(CommonLootValues.Numbers.ONE))
                .pool(LootPool.builder() //if the block was fully grown, drop 1-4 ender pearls
                    .with(ItemEntry.builder(Items.ENDER_PEARL))
                    .rolls(CommonLootValues.Numbers.between(1,4))
                    .conditionally(enderweedGrown))
                .pool(LootPool.builder() //if the block was fully grown, there is a 5% chance to drop an additional seed
                    .with(ItemEntry.builder(ModItems.get("odd_seed")))
                        .apply(CommonLootValues.Functions.setCount(1))
                )
        );
    }
}
