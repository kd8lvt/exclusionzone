package com.kd8lvt.exclusionzone.init.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

import java.util.ArrayList;
import java.util.List;

public class Artifact extends Item {
    public final List<Text> tt = new ArrayList<>();
    public Artifact() {
        super(new net.minecraft.item.Item.Settings());
        this.tt.addAll(Text.of("Research Notes:").getWithStyle(Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow())));
    }

    public Artifact(Item.Settings settings) {
        super(settings);
        this.tt.addAll(Text.of("Research Notes:").getWithStyle(Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow())));
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        tooltip.addAll(this.tt);
        super.appendTooltip(stack, context, tooltip, type);
    }
}
