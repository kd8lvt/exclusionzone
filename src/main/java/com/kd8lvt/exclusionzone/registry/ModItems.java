package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.items.*;
import com.kd8lvt.exclusionzone.init.items.Dolls.BoyDoll;
import com.kd8lvt.exclusionzone.init.items.Dolls.GirlDoll;
import com.kd8lvt.exclusionzone.init.items.Dolls.VillagerDoll;
import com.kd8lvt.exclusionzone.init.items.PersonaWeapons.PersonaMonosword;
import com.kd8lvt.exclusionzone.init.items.PersonaWeapons.PersonaWeaponTraits;
import com.kd8lvt.exclusionzone.init.items.PersonaWeapons.Traits.PTraitFastMover;
import com.kd8lvt.exclusionzone.init.items.PersonaWeapons.Traits.PTraitKillFocused;
import com.kd8lvt.exclusionzone.init.items.PersonaWeapons.Traits.PTraitLightweight;
import com.kd8lvt.exclusionzone.init.items.PersonaWeapons.Traits.PTraitMadMuttering;
import com.kd8lvt.exclusionzone.init.items.Tools.Glasscutter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

public class ModItems {
    public static final Style ttStyle = Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow());
    static void register() {
        //Artefacts
        //Dolls
        BOY_DOLL = ModRegistries.register("boy_doll", new BoyDoll());
        GIRL_DOLL = ModRegistries.register("girl_doll", new GirlDoll());
        VILLAGER_DOLL = ModRegistries.register("villager_doll", new VillagerDoll());
        //Occult
        MYSTERIOUS_CHUNK = ModRegistries.register("mysterious_chunk", new Artifact());
        OTHERWORLDLY_BONE = ModRegistries.register("otherworldly_bone", new Artifact());
        QUICKMETAL = ModRegistries.register("quickmetal", new Artifact());
        SCRAP_METAL = ModRegistries.register("scrap_metal", new Artifact());
        WARPED_MEAT = ModRegistries.register("warped_meat", new Artifact(new Item.Settings().food(ModFoodComponents.WARPED_MEAT)));
        INFINITE_STEAK = ModRegistries.register("cito_sanitatem_caro", new InfiniteFoodArtifact());
        CARO_INVICTUS_SUMMON = ModRegistries.register("omen_of_caro_invictus", new CaroInvictusSummoner());
        //Moss Loot
        ODD_SEED = ModRegistries.register("odd_seed", new BlockItemArtifact(ModRegistries.BLOCKS.get("plant/enderweed").value()));
        MOSS_SAMPLE = ModRegistries.register("moss_sample", new Artifact());
        CHIPPED_CARAPACE = ModRegistries.register("chipped_carapace", new Artifact());
        HUNK_OF_AMBER = ModRegistries.register("hunk_of_amber", new Artifact());
        ENORMOUS_TARDIGRADE = ModRegistries.register("enormous_tardigrade", new Artifact());
        //Tools
        GLASSCUTTER = ModRegistries.register("glasscutter", new Glasscutter());
        PERSONA_MONOSWORD = ModRegistries.register("persona_monosword", new PersonaMonosword());
        MAGNET = ModRegistries.register("magnet",new Magnet(6));
        //ItemGroup
        CREATIVE_TAB = ModRegistries.register("creativetab", ItemGroup.create(ItemGroup.Row.TOP, 1).displayName(Text.of("Exclusion Zone")).icon(ModRegistries.ITEMS.get("mysterious_chunk").value()::getDefaultStack).entries(ExclusionZone::TabEntryCollector).build());

        PersonaWeaponTraits.register(ExclusionZone.id("fast_mover"), new PTraitFastMover());
        PersonaWeaponTraits.register(ExclusionZone.id("kill_focused"), new PTraitKillFocused());
        PersonaWeaponTraits.register(ExclusionZone.id("mad_muttering"), new PTraitMadMuttering());
        PersonaWeaponTraits.register(ExclusionZone.id("lightweight"), new PTraitLightweight());

        applyTooltips();
    }

    public static RegistryEntry<Item> getEntry(String id) {return ModRegistries.ITEMS.get(id);}
    public static Item get(String id) {return getEntry(id).value();}

    public static void CreativeTabSetup(ItemGroup.Entries entries) {
        for (Item item : ModRegistries.ITEMS.ENTRIES_BY_VALUE.keySet()) entries.add(item);
    }

    private static void addToolTip(BlockItemArtifact item, String[] strs) {
        for (String str : strs) {
            addToolTip(item, str);
        }
    }
    private static void addToolTip(BlockItemArtifact item, String str) {
        item.tt.addAll(Text.of(str).getWithStyle(ttStyle));
    }

    private static void addTooltip(Artifact item, String str) {
        item.tt.addAll(Text.of(str).getWithStyle(ttStyle));
    }

    private static void addToolTip(Artifact item, String[] strs) {
        for (String str : strs) {
            addTooltip(item, str);
        }
    }

    private static void applyTooltips() {
        addToolTip((Artifact)MYSTERIOUS_CHUNK.value(), new String[]{
                "What appears to be a regular chunk of stone hides a fascinating secret.",
                "When held, it tugs almost imperceptibly towards the Exclusion Zone."
        });
        addToolTip((Artifact)OTHERWORLDLY_BONE.value(), new String[]{
                "Despite being covered in tumors, this bone is unmistakably human in origin.",
                "It's probably best to maintain your protective gear extremely thoroughly",
                "when in the Exclusion Zone, lest you end up like this bone's previous owner."
        });
        addToolTip((Artifact)QUICKMETAL.value(), new String[]{
                "This metal acts as if it is alive, bending and folding without resistance.",
                "However, when left unobserved for a few seconds, it will be in the exact shape it was when first unearthed."
        });
        addToolTip((Artifact)SCRAP_METAL.value(), new String[]{
                "A shard from a metal tool head, or perhaps just some slag left over from smithing something.",
                "Either way it has large value in learning about the society that lived in the Exclusion Zone."
        });
        addToolTip((Artifact)WARPED_MEAT.value(), new String[]{
                "At first glance this scrap resembles Zombie Flesh, but genetic analysis reveals it is actually something entirely unknown.",
                "Whatever it was in life, it shared an unsettlingly large amount of DNA with humans."
        });
        addToolTip(((BlockItemArtifact)ODD_SEED.value()), new String[]{
                "A never-before seen seed. Its species is an ongoing subject of study.",
                "Should you wish to plant it, doing so in Moss is likely to be most effective.",
                "However, be warned that it is packed with extremely high amounts of the toxins prevalent",
                "in its natural habitat, which will likely be released as it grows."
        });
        addToolTip((Artifact)MOSS_SAMPLE.value(), new String[]{
                "A living sample of moss that covers the rocky ground of the Exclusion Zone.",
                "It is incredibly aggressive, taking over small rocks in mere minutes."
        });
        addToolTip((Artifact)CHIPPED_CARAPACE.value(), new String[]{
                "The damaged carapace of an arthropod.",
                "Whatever shed it must have been gigantic..."
        });
        addToolTip((Artifact)HUNK_OF_AMBER.value(), new String[]{
                "A chunk of amber, found buried in moss.",
                "No mosquito to be found, unfortunately."
        });
        addToolTip((Artifact)ENORMOUS_TARDIGRADE.value(), new String[]{
                "Echiniscoides sigismundi, scaled up to an even more gargantuan size.",
                "It's unclear exactly how this tardigrade became so large.",
                "Though, given the circumstances, your guess is probably accurate."
        });
        addToolTip((Artifact)CARO_INVICTUS_SUMMON.value(), new String[]{
                "You feel uneasy just holding it...",
                "If you choose to challenge the beast, make sure you're well-armed.",
                "To initiate the ritual, sneakily apply to an active Beacon, and wait..."
        });
        addToolTip((Artifact)INFINITE_STEAK.value(), new String[]{
                "Translation: Rapidly Regenerating Flesh",
                "A squirming mass of rapidly regenerating flesh.",
                "Surprisingly, the taste is almost identical to a perfectly cooked steak.",
                "It is, however, absolutely riddled with the Exclusion Zone's toxins,",
                "so eating from it too often would be detrimental to your health."
        });
        addToolTip((Artifact)MAGNET.value(), new String[]{
                "This finely-carved Mysterious Chunk resembles a horseshoe magnet.",
                "Sneak-right-click to toggle it on/off."
        });
        addToolTip((Artifact)BOY_DOLL.value(), new String[]{
                "A discarded children's toy.",
                "Judging from the colors, this one is meant to be a boy."
        });
        addToolTip((Artifact)GIRL_DOLL.value(), new String[]{
                "A discarded children's toy.",
                "Judging from the colors, this one is meant to be a girl."
        });
        addToolTip((Artifact)VILLAGER_DOLL.value(), new String[]{
                "A discarded children's toy.",
                "This one has an enormous nose!"
        });
    }

    public static ModContent<Item> MYSTERIOUS_CHUNK;
    public static ModContent<Item> OTHERWORLDLY_BONE;
    public static ModContent<Item> QUICKMETAL;
    public static ModContent<Item> SCRAP_METAL;
    public static ModContent<Item> WARPED_MEAT;
    public static ModContent<Item> ODD_SEED;
    public static ModContent<Item> MOSS_SAMPLE;
    public static ModContent<Item> CHIPPED_CARAPACE;
    public static ModContent<Item> HUNK_OF_AMBER;
    public static ModContent<Item> ENORMOUS_TARDIGRADE;
    public static ModContent<Item> BOY_DOLL;
    public static ModContent<Item> GIRL_DOLL;
    public static ModContent<Item> VILLAGER_DOLL;
    public static ModContent<Item> CARO_INVICTUS_SUMMON;
    public static ModContent<Item> INFINITE_STEAK;
    public static ModContent<Item> MAGNET;
    public static ModContent<Item> PERSONA_MONOSWORD;
    public static ModContent<Item> GLASSCUTTER;
    public static ModContent<ItemGroup> CREATIVE_TAB;
}