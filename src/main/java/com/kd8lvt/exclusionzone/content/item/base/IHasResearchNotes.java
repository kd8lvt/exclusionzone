package com.kd8lvt.exclusionzone.content.item.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.kd8lvt.exclusionzone.registry.ModItems.ttStyle;

public interface IHasResearchNotes {
    @SuppressWarnings("unused")
    static List<Text> calculateTooltip(List<Text> tt, ItemStack stack, Item.TooltipContext context, List<Text> tooltip, TooltipType type) {
        ArrayList<Text> notes = new ArrayList<>(Text.translatable("tooltips.exclusionzone.research_notes.header").getWithStyle(ttStyle));
        for (int i=0;i<tt.size();i++) {
            notes.addAll(Text.stringifiedTranslatable(stack.getItem().getTranslationKey()+".research_notes_"+i).getWithStyle(ttStyle));
        }
        tooltip.addAll(notes);
        return tooltip;
    }
    List<Text> getTooltips();
}
