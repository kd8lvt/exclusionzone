package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.init.registries.ModItemRegistry;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;

import java.util.List;

@SuppressWarnings("unused")
public class ModItems {
    public static Item BOY_DOLL;
    public static Item GIRL_DOLL;
    public static Item VILLAGER_DOLL;
    public static Item MYSTERIOUS_CHUNK;
    public static Item OTHERWORLDLY_BONE;
    public static Item QUICKMETAL;
    public static Item SCRAP_METAL;
    public static Item WARPED_MEAT;
    public static Item INFINITE_STEAK;
    public static Item CARO_INVICTUS_SPAWNER;
    public static Item ODD_SEED;
    public static Item MOSS_SAMPLE;
    public static Item CHIPPED_CARAPACE;
    public static Item HUNK_OF_AMBER;
    public static Item ENORMOUS_TARDIGRADE;
    public static Item GLASSCUTTER;
    public static Item PERSONA_MONOSWORD;
    public static final Style ttStyle = Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow());
    public static ItemGroup ITEM_GROUP;
    public static ComponentType<List<Identifier>> DATA_COMPONENT_PWEAPON_TRAITS;

    public static void ready() {
        BOY_DOLL = ModItemRegistry.BOY_DOLL.value();
        GIRL_DOLL = ModItemRegistry.GIRL_DOLL.value();
        VILLAGER_DOLL= ModItemRegistry.VILLAGER_DOLL.value();
        MYSTERIOUS_CHUNK = ModItemRegistry.MYSTERIOUS_CHUNK.value();
        OTHERWORLDLY_BONE = ModItemRegistry.OTHERWORLDLY_BONE.value();
        QUICKMETAL = ModItemRegistry.QUICKMETAL.value();
        SCRAP_METAL = ModItemRegistry.SCRAP_METAL.value();
        WARPED_MEAT = ModItemRegistry.WARPED_MEAT.value();
        INFINITE_STEAK = ModItemRegistry.INFINITE_STEAK.value();
        CARO_INVICTUS_SPAWNER = ModItemRegistry.CARO_INVICTUS_SPAWNER.value();
        ODD_SEED = ModItemRegistry.ODD_SEED.value();
        MOSS_SAMPLE = ModItemRegistry.MOSS_SAMPLE.value();
        CHIPPED_CARAPACE = ModItemRegistry.CHIPPED_CARAPACE.value();
        HUNK_OF_AMBER = ModItemRegistry.HUNK_OF_AMBER.value();
        ENORMOUS_TARDIGRADE = ModItemRegistry.ENORMOUS_TARDIGRADE.value();
        GLASSCUTTER = ModItemRegistry.GLASSCUTTER.value();
        PERSONA_MONOSWORD = ModItemRegistry.PERSONA_MONOSWORD.value();

        ITEM_GROUP = ModItemRegistry.ITEM_GROUP.value();
        DATA_COMPONENT_PWEAPON_TRAITS = ModItemRegistry.DATA_COMPONENT_PWEAPON_TRAITS;
    }
}
