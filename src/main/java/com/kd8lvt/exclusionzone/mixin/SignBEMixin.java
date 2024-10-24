package com.kd8lvt.exclusionzone.mixin;

import com.kd8lvt.exclusionzone.registry.ModStatusEffects;
import net.minecraft.block.entity.SignBlockEntity;
import net.minecraft.block.entity.SignText;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.filter.FilteredMessage;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Mixin(SignBlockEntity.class)
public class SignBEMixin {
    @Inject(at=@At("RETURN"),method= "getTextWithMessages(Lnet/minecraft/entity/player/PlayerEntity;Ljava/util/List;Lnet/minecraft/block/entity/SignText;)Lnet/minecraft/block/entity/SignText;",cancellable = true)
    void exclusionzone$getTextWithMessages(PlayerEntity player, List<FilteredMessage> messages, SignText text, CallbackInfoReturnable<SignText> cir) {
        if (!(Date.from(Instant.now()).getMonth() == Calendar.APRIL && Date.from(Instant.now()).getDay() == 1)) return;
        SignText newText = new SignText();
        if (player.hasStatusEffect(ModStatusEffects.getEntry("milk"))) {
            for (int i=0; i < messages.size(); i++) {
                ArrayList<String> words = new ArrayList<>(List.of(text.getMessage(i,false).getString().split(" ")));
                for (int j=0;j<words.size();j++) {
                    int len = words.get(j).length();
                    if (len % 3 == 0) {
                        words.set(j,"moo".repeat(len/3));
                    } else {
                        words.set(j,"moo".repeat(len/3)+"moo".substring(0,4-(len%3)));
                    }
                }
                newText = newText.withMessage(i, Text.of(String.join(" ",words)));
            }
            cir.setReturnValue(newText);
            cir.cancel();
        }
    }
}
