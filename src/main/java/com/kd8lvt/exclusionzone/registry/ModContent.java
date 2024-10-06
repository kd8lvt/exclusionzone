package com.kd8lvt.exclusionzone.registry;

import net.minecraft.registry.entry.RegistryEntry;

public record ModContent<T>(T value, String id, RegistryEntry<T> entry) {}
