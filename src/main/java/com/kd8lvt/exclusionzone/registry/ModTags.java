package com.kd8lvt.exclusionzone.registry;

import net.minecraft.item.Item;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

import static com.kd8lvt.exclusionzone.ExclusionZone.id;

public class ModTags {
    public static final TagKey<ArmorTrimMaterial> DISTRACTING_TRIM = TagKey.of(RegistryKeys.TRIM_MATERIAL,id("distracts_piglins"));
    public static final TagKey<Item> SMALL_HIDES = TagKey.of(RegistryKeys.ITEM, Identifier.of("c","small_hide"));
    public static final ArrayList<TagKey<?>> LIST = new ArrayList<>(List.of(DISTRACTING_TRIM,SMALL_HIDES));
}
