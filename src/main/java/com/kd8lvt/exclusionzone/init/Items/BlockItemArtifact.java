package com.kd8lvt.exclusionzone.init.Items;

import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BlockItemArtifact extends AliasedBlockItem {
    public List<Text> tt = new ArrayList<>();
    public BlockItemArtifact(Block block) {
        super(block,new net.minecraft.item.Item.Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.addAll(this.tt);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
