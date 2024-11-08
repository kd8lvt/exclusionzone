package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.content.item.*;
import com.kd8lvt.exclusionzone.content.item.Dolls.BoyDoll;
import com.kd8lvt.exclusionzone.content.item.Dolls.GirlDoll;
import com.kd8lvt.exclusionzone.content.item.Dolls.VillagerDoll;
import com.kd8lvt.exclusionzone.content.item.PersonaWeapons.PersonaMonosword;
import com.kd8lvt.exclusionzone.content.item.PersonaWeapons.PersonaWeaponTraits;
import com.kd8lvt.exclusionzone.content.item.PersonaWeapons.Traits.PTraitFastMover;
import com.kd8lvt.exclusionzone.content.item.PersonaWeapons.Traits.PTraitKillFocused;
import com.kd8lvt.exclusionzone.content.item.PersonaWeapons.Traits.PTraitLightweight;
import com.kd8lvt.exclusionzone.content.item.PersonaWeapons.Traits.PTraitMadMuttering;
import com.kd8lvt.exclusionzone.content.item.Tools.*;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

public class ModItems {
    public static final Style ttStyle = Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow()).withItalic(true);

    static void register() {
        //Artefacts
        //Dolls
        ModRegistries.register("boy_doll", new BoyDoll());
        ModRegistries.register("girl_doll", new GirlDoll());
        ModRegistries.register("villager_doll", new VillagerDoll());
        //Occult
        ModRegistries.register("mysterious_chunk", new Artifact());
        ModRegistries.register("otherworldly_bone", new Artifact());
        ModRegistries.register("quickmetal", new Artifact());
        ModRegistries.register("scrap_metal", new Artifact());
        ModRegistries.register("warped_meat", new Artifact(new Item.Settings().food(ModFoodComponents.WARPED_MEAT)));
        ModRegistries.register("cito_sanitatem_caro", new InfiniteFoodArtifact());
        ModRegistries.register("omen_of_caro_invictus", new CaroInvictusSummoner());
        //Moss Loot
        ModRegistries.register("odd_seed", new BlockItemArtifact(ModRegistries.BLOCKS.get("plant/enderweed").value()));
        ModRegistries.register("moss_sample", new Artifact());
        ModRegistries.register("chipped_carapace", new Artifact());
        ModRegistries.register("hunk_of_amber", new Artifact());
        ModRegistries.register("enormous_tardigrade", new Artifact());
        //Tools
        ModRegistries.register("glasscutter", new Glasscutter());
        ModRegistries.register("persona_monosword", new PersonaMonosword());
        ModRegistries.register("magnet",new Magnet(6));
        ModRegistries.register("logging_axe",new LoggingAxe());
        ModRegistries.register("void_tear",new VoidTear());
        ModRegistries.register("guster_jar",new GusterJar());
        //Misc
        ModRegistries.register("reinforced_handle",new Artifact());
        ModRegistries.register("logging_axe_head",new Artifact());
        ModRegistries.register("leather_scraps",new Artifact());

        //ItemGroup
        ModRegistries.register("creativetab", ItemGroup.create(ItemGroup.Row.TOP, 1).displayName(Text.of("Exclusion Zone")).icon(ModRegistries.ITEMS.get("mysterious_chunk").value()::getDefaultStack).entries(ExclusionZone::TabEntryCollector).build());

        PersonaWeaponTraits.register(ExclusionZone.id("fast_mover"), new PTraitFastMover());
        PersonaWeaponTraits.register(ExclusionZone.id("kill_focused"), new PTraitKillFocused());
        PersonaWeaponTraits.register(ExclusionZone.id("mad_muttering"), new PTraitMadMuttering());
        PersonaWeaponTraits.register(ExclusionZone.id("lightweight"), new PTraitLightweight());

        generateTooltips();
    }


    public static RegistryEntry<Item> getEntry(String id) {return ModRegistries.ITEMS.get(id);}
    public static Item get(String id) {return getEntry(id).value();}

    public static void CreativeTabSetup(ItemGroup.Entries entries) {
        for (Item item : ModRegistries.ITEMS.ENTRIES_BY_VALUE.keySet()) entries.add(item);
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

    private static void generateTooltips() {
        addToolTip((Artifact) ModRegistries.ITEMS.get("mysterious_chunk").value(), new String[]{
                "What appears to be a regular chunk of stone hides a fascinating secret.",
                "When held, it tugs almost imperceptibly towards the Exclusion Zone."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("otherworldly_bone").value(), new String[]{
                "Despite being covered in tumors, this bone is unmistakably human in origin.",
                "It's probably best to maintain your protective gear extremely thoroughly",
                "when in the Exclusion Zone, lest you end up like this bone's previous owner."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("quickmetal").value(), new String[]{
                "This metal acts as if it is alive, bending and folding without resistance.",
                "However, when left unobserved for a few seconds, it will be in the exact shape it was when first unearthed."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("scrap_metal").value(), new String[]{
                "A shard from a metal tool head, or perhaps just some slag left over from smithing something.",
                "Either way it has large value in learning about the society that lived in the Exclusion Zone."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("warped_meat").value(), new String[]{
                "At first glance this scrap resembles Zombie Flesh, but genetic analysis reveals it is actually something entirely unknown.",
                "Whatever it was in life, it shared an unsettlingly large amount of DNA with humans."
        });
        addToolTip(((BlockItemArtifact)ModRegistries.ITEMS.get("odd_seed").value()), new String[]{
                "A never-before seen seed. Its species is an ongoing subject of study.",
                "Should you wish to plant it, doing so in Moss is likely to be most effective.",
                "However, be warned that it is packed with extremely high amounts of the toxins prevalent",
                "in its natural habitat, which will likely be released as it grows."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("moss_sample").value(), new String[]{
                "A living sample of moss that covers the rocky ground of the Exclusion Zone.",
                "It is incredibly aggressive, taking over small rocks in mere minutes."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("chipped_carapace").value(), new String[]{
                "The damaged carapace of an arthropod.",
                "Whatever shed it must have been gigantic..."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("hunk_of_amber").value(), new String[]{
                "A chunk of amber, found buried in moss.",
                "No mosquito to be found, unfortunately."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("enormous_tardigrade").value(), new String[]{
                "Echiniscoides sigismundi, scaled up to an even more gargantuan size.",
                "It's unclear exactly how this tardigrade became so large.",
                "Though, given the circumstances, your guess is probably accurate."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("omen_of_caro_invictus").value(), new String[]{
                "You feel uneasy just holding it...",
                "If you choose to challenge the beast, make sure you're well-armed.",
                "To initiate the ritual, sneakily apply to an active Beacon, and wait..."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("cito_sanitatem_caro").value(), new String[]{
                "A squirming mass of rapidly regenerating flesh.",
                "Surprisingly, the taste is almost identical to a perfectly cooked steak.",
                "It is, however, absolutely riddled with the Exclusion Zone's toxins,",
                "so eating from it too often would be detrimental to your health."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("magnet").value(), new String[]{
                "This finely-carved Mysterious Chunk resembles a horseshoe magnet.",
                "Sneak-right-click to toggle it on/off."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("boy_doll").value(), new String[]{
                "A discarded children's toy.",
                "Judging from the colors, this one is meant to be a boy."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("girl_doll").value(), new String[]{
                "A discarded children's toy.",
                "Judging from the colors, this one is meant to be a girl."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("villager_doll").value(), new String[]{
                "A discarded children's toy.",
                "This one has an enormous nose!"
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("logging_axe_head").value(), new String[]{
                "An extremely heavy axe head.",
                "Probably requires something a little tougher than some sticks to wield."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("reinforced_handle").value(), new String[]{
                "This should be able to *handle* even the heaviest tool heads!",
                "...",
                "I'm sorry, that was terrible."
        });
        addToolTip((Artifact) ModRegistries.ITEMS.get("leather_scraps").value(), new String[]{
                "For when leathers obtained from large animals are too big.",
                "Makes for a good Rabbit Hide replacement!"
        });
    }
}
