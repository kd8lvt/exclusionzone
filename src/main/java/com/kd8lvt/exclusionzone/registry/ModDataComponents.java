package com.kd8lvt.exclusionzone.registry;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModDataComponents {
    static void register() {
        ModRegistries.register("magnet_enabled", new ComponentType.Builder<Boolean>().codec(Codec.BOOL).build());
        ModRegistries.register("persona_weapon_traits", new ComponentType.Builder<List<Identifier>>().codec(Codec.list(Codec.stringResolver(Identifier::toString, Identifier::of))).build());
    }

    public static RegistryEntry<ComponentType<?>> getEntry(String id) {return ModRegistries.COMPONENT_TYPES.get(id);}
    public static ComponentType<?> get(String id) {return getEntry(id).value();}
}
