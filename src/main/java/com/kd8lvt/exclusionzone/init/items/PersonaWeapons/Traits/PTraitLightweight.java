package com.kd8lvt.exclusionzone.init.items.PersonaWeapons.Traits;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.text.Text;

public class PTraitLightweight extends PTrait_Attribute {
    public PTraitLightweight() {
        super(EntityAttributes.GENERIC_ATTACK_SPEED, new EntityAttributeModifier(ExclusionZone.id("ptrait_lightweight"),0.5, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND);
        tt.add(Text.of("Lightweight:"));
        tt.add(Text.of("This weapon is exceptionally lightweight,"));
        tt.add(Text.of("making it considerably easier to swing around."));
    }
}
