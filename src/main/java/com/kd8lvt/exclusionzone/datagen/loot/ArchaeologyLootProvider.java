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
import net.minecraft.util.Pair;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class ArchaeologyLootProvider extends SimpleFabricLootTableProvider {
    public static final RegistryKey<LootTable> MOSS_LOOT = CommonLootValues.key("archaeology/wild_moss");
    private static final ArrayList<Pair<String,Integer>> MOSS_LOOT_ITEMS = new ArrayList<>();
    public ArchaeologyLootProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup, LootContextTypes.ARCHAEOLOGY);
        MOSS_LOOT_ITEMS.add(new Pair<>("otherworldly_bone",25));
        MOSS_LOOT_ITEMS.add(new Pair<>("mysterious_chunk",25));
        MOSS_LOOT_ITEMS.add(new Pair<>("warped_meat",15));
        MOSS_LOOT_ITEMS.add(new Pair<>("quickmetal",20));
        MOSS_LOOT_ITEMS.add(new Pair<>("odd_seed",20));
        MOSS_LOOT_ITEMS.add(new Pair<>("chipped_carapace",20));
        MOSS_LOOT_ITEMS.add(new Pair<>("hunk_of_amber",20));
    }

    @Override
    public void accept(BiConsumer<RegistryKey<LootTable>, LootTable.Builder> biConsumer) {
        LootPool.Builder mossPoolBuilder = LootPool.builder().rolls(ConstantLootNumberProvider.create(1f));
        for (Pair<String,Integer> pair : MOSS_LOOT_ITEMS) {
            mossPoolBuilder.with(ItemEntry.builder(ModItems.get(pair.getLeft())).weight(pair.getRight()));
        }
        biConsumer.accept(MOSS_LOOT,LootTable.builder().pool(mossPoolBuilder));
    }
}
