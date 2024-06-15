package com.kd8lvt.exclusionzone.init.Items.Tools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentMap;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;
import java.util.Objects;

public class Glasscutter extends ToolItem {
    public static final ToolMaterial material = new ToolMaterial() {
        @Override
        public int getDurability() {
            return 1024;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 999;
        }

        @Override
        public float getAttackDamage() {
            return 0;
        }

        @Override
        public TagKey<Block> getInverseTag() {
            return null;
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

    private boolean isGlassBlock(BlockState state) {
        return state.isIn(TagKey.of(Registries.BLOCK.getKey(),Identifier.of("c","glass_blocks"))) || state.isIn(TagKey.of(Registries.BLOCK.getKey(),Identifier.of("c","glass_panes")));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        return isGlassBlock(state);
    }

    @Override
    public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
        super.onCraftByPlayer(stack, world, player);
        //Super cursed, but I have no idea how to do this otherwise.
        stack.addEnchantment((RegistryEntry<Enchantment>) Objects.requireNonNull(Registries.REGISTRIES.get(Enchantments.SILK_TOUCH.getRegistry())).getEntry(Enchantments.SILK_TOUCH.getValue()).get(),1);
        stack.applyComponentsFrom((ComponentMap) stack.getEnchantments().withShowInTooltip(false));
    }

    @Override
    public void onCraft(ItemStack stack, World world) {
        super.onCraft(stack, world);
        stack.addEnchantment((RegistryEntry<Enchantment>) Objects.requireNonNull(Registries.REGISTRIES.get(Enchantments.SILK_TOUCH.getRegistry())).getEntry(Enchantments.SILK_TOUCH.getValue()).get(),1);
        stack.applyComponentsFrom((ComponentMap) stack.getEnchantments().withShowInTooltip(false));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (stack.hasEnchantments()) return;
        stack.addEnchantment((RegistryEntry<Enchantment>) Objects.requireNonNull(Registries.REGISTRIES.get(Enchantments.SILK_TOUCH.getRegistry())).getEntry(Enchantments.SILK_TOUCH.getValue()).get(),1);
        stack.applyComponentsFrom((ComponentMap) stack.getEnchantments().withShowInTooltip(false));
    }
}
