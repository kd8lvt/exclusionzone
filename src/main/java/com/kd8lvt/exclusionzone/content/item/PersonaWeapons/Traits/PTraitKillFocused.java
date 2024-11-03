package com.kd8lvt.exclusionzone.content.item.PersonaWeapons.Traits;

import com.kd8lvt.exclusionzone.registry.ModStatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

import java.util.ArrayList;

public class PTraitKillFocused extends PTraitPotionEffect {
    public PTraitKillFocused() {
        super();
        this.tt = new ArrayList<>();
        this.doesAmplifierStack=true;
        this.doesTimeStack=true;
        tt.add(Text.of("Kill-Focused:"));
        tt.add(Text.of("This weapon instills a sense of focus into"));
        tt.add(Text.of("the wielder when they strike down their opponent."));
    }

    @Override
    public void onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target.getHealth() > 0) return;
        this.applyStatus(attacker,5*20,1);
    }

    @Override
    public StatusEffect getStatus() {
        //I'm aware this is stupid. It fixed a crash. Blame Java.
        return ModStatusEffects.get("kill_focus");
    }
}
