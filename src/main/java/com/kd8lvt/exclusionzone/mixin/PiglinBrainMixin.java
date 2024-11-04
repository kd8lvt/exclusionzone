package com.kd8lvt.exclusionzone.mixin;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.item.ItemStack;
import net.minecraft.item.trim.ArmorTrim;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.kd8lvt.exclusionzone.datagen.tag.ArmorTrimTagProvider.DISTRACTING_TRIM;

//This Mixin has been shamelessly yoinked from https://github.com/Darkhax-Minecraft/Distracting-Trims/blob/1.20.4/common/src/main/java/net/darkhax/distractingtrims/mixin/MixinPiglinAi.java
//I'll un-yoink it once darkhax updates his mod :P
@Mixin(PiglinBrain.class)
public class PiglinBrainMixin {
    @Inject(method="wearsGoldArmor",at=@At("HEAD"),cancellable = true)
    private static void wearsGoldArmor(LivingEntity entity, CallbackInfoReturnable<Boolean> cir) {
        for (ItemStack stack : entity.getArmorItems()) {
            if (stack.getComponents().contains(DataComponentTypes.TRIM)) {
                final ArmorTrim trim = stack.getComponents().get(DataComponentTypes.TRIM);
                if (trim != null && trim.getMaterial().isIn(DISTRACTING_TRIM)) cir.setReturnValue(true);
            }
        }
    }
}
