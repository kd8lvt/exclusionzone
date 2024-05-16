package com.kd8lvt.exclusionzone.init.Items.Tools;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipData;
import net.minecraft.client.item.TooltipType;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.LootTableEntry;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import javax.swing.text.AttributeSet;
import java.util.List;
import java.util.Optional;

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
        super(material, new Settings());
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
        return state.isIn(TagKey.of(Registries.BLOCK.getKey(),new Identifier("c","glass_blocks"))) || state.isIn(TagKey.of(Registries.BLOCK.getKey(),new Identifier("c","glass_panes")));
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        return isGlassBlock(state);
    }

    @Override
    public void onCraftByPlayer(ItemStack stack, World world, PlayerEntity player) {
        super.onCraftByPlayer(stack, world, player);
        stack.addEnchantment(Enchantments.SILK_TOUCH,1);
        stack.applyComponentsFrom((ComponentMap) stack.getEnchantments().withShowInTooltip(false));
    }

    @Override
    public void onCraft(ItemStack stack, World world) {
        super.onCraft(stack, world);
        stack.addEnchantment(Enchantments.SILK_TOUCH,1);
        stack.applyComponentsFrom((ComponentMap) stack.getEnchantments().withShowInTooltip(false));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (stack.hasEnchantments()) return;
        stack.addEnchantment(Enchantments.SILK_TOUCH,1);
        stack.applyComponentsFrom((ComponentMap) stack.getEnchantments().withShowInTooltip(false));
    }
}
