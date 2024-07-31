package com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.text.Text;

public class PTraitFastMover extends PTrait_Attribute {
    public PTraitFastMover() {
        super(EntityAttributes.GENERIC_MOVEMENT_SPEED,new EntityAttributeModifier(ExclusionZone.id("ptrait_fastmover"),0.25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.MAINHAND);
        tt.add(Text.of("Fast Mover:"));
        tt.add(Text.of("This weapon imbues its wielder with a sense of urgency,"));
        tt.add(Text.of("increasing their movement speed passively."));
    }
}
