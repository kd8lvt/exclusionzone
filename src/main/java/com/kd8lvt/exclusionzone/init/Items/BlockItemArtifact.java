package com.kd8lvt.exclusionzone.init.Items;

import net.minecraft.block.Block;
import net.minecraft.client.item.TooltipType;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BlockItemArtifact extends AliasedBlockItem {
    public List<Text> tt = new ArrayList<>();
    public BlockItemArtifact(Block block) {
        super(block,new Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.addAll(this.tt);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
