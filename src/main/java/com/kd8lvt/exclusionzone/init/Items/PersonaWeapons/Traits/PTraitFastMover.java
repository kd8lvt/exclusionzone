package com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.ArrayList;

public class PTraitFastMover extends PTrait {
    public PTraitFastMover() {
        tt.add(Text.of("Fast Mover:"));
        tt.add(Text.of("This weapon imbues its wielder with a sense of urgency,"));
        tt.add(Text.of("increasing their movement speed passively."));
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        AttributeModifiersComponent comp = stack.getComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS);
        ArrayList<AttributeModifiersComponent.Entry> attrs = new ArrayList<>();
        attrs.add(new AttributeModifiersComponent.Entry(EntityAttributes.GENERIC_MOVEMENT_SPEED,new EntityAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED.getKey().get().getValue(),1.25, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL), AttributeModifierSlot.HAND));

        if (comp != null && comp.modifiers().stream().anyMatch(entry-> entry.modifier().id().equals(EntityAttributes.GENERIC_MOVEMENT_SPEED.getKey().get().getValue()))) comp.modifiers().addAll(attrs);
        if (comp == null) comp = new AttributeModifiersComponent(attrs,false);
        stack.set(DataComponentTypes.ATTRIBUTE_MODIFIERS,comp);
    }
}
