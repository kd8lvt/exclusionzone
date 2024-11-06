package com.kd8lvt.exclusionzone.mixin;

import com.kd8lvt.exclusionzone.registry.ModAttributes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.registry.entry.RegistryEntry;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;
import java.util.function.Supplier;

@Mixin(ArmorItem.class)
public abstract class ArmorItemMixin {
    //I highly doubt this actually needs to be a mixin. This is just cleaner than my original solution.
    @Inject(at=@At(value = "RETURN"),method="getAttributeModifiers",cancellable = true)
    private void getAttributeModifiers(CallbackInfoReturnable<AttributeModifiersComponent> cir) {
        AttributeModifiersComponent comp = cir.getReturnValue();
        if (comp == AttributeModifiersComponent.DEFAULT) return;
        Double toxRes = getToxicResistanceForItem();
        if (toxRes.isNaN()) return;
        cir.setReturnValue(cir.getReturnValue().with(
                ModAttributes.TOXIN_RESISTANCE,
                new EntityAttributeModifier(
                        ModAttributes.MODIFIERS.forArmorResistance(type),
                        toxRes,
                        EntityAttributeModifier.Operation.ADD_MULTIPLIED_BASE
                ),
                AttributeModifierSlot.forEquipmentSlot(this.getSlotType())
            )
        );
    }

    /*
        Programmatically generate armor's toxin resistance values.
        Not guarantied to function with negative values, and outputs NaN if it doesn't like something about the input.

        Interactive Desmos graph for the output "curve": https://www.desmos.com/calculator/wqi6fd5poc
     */
    @Unique
    private static final Map<ArmorItem.Type,Double> baseValues = Map.of(
        ArmorItem.Type.HELMET, 1.0,
        ArmorItem.Type.CHESTPLATE, 3.0,
        ArmorItem.Type.LEGGINGS, 2.0,
        ArmorItem.Type.BOOTS, 1.0
    );
    @Unique
    private static final Map<ArmorItem.Type,Double> scalingFactors = Map.of(
            ArmorItem.Type.HELMET, 0.15,
            ArmorItem.Type.CHESTPLATE, 0.5,
            ArmorItem.Type.LEGGINGS, 0.25,
            ArmorItem.Type.BOOTS, 0.1
    );
    @Unique
    private Double getToxicResistanceForItem() {
        //Animal armor
        if (this.type == ArmorItem.Type.BODY) return 1.0;

        //Player armor
        Integer def = material.value().defense().get(type);
        Double base = baseValues.get(type);
        Double scale = scalingFactors.get(type);
        if (def == null || scale == null) return Double.NaN;
        return (base/def)*scale;
    }

    @Shadow @Final private Supplier<AttributeModifiersComponent> attributeModifiers;
    @Shadow public abstract EquipmentSlot getSlotType();
    @Shadow @Final protected RegistryEntry<ArmorMaterial> material;
    @Shadow @Final protected ArmorItem.Type type;
}

