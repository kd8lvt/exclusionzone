package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.content.attribute.DynamicEntityAttribute;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.item.ArmorItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import static com.kd8lvt.exclusionzone.ExclusionZone.id;

public class ModAttributes {
    public static final Identifier TOXIN_DAMAGE_ID = id("generic.toxin_damage");
    public static RegistryEntry<EntityAttribute> TOXIN_DAMAGE = register(TOXIN_DAMAGE_ID,new DynamicEntityAttribute(TOXIN_DAMAGE_ID.toTranslationKey("attribute")).setTracked(true));;
    public static final Identifier TOXIN_RESISTANCE_ID = id("generic.toxin_resistance");
    public static RegistryEntry<EntityAttribute> TOXIN_RESISTANCE = register(TOXIN_RESISTANCE_ID,new DynamicEntityAttribute(TOXIN_RESISTANCE_ID.toTranslationKey("attribute"),1).setTracked(true));;
    public static void register() {} //Incredibly annoying I can't do it the normal way

    private static <T extends EntityAttribute> RegistryEntry<EntityAttribute> register(Identifier id, T attribute) {
        return Registry.registerReference(Registries.ATTRIBUTE,id,attribute);
    }


    public static RegistryEntry<EntityAttribute> getEntry(String id) {return Registries.ATTRIBUTE.getEntry(Identifier.of(id)).orElseThrow();}
    public static EntityAttribute get(String id) {return getEntry(id).value();}

    public static final class MODIFIERS {
        public static Identifier forArmorResistance(ArmorItem.Type type) {
            return id("base_armor_resistance_"+type.getName());
        }
    }
}
