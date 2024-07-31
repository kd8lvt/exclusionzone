package com.kd8lvt.exclusionzone.init.registries;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Items.*;
import com.kd8lvt.exclusionzone.init.Items.Dolls.BoyDoll;
import com.kd8lvt.exclusionzone.init.Items.Dolls.GirlDoll;
import com.kd8lvt.exclusionzone.init.Items.Dolls.VillagerDoll;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.PersonaMonosword;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.PersonaWeaponTraits;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits.PTraitFastMover;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits.PTraitKillFocused;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits.PTraitMadMuttering;
import com.kd8lvt.exclusionzone.init.Items.Tools.Glasscutter;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import com.kd8lvt.exclusionzone.init.RegistryUtil;
import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModItemRegistry {
    public static final ArrayList<Item> ITEMS = new ArrayList<>();
    public static RegistryEntry<Item> BOY_DOLL;
    public static RegistryEntry<Item> GIRL_DOLL;
    public static RegistryEntry<Item> VILLAGER_DOLL;
    public static RegistryEntry<Item> MYSTERIOUS_CHUNK;
    public static RegistryEntry<Item> OTHERWORLDLY_BONE;
    public static RegistryEntry<Item> QUICKMETAL;
    public static RegistryEntry<Item> SCRAP_METAL;
    public static RegistryEntry<Item> WARPED_MEAT;
    public static RegistryEntry<Item> INFINITE_STEAK;
    public static RegistryEntry<Item> CARO_INVICTUS_SPAWNER;
    public static RegistryEntry<Item> ODD_SEED;
    public static RegistryEntry<Item> MOSS_SAMPLE;
    public static RegistryEntry<Item> CHIPPED_CARAPACE;
    public static RegistryEntry<Item> HUNK_OF_AMBER;
    public static RegistryEntry<Item> ENORMOUS_TARDIGRADE;
    public static RegistryEntry<Item> GLASSCUTTER;
    public static RegistryEntry<Item> PERSONA_MONOSWORD;
    public static final Style ttStyle = Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow());
    public static RegistryEntry<ItemGroup> ITEM_GROUP;
    public static ComponentType<List<Identifier>> DATA_COMPONENT_PWEAPON_TRAITS;

    public static void register() {
        //Artefacts
        //Dolls
        BOY_DOLL = RegistryUtil.register("boy_doll", new BoyDoll());
        GIRL_DOLL = RegistryUtil.register("girl_doll", new GirlDoll());
        VILLAGER_DOLL = RegistryUtil.register("villager_doll", new VillagerDoll());
        //Occult
        MYSTERIOUS_CHUNK = RegistryUtil.register("mysterious_chunk", new Artifact());
        OTHERWORLDLY_BONE = RegistryUtil.register("otherworldly_bone", new Artifact());
        QUICKMETAL = RegistryUtil.register("quickmetal", new Artifact());
        SCRAP_METAL = RegistryUtil.register("scrap_metal", new Artifact());
        WARPED_MEAT = RegistryUtil.register("warped_meat", new Artifact(new Item.Settings().food(ModFoodComponents.WARPED_MEAT)));
        INFINITE_STEAK = RegistryUtil.register("cito_sanitatem_caro", new InfiniteFoodArtifact());
        CARO_INVICTUS_SPAWNER = RegistryUtil.register("omen_of_caro_invictus", new CaroInvictusSummoner());
        //Moss Loot
        ODD_SEED = RegistryUtil.register("odd_seed", new BlockItemArtifact(ModBlocks.ENDERWEED));
        MOSS_SAMPLE = RegistryUtil.register("moss_sample", new Artifact());
        CHIPPED_CARAPACE = RegistryUtil.register("chipped_carapace", new Artifact());
        HUNK_OF_AMBER = RegistryUtil.register("hunk_of_amber", new Artifact());
        ENORMOUS_TARDIGRADE = RegistryUtil.register("enormous_tardigrade", new Artifact());
        //Tools
        GLASSCUTTER = RegistryUtil.register("glasscutter", new Glasscutter());
        PERSONA_MONOSWORD = RegistryUtil.register("persona_monosword", new PersonaMonosword());
        //ItemGroup
        ITEM_GROUP = RegistryUtil.register("creativetab", ItemGroup.create(ItemGroup.Row.TOP, 1).displayName(Text.of("Exclusion Zone")).icon(MYSTERIOUS_CHUNK.value()::getDefaultStack).entries(ExclusionZone::TabEntryCollector).build());
        //Data Component Types
        DATA_COMPONENT_PWEAPON_TRAITS = Registry.register(Registries.DATA_COMPONENT_TYPE, ExclusionZone.id("persona_weapon_traits"), new ComponentType.Builder<List<Identifier>>().codec(Codec.list(Codec.stringResolver(Identifier::toString, Identifier::of))).build());
        PersonaWeaponTraits.register(ExclusionZone.id("fast_mover"), new PTraitFastMover());
        PersonaWeaponTraits.register(ExclusionZone.id("kill_focused"), new PTraitKillFocused());
        PersonaWeaponTraits.register(ExclusionZone.id("mad_muttering"), new PTraitMadMuttering());


        addToolTip((Artifact) MYSTERIOUS_CHUNK.value(), new String[]{"What appears to be a regular chunk", "of stone hides a fascinating secret.", "When held, it tugs almost imperceptibly", "towards the Exclusion Zone."});
        addToolTip((Artifact) OTHERWORLDLY_BONE.value(), new String[]{"It doesn't resemble a bone from any", "species you recognize, and is", "covered in tumors. It's probably", "best to maintain your protective", "gear extremely thoroughly when", "in the Exclusion Zone..."});
        addToolTip((Artifact) QUICKMETAL.value(), new String[]{"This metal acts as if it is", "alive, bending and folding", "without resistance. However,", "when left unobserved for a", "few seconds, it will be in the", "exact shape it was when", "first unearthed."});
        addToolTip((Artifact) SCRAP_METAL.value(), new String[]{"A shard from a metal tool head,", "or perhaps just some slag left", "over from smithing something.", "Either way it has large value", "for learning about the society", "that lived in the Exclusion Zone."});
        addToolTip((Artifact) WARPED_MEAT.value(), new String[]{"At first glance", "resembles Zombie Flesh, but genetic", "analysis reveals it is actually", "somethign entirely unknown. It has an", "unsettlingly large amount of DNA in", "common with Humans."});
        addToolTip(((BlockItemArtifact)ODD_SEED.value()), new String[]{"A never-before seen seed.", "Its species is an ongoing subject of study."});
        addToolTip((Artifact) MOSS_SAMPLE.value(), new String[]{"A living sample of moss that covers the rocky ground of the Exclusion Zone.", "It is incredibly aggressive, taking over small pebbles in mere minutes."});
        addToolTip((Artifact) CHIPPED_CARAPACE.value(), new String[]{"The damaged carapace of an arthropod.", "What ever shed it must have been gigantic..."});
        addToolTip((Artifact) HUNK_OF_AMBER.value(), new String[]{"A chunk of amber, found buried in moss.", "No mosquito to be found, unfortunately."});
        addToolTip((Artifact) ENORMOUS_TARDIGRADE.value(), new String[]{"Your bog-standard tardigrade, but scaled up to gargantuan size.", "It's unclear exactly how the tardigrade became so large,", "but given the circumstances, you can probably guess."});
        addToolTip((Artifact) CARO_INVICTUS_SPAWNER.value(), new String[]{"You feel uneasy just holding it...", "If you choose to challenge the beast,", "one should be prepared, and sneakily apply to an active Beacon..."});
    }

    public static void CreativeTabSetup(ItemGroup.Entries entries) {
        for (Item item : ITEMS) entries.add(item);
    }

    public static void addToolTip(BlockItemArtifact item, String[] strs) {
        for (String str : strs) {
            addToolTip(item, str);
        }
    }
    public static void addToolTip(BlockItemArtifact item, String str) {
        item.tt.addAll(Text.of(str).getWithStyle(ttStyle));
    }

    public static void addTooltip(Artifact item, String str) {
        item.tt.addAll(Text.of(str).getWithStyle(ttStyle));
    }

    public static void addToolTip(Artifact item, String[] strs) {
        for (String str : strs) {
            addTooltip(item, str);
        }
    }
}
