package com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits;

import com.kd8lvt.exclusionzone.init.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class PTraitKillFocused extends PTraitPotionEffect {
    public PTraitKillFocused() {
        this.tt = new ArrayList<>();
        this.doesAmplifierStack=true;
        this.doesTimeStack=true;
        this.status = ModStatusEffects.KILL_FOCUS;
        tt.add(Text.of("Kill-Focused:"));
        tt.add(Text.of("This weapon instills a sense of focus into"));
        tt.add(Text.of("the wielder when they strike down their opponent."));
    }

    @Override
    public void onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getHealth() > 0) return;
        this.applyStatus(attacker,5*20,1);
    }
}
