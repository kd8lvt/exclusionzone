package com.kd8lvt.exclusionzone.item;

import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.kd8lvt.exclusionzone.registry.ModItems.ttStyle;

public class BlockItemArtifact extends AliasedBlockItem {
    public final List<Text> tt = new ArrayList<>();
    public BlockItemArtifact(Block block) {
        super(block,new net.minecraft.item.Item.Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ArrayList<Text> notes = new ArrayList<>(Text.stringifiedTranslatable("tooltips.exclusionzone.research_notes.header").getWithStyle(ttStyle));
        for (int i=0;i<this.tt.size();i++) {
            notes.addAll(Text.translatable(getTranslationKey()+".research_notes_"+i).getWithStyle(ttStyle));
        }
        tooltip.addAll(notes);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
