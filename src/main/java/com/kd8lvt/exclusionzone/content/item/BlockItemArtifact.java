package com.kd8lvt.exclusionzone.content.item;

import com.kd8lvt.exclusionzone.content.item.base.IHasResearchNotes;
import net.minecraft.block.Block;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.kd8lvt.exclusionzone.registry.ModItems.ttStyle;

public class BlockItemArtifact extends AliasedBlockItem implements IHasResearchNotes {
    public final List<Text> tt = new ArrayList<>();
    public BlockItemArtifact(Block block) {
        super(block,new Settings());
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ArrayList<Text> notes = new ArrayList<>(Text.stringifiedTranslatable("tooltips.exclusionzone.research_notes.header").getWithStyle(ttStyle));
        for (int i=0;i<this.getTooltips().size();i++) {
            notes.addAll(Text.translatable(getTranslationKey()+".research_notes_"+i).getWithStyle(ttStyle));
        }
        tooltip.addAll(notes);
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public List<Text> getTooltips() {
        return tt;
    }
}
