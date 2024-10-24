package com.kd8lvt.exclusionzone.item.Tools;

import net.fabricmc.fabric.api.item.v1.EnchantingContext;
import net.minecraft.block.Block;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.List;

public class Glasscutter extends ToolItem {
    public static final ToolMaterial material = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 1024;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 2;
        }

        @Override
        public float getAttackDamage() {
            return 0;
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return BlockTags.INCORRECT_FOR_IRON_TOOL;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.ofItems(Items.AMETHYST_SHARD);
        }
    };
    public Glasscutter() {
        super(material, new net.minecraft.item.Item.Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.addAll(Text.of("An amethyst-tipped blade designed to gently remove material.").getWithStyle(Style.EMPTY.withColor(Colors.GRAY).withItalic(true)));
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return false;
    }
    @Override
    public boolean canBeEnchantedWith(ItemStack stack, RegistryEntry<Enchantment> enchantment, EnchantingContext context) {
        return new ArrayList<RegistryKey<Enchantment>>(){{
            add(Enchantments.MENDING);
            add(Enchantments.SILK_TOUCH);
            add(Enchantments.UNBREAKING);
        }}.contains(enchantment.getKey().get());
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (stack.hasEnchantments()) return;
        stack.addEnchantment(world.getRegistryManager().get(Enchantments.SILK_TOUCH.getRegistryRef()).entryOf(Enchantments.SILK_TOUCH),1);
    }

    @Override
    public boolean isEnchantable(ItemStack stack) {
        return !stack.hasEnchantments();
    }
}
