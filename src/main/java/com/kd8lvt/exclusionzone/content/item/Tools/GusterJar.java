package com.kd8lvt.exclusionzone.content.item.Tools;

import com.kd8lvt.exclusionzone.api.helpers.GusterHelper;
import com.kd8lvt.exclusionzone.content.item.base.IHasResearchNotes;
import com.kd8lvt.exclusionzone.registry.ModDataComponents;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.ColorHelper;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.apache.commons.lang3.math.Fraction;

import java.util.List;

public class GusterJar extends Item implements IHasResearchNotes {
    List<Text> tt = List.of(Text.literal("A terracotta jar filled with wind energy harvested from Breezes."),Text.literal("Usable in combination with an Elytra to provide a safer boost than using firework rockets."),Text.literal("It eventually runs out of charge, but can be refreshed by absorbing a Breeze."));
    @SuppressWarnings("unchecked")
    public static final ComponentType<Float> STORED_ENERGY = (ComponentType<Float>) ModDataComponents.get("stored_energy");
    public GusterJar() {
        super(new Settings().component(STORED_ENERGY,1f));
    }

    @Override
    public List<Text> getTooltips() {
        return tt;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof BreezeEntity breeze && stack.getOrDefault(STORED_ENERGY,1f) <= 0) {
            user.getWorld().addParticle(ParticleTypes.GUST,breeze.getX(),breeze.getY()+(breeze.getEyeY()/2),breeze.getZ(),0d,0d,0d);
            breeze.discard();
            user.playSound(SoundEvents.ENTITY_CHICKEN_EGG);
            user.playSound(SoundEvents.ENTITY_BREEZE_DEATH,0.5f,1f);
            user.playSound(SoundEvents.ENTITY_BREEZE_INHALE,0.5f,1f);
            stack.set(STORED_ENERGY,1f);
            user.setStackInHand(hand,stack);
            return ActionResult.SUCCESS;
        }
        if (stack.getOrDefault(STORED_ENERGY,1f) > 0 && entity.isPushable()){
            user.playSound(SoundEvents.ENTITY_BREEZE_JUMP);
            entity.takeKnockback(0.5, user.getX() - entity.getX(), user.getZ() - entity.getZ());
            useEnergy(stack);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (stack.get(STORED_ENERGY) <= 0) {
            user.playSound(SoundEvents.BLOCK_DECORATED_POT_INSERT_FAIL,1f,0.5f);
            return TypedActionResult.pass(stack);
        }
        if (user.isFallFlying()) {
            if (!world.isClient) {
                useEnergy(stack);
                GusterHelper.refreshTimer(user);
                user.incrementStat(Stats.USED.getOrCreateStat(this));
            }
            user.getWorld().addParticle(ParticleTypes.GUST,user.getX(),user.getY(),user.getZ(),0d,0d,0d);
            user.playSound(SoundEvents.ENTITY_BREEZE_JUMP);
            return TypedActionResult.success(stack, world.isClient());
        } else {
            return TypedActionResult.pass(stack);
        }
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        if (stack.get(STORED_ENERGY) == 1) return false;
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        if (stack.get(STORED_ENERGY) <= 0f) return 13;
        if (stack.get(STORED_ENERGY) >= 0.9f) return 13;
        return Math.min(1 + MathHelper.multiplyFraction(Fraction.getFraction(stack.get(STORED_ENERGY)), 12), 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        Float energy = stack.get(STORED_ENERGY);
        int empty = ColorHelper.Argb.getArgb(255,0,0);
        if (energy <= 0) return empty;
        int full = ColorHelper.Argb.getArgb(2,192,250);
        return ColorHelper.Argb.lerp(energy,empty,full);
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        IHasResearchNotes.calculateTooltip(tt,stack,context,tooltip,type);
    }

    @SuppressWarnings("DataFlowIssue")
    public static void useEnergy(ItemStack stack, Float amount) {
        stack.set(STORED_ENERGY,stack.get(STORED_ENERGY)-amount);
    }

    public static void useEnergy(ItemStack stack) {
        useEnergy(stack,0.01f);
    }
}