package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;

import java.util.HashMap;

/**
 * A simple wrapper class to make switching between RegistryEntries and their values more convenient.
 * @param <T> A type of registrable object.
 */
public class ModRegistry<T> {
    public final HashMap<Identifier, RegistryEntry<T>> ENTRIES_BY_ID = new HashMap<>();
    public final HashMap<T,RegistryEntry<T>> ENTRIES_BY_VALUE = new HashMap<>();
    public final Registry<T> REGISTRY;

    /**
     * @param registry The Minecraft registry that holds values of type T
     */
    public ModRegistry(Registry<T> registry) {
        this.REGISTRY = registry;
    }

    /**
     * @param identifierPath The path portion of an identifier. Is automatically given the ExclusionZone namepsace.
     * @param value The T you're registering.
     * @return The registry entry pertaining to the registered T.
     */
    public RegistryEntry<T> register(String identifierPath, T value) {
        Identifier identifier = ExclusionZone.id(identifierPath);
        Registry.register(REGISTRY, identifier,value);
        RegistryEntry<T> entry = REGISTRY.getEntry(value);
        ENTRIES_BY_ID.put(identifier,entry);
        ENTRIES_BY_VALUE.put(value,entry);
        return entry;
    }

    /**
     * @param id Identifier to find the RegistryEntry of.
     * @return Found RegistryEntry, if any. Otherwise, null.
     */
    public RegistryEntry<T> get(Identifier id) {return ENTRIES_BY_ID.get(id);}

    /**
     * @param id Path portion of an identifier to find the RegistryEntry of. Is automatically given the ExclusionZone namespace.
     * @return Found RegistryEntry, if any. Otherwise, null.
     */
    public RegistryEntry<T> get(String id) {return get(ExclusionZone.id(id));}

    /**
     * @param value The value of type T to find a RegistryEntry of.
     * @return Found RegistryEntry, if any. Otherwise, null.
     */
    @SuppressWarnings("unused")
    public RegistryEntry<T> get(T value) {return ENTRIES_BY_VALUE.get(value);}
}
