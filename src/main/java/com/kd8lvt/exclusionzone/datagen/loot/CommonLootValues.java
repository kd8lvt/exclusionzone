package com.kd8lvt.exclusionzone.datagen.loot;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.condition.LootCondition;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.ConditionalLootFunction;
import net.minecraft.loot.function.LimitCountLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.operator.BoundedIntUnaryOperator;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.LootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.state.property.Property;

@SuppressWarnings({"unused","unchecked"})
public final class CommonLootValues {
    public static final RegistryKey<Registry<LootTable>> LOOT_TABLE_REGISTRY = LootTables.ABANDONED_MINESHAFT_CHEST.getRegistryRef();

    public static RegistryKey<LootTable> key(String id) {
        return RegistryKey.of(LOOT_TABLE_REGISTRY, ExclusionZone.id(id));
    }

    public static abstract class Numbers {
        public static LootNumberProvider ONE = constant(1);
        public static LootNumberProvider ONE_OR_TWO = between(1,2);

        public static LootNumberProvider constant(float num) {
            return ConstantLootNumberProvider.create(num);
        }

        public static LootNumberProvider between(float min, float max) {
            return UniformLootNumberProvider.create(min,max);
        }
    }

    public static abstract class Conditions {
        public static final LootCondition.Builder HALF_CHANCE = random(0.5f);
        public static final LootCondition.Builder QUARTER_CHANCE = random(0.25f);
        public static final LootCondition.Builder ONE_THIRD_CHANCE = random(0.333f);
        public static LootCondition.Builder random(float chance) {
            return RandomChanceLootCondition.builder(chance);
        }
        public static LootCondition.Builder fullyGrown(CropBlock block) {
            return BlockStatePropertyLootCondition.builder(block).properties(StatePredicate.Builder.create().exactMatch((Property<Integer>)block.getStateManager().getProperty("age"),block.getMaxAge()));
        }
    }

    public static abstract class Functions {
        public static ConditionalLootFunction.Builder<?> setCount(LootNumberProvider amt) {
            return SetCountLootFunction.builder(amt);
        }
        public static ConditionalLootFunction.Builder<?> setCount(float amt) {
            return setCount(Numbers.constant(amt));
        }
        public static ConditionalLootFunction.Builder<?> limitCount(int min, int max) {
            return LimitCountLootFunction.builder(BoundedIntUnaryOperator.create(min,max));
        }
    }

    public static abstract class Entries {
        public static <T extends ItemConvertible> ItemEntry.Builder<?> of(T item) {
            return ItemEntry.builder(item);
        }
        public static <T extends ItemConvertible> ItemEntry.Builder<?> halfChanceOf(T item) {
            return of(item).conditionally(Conditions.HALF_CHANCE);
        }
    }
}
