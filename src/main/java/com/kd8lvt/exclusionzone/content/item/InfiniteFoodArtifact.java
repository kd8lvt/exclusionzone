package com.kd8lvt.exclusionzone.content.item;

import com.kd8lvt.exclusionzone.api.trackers.ToxicBuildupTracker;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

import java.util.List;

public class InfiniteFoodArtifact extends EdibleArtifact {
    final Float toxicBuildupWhenEaten = 600f; //In ticks
    public InfiniteFoodArtifact() {
        super(new FoodComponent.Builder().nutrition(8).saturationModifier(12.8f).alwaysEdible().build());
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        return stack;
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        FoodComponent component = stack.get(DataComponentTypes.FOOD);
        if (component != null) {
            world.playSound(null, user.getX(), user.getY(), user.getZ(), user.getEatSound(stack), SoundCategory.NEUTRAL, 1.0f, 1.0f + (world.random.nextFloat() - world.random.nextFloat()) * 0.4f);
            if (world.isClient()) {
                return stack;
            }

            if (user instanceof PlayerEntity) {
                ((PlayerEntity) user).getHungerManager().eat(stack.get(DataComponentTypes.FOOD));
                ((PlayerEntity) user).incrementStat(Stats.USED.getOrCreateStat(stack.getItem()));
                world.playSound(null, user.getX(), user.getY(), user.getZ(), SoundEvents.ENTITY_PLAYER_BURP, SoundCategory.PLAYERS, 0.5f, world.random.nextFloat() * 0.1f + 0.9f);
                if (user instanceof ServerPlayerEntity) {
                    Criteria.CONSUME_ITEM.trigger((ServerPlayerEntity)user, stack);
                }
            }

            List<FoodComponent.StatusEffectEntry> list = component.effects();
            for (FoodComponent.StatusEffectEntry statusEffectEntry : list) {
                if (!(world.random.nextFloat() < statusEffectEntry.probability())) continue;
                user.addStatusEffect(statusEffectEntry.effect());
            }

            user.emitGameEvent(GameEvent.EAT);
            if (user instanceof PlayerEntity) {
                ((PlayerEntity)user).getItemCooldownManager().set(this,100);
                this.applyToxicBuildup(user,world);
            }
        }
        return stack;
    }

    public void applyToxicBuildup(LivingEntity entity,World world) {
        if (world.isClient) return;
        if (entity instanceof ServerPlayerEntity) ToxicBuildupTracker.incrementBuildup(entity,this.toxicBuildupWhenEaten);
    }
}
