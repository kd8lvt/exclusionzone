package com.kd8lvt.exclusionzone.init.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

import static com.kd8lvt.exclusionzone.init.registries.ModItemRegistry.ttStyle;

public class Artifact extends Item {
    public final List<Text> tt = new ArrayList<>();
    public Artifact() {
        this(new net.minecraft.item.Item.Settings());
    }

    public Artifact(Item.Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        ArrayList<Text> notes = new ArrayList<>(Text.translatable("tooltips.exclusionzone.research_notes.header").getWithStyle(ttStyle));
        for (int i=0;i<this.tt.size();i++) {
            notes.addAll(Text.stringifiedTranslatable(getTranslationKey()+".research_notes_"+i).getWithStyle(ttStyle));
        }
        tooltip.addAll(notes);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
