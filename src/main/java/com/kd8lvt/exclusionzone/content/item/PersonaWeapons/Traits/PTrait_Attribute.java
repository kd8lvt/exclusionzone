package com.kd8lvt.exclusionzone.content.item.PersonaWeapons.Traits;

import net.minecraft.component.ComponentChanges;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

public class PTrait_Attribute extends PTrait {
    public final EntityAttributeModifier modifier;
    public final RegistryEntry<EntityAttribute> attribute;
    public final AttributeModifierSlot slot;
    public PTrait_Attribute(RegistryEntry<EntityAttribute> attr, EntityAttributeModifier mod, AttributeModifierSlot slot) {
        this.modifier = mod;
        this.attribute = attr;
        this.slot = slot;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        //Just so we're all aware:
        //This was SO PAINFUL. For NO REASON.
        super.inventoryTick(stack, world, entity, slot, selected);
        if (Objects.requireNonNull(stack.getComponents().get(DataComponentTypes.ATTRIBUTE_MODIFIERS)).modifiers().contains(new AttributeModifiersComponent.Entry(this.attribute,this.modifier,this.slot))) return;
        ArrayList<AttributeModifiersComponent.Entry> entries = new ArrayList<>(Objects.requireNonNull(stack.get(DataComponentTypes.ATTRIBUTE_MODIFIERS)).modifiers());
        entries.add(new AttributeModifiersComponent.Entry(this.attribute,this.modifier,this.slot));
        ComponentChanges changes = ComponentChanges.builder().add(DataComponentTypes.ATTRIBUTE_MODIFIERS,new AttributeModifiersComponent(entries,true)).build();
        stack.applyChanges(changes);
    }
}
