package com.kd8lvt.exclusionzone.datagen.loot;

import com.kd8lvt.exclusionzone.registry.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class EntityLootProvider extends SimpleFabricLootTableProvider {
    public static final RegistryKey<LootTable> CARO_INVICTUS = CommonLootValues.key("entities/caro_invictus");
    public EntityLootProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ENTITY);
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> biConsumer) {
        biConsumer.accept(CARO_INVICTUS,LootTable.builder()
                .pool(LootPool.builder().with(ItemEntry.builder(ModItems.get("cito_sanitatem_caro"))).rolls(ConstantLootNumberProvider.create(1f))));
    }
}
