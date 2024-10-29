package com.kd8lvt.exclusionzone.registry;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModDataComponents {
    static void register() {
        ModRegistries.register("enabled", new ComponentType.Builder<Boolean>().codec(Codec.BOOL).build());
        ModRegistries.register("persona_weapon_traits", new ComponentType.Builder<List<Identifier>>().codec(Codec.list(Codec.stringResolver(Identifier::toString, Identifier::of))).build());
        ModRegistries.register("deep_storage", new ComponentType.Builder<NbtComponent>().codec(NbtComponent.CODEC).packetCodec(NbtComponent.PACKET_CODEC).build());
    }

    public static RegistryEntry<ComponentType<?>> getEntry(String id) {return ModRegistries.COMPONENT_TYPES.get(id);}
    public static ComponentType<?> get(String id) {return getEntry(id).value();}
}
