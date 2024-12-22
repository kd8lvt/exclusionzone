package com.kd8lvt.exclusionzone.datagen.enchantment;

import com.kd8lvt.exclusionzone.registry.ModAttributes;
import com.kd8lvt.exclusionzone.registry.ModEnchantments;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;

import java.util.concurrent.CompletableFuture;

import static com.kd8lvt.exclusionzone.api.CommonConstants.MOD_ID;


public class EnchantmentGenerator extends FabricDynamicRegistryProvider {

    public EnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        RegistryWrapper<Item> itemLookup = registries.getWrapperOrThrow(RegistryKeys.ITEM);

        register(
                entries,
                ModEnchantments.KEYS.TOXICUS_PERDITIO,
                createAttributeEnchantment(
                        itemLookup,
                        ModEnchantments.KEYS.TOXICUS_PERDITIO,
                        ItemTags.WEAPON_ENCHANTABLE,
                        10,
                        5,
                        Enchantment.leveledCost(1,10), //base cost/level
                        Enchantment.leveledCost(1,15), //max cost/level)
                        7,
                        AttributeModifierSlot.forEquipmentSlot(EquipmentSlot.MAINHAND),
                        ModAttributes.TOXIN_DAMAGE,
                        EnchantmentLevelBasedValue.linear(20,20),
                        EntityAttributeModifier.Operation.ADD_VALUE
                )
        );

        register(
                entries,
                ModEnchantments.KEYS.TOXICAE_PRAESIDIUM,
                createAttributeEnchantment(
                        itemLookup,
                        ModEnchantments.KEYS.TOXICAE_PRAESIDIUM,
                        ItemTags.ARMOR_ENCHANTABLE,
                        10,
                        4,
                        Enchantment.leveledCost(1,10), //base cost/level
                        Enchantment.leveledCost(1,15), //max cost/level)
                        7,
                        AttributeModifierSlot.ARMOR,
                        ModAttributes.TOXIN_RESISTANCE,
                        EnchantmentLevelBasedValue.linear(0.1f,0.1f),
                        EntityAttributeModifier.Operation.ADD_VALUE
                )
        );
    }

    private static void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... conditions) {
        entries.add(key,builder.build(key.getValue()),conditions);
    }

    private static Enchantment.Builder createAttributeEnchantment(RegistryWrapper<Item> itemLookup, RegistryKey<Enchantment> registryKey, TagKey<Item> appliesToTag, int weight, int maxLevel, Enchantment.Cost baseLeveledCost, Enchantment.Cost maxLeveledCost, int anvilCost, AttributeModifierSlot modifierSlot, RegistryEntry<EntityAttribute> attrKey, EnchantmentLevelBasedValue toApplyPerLevel, EntityAttributeModifier.Operation operation) {
        return Enchantment.builder(
                Enchantment.definition(
                        itemLookup.getOrThrow(appliesToTag),
                        weight,
                        maxLevel,
                        baseLeveledCost,
                        maxLeveledCost,
                        anvilCost,
                        modifierSlot
                )
        ).addEffect(
                EnchantmentEffectComponentTypes.ATTRIBUTES,
                new AttributeEnchantmentEffect(
                        registryKey.getValue(),
                        attrKey,
                        toApplyPerLevel,
                        operation
                )
        );
    }

    @Override
    public String getName() {
        return MOD_ID+"/Enchantments";
    }
}
