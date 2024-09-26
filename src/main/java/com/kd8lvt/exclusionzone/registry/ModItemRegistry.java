package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;

public class ModItemRegistry extends ModRegistry<Item> {
    public ModItemRegistry() {
        super(Registries.ITEM);
    }

    public RegistryEntry<Item> register(String id, BlockItem value) {
        return REGISTRY.getEntry(Registry.register(REGISTRY,ExclusionZone.id(id),value));
    }
}
