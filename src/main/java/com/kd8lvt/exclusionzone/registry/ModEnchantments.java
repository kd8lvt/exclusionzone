package com.kd8lvt.exclusionzone.registry;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;

public class ModEnchantments {
    public static void register() {}
    public static RegistryEntry<Enchantment> getEntry(String id) {return ModRegistries.ENCHANTMENTS.get(id);}
    public static Enchantment get(String id) {return getEntry(id).value();}
}
