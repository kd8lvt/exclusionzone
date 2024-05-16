package com.kd8lvt.exclusionzone.init.StatusEffects;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class MilkStatusEffect extends StatusEffect {

    public MilkStatusEffect() {
        super(StatusEffectCategory.BENEFICIAL,0xd1d5d0);
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void applyInstantEffect(@Nullable Entity source, @Nullable Entity attacker, LivingEntity target, int amplifier, double proximity) {
        Items.MILK_BUCKET.finishUsing(new ItemStack(Items.MILK_BUCKET),target.getWorld(),target);
    }
}
