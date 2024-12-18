package com.kd8lvt.exclusionzone.content.item;

import com.kd8lvt.exclusionzone.content.item.base.IHasResearchNotes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class Artifact extends Item implements IHasResearchNotes {
    public final List<Text> tt = new ArrayList<>();
    public Artifact() {
        this(new Settings());
    }
    public Artifact(Settings settings) {super(settings);}

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        super.appendTooltip(stack, context, IHasResearchNotes.calculateTooltip(tt, stack, context, tooltip, type), type);
    }

    @Override
    public List<Text> getTooltips() {
        return tt;
    }
}
