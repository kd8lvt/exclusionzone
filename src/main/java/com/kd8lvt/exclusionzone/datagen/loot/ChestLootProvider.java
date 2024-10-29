package com.kd8lvt.exclusionzone.datagen.loot;

import com.kd8lvt.exclusionzone.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;


public class ChestLootProvider extends SimpleFabricLootTableProvider {
    public static final RegistryKey<LootTable> SMALL_MONOLITH_CHEST = CommonLootValues.key("chests/small_monolith_chest");
    public ChestLootProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.CHEST);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> biConsumer) {
        biConsumer.accept(SMALL_MONOLITH_CHEST,
            LootTable.builder().pool(
                LootPool.builder()
                .rolls(UniformLootNumberProvider.create(10,20))
                .with(
                    CommonLootValues.Entries.halfChanceOf(Items.MOSS_BLOCK)
                    .weight(20)
                    .apply(
                        CommonLootValues.Functions.setCount(
                            CommonLootValues.Numbers.between(1,2)
                        ).conditionally(
                            CommonLootValues.Conditions.HALF_CHANCE
                        )
                    )
                    .apply(CommonLootValues.Functions.limitCount(1,2))
                )
                .with(
                    CommonLootValues.Entries.halfChanceOf(ModItems.get("amber_block"))
                    .weight(10)
                    .apply(
                        CommonLootValues.Functions.setCount(CommonLootValues.Numbers.between(1,3))
                        .conditionally(CommonLootValues.Conditions.HALF_CHANCE)
                    ).apply(
                        CommonLootValues.Functions.limitCount(1,4)
                    )
                )
                .with(CommonLootValues.Entries.halfChanceOf(Items.IRON_INGOT))
                .with(
                    CommonLootValues.Entries.of(Items.ENDER_PEARL).conditionally(
                        CommonLootValues.Conditions.random(0.1f)
                    )
                )
            )
        );
    }
}
