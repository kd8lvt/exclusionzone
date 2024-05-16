package com.kd8lvt.exclusionzone.init.Items;

import com.kd8lvt.exclusionzone.init.ModSounds;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class Doll extends Artifact {
    SoundEvent defaultSound;
    SoundEvent memeSound;
    double memeChance;
    String memeName = null;
    public Doll(SoundEvent defaultSound, SoundEvent memeSound, double memeChance, String memeName) {
        super();
        this.defaultSound = defaultSound;
        this.memeSound = memeSound;
        this.memeChance = memeChance;
        this.memeName = memeName;
    }

    public Doll(SoundEvent defaultSound, SoundEvent memeSound, double memeChance) {
        super();
        this.defaultSound = defaultSound;
        this.memeSound = memeSound;
        this.memeChance = memeChance;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        if (this.memeName != null && player.getStackInHand(hand).getName().getString().equalsIgnoreCase(this.memeName)) {
            if (!world.isClient)
                world.playSound(null,player.getBlockPos(), this.memeSound, SoundCategory.PLAYERS, 1f, 1.0f);

            return TypedActionResult.success(player.getStackInHand(hand),true);
        }

        if (Math.random() < this.memeChance) {
            if (!world.isClient)
                world.playSound(null,player.getBlockPos(), this.memeSound, SoundCategory.PLAYERS, 1f, 1.0f);
        } else {
            if (!world.isClient)
                world.playSound(null,player.getBlockPos(), this.defaultSound, SoundCategory.PLAYERS, 1f, ModSounds.randPitch());
        }
        return TypedActionResult.success(player.getStackInHand(hand),true);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        if (this.memeName != null && stack.getName().getString().equalsIgnoreCase(this.memeName)) {
            return true;
        } else {
            return super.hasGlint(stack);
        }
    }
}
