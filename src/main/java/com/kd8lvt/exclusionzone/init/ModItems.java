package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Items.Artifact;
import com.kd8lvt.exclusionzone.init.Items.BlockItemArtifact;
import com.kd8lvt.exclusionzone.init.Items.Dolls.BoyDoll;
import com.kd8lvt.exclusionzone.init.Items.Dolls.GirlDoll;
import com.kd8lvt.exclusionzone.init.Items.Dolls.VillagerDoll;
import com.kd8lvt.exclusionzone.init.Items.ModFoodComponents;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.PersonaMonosword;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.PersonaWeaponTraits;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits.PTraitFastMover;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits.PTraitKillFocused;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits.PTraitMadMuttering;
import com.kd8lvt.exclusionzone.init.Items.Tools.Glasscutter;
import com.mojang.serialization.Codec;
import net.minecraft.component.DataComponentType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class ModItems {
    public static final BoyDoll BOY_DOLL = new BoyDoll();
    public static final GirlDoll GIRL_DOLL = new GirlDoll();
    public static final VillagerDoll VILLAGER_DOLL = new VillagerDoll();
    public static final Artifact MYSTERIOUS_CHUNK = new Artifact();
    public static final Artifact OTHERWORLDLY_BONE = new Artifact();
    public static final Artifact QUICKMETAL = new Artifact();
    public static final Artifact SCRAP_METAL = new Artifact();
    public static final Artifact WARPED_MEAT = new Artifact(new Item.Settings().food(ModFoodComponents.WARPED_MEAT));
    public static final BlockItemArtifact ODD_SEED = new BlockItemArtifact(ModBlocks.ENDERWEED);
    public static final Artifact MOSS_SAMPLE = new Artifact();
    public static final Artifact CHIPPED_CARAPACE = new Artifact();
    public static final Artifact HUNK_OF_AMBER = new Artifact();
    public static final Artifact ENORMOUS_TARDIGRADE = new Artifact();
    public static final Glasscutter GLASSCUTTER = new Glasscutter();
    public static final PersonaMonosword PERSONA_MONOSWORD = new PersonaMonosword();
    public static final Style ttStyle = Style.EMPTY.withColor(TextColor.parse("gray").getOrThrow());
    static {
        addToolTip(MYSTERIOUS_CHUNK,new String[]{"What appears to be a regular chunk","of stone hides a fascinating secret.","When held, it tugs almost imperceptibly","towards the Exclusion Zone."});
        addToolTip(OTHERWORLDLY_BONE,new String[]{"It doesn't resemble a bone from any","species you recognize, and is","covered in tumors. It's probably","best to maintain your protective","gear extremely thoroughly when","in the Exclusion Zone..."});
        addToolTip(QUICKMETAL,new String[]{"This metal acts as if it is","alive, bending and folding","without resistance. However,","when left unobserved for a","few seconds, it will be in the","exact shape it was when","first unearthed."});
        addToolTip(SCRAP_METAL,new String[]{"A shard from a metal tool head,","or perhaps just some slag left","over from smithing something.","Either way it has large value","for learning about the society","that lived in the Exclusion Zone."});
        addToolTip(WARPED_MEAT,new String[]{"At first glance","resembles Zombie Flesh, but genetic","analysis reveals it is actually","somethign entirely unknown. It has an","unsettlingly large amount of DNA in", "common with Humans."});
        addToolTip(ODD_SEED,new String[]{"A never-before seen seed.","Its species is an ongoing subject of study."});
        addToolTip(MOSS_SAMPLE,new String[]{"A living sample of moss that covers the rocky ground of the Exclusion Zone.","It is incredibly aggressive, taking over small pebbles in mere minutes."});
        addToolTip(CHIPPED_CARAPACE,new String[]{"The damaged carapace of an arthropod.","What ever shed it must have been gigantic..."});
        addToolTip(HUNK_OF_AMBER,new String[]{"A chunk of amber, found buried in moss.","No mosquito to be found, unfortunately."});
        addToolTip(ENORMOUS_TARDIGRADE,new String[]{"Your bog-standard tardigrade, but scaled up to gargantuan size.","It's unclear exactly how the tardigrade became so large,","but given the circumstances, you can probably guess."});
    }
    public static final ItemGroup.Builder ITEM_GROUP_BUILDER = ItemGroup.create(ItemGroup.Row.TOP,1).displayName(Text.of("Exclusion Zone")).icon(ModItems.MYSTERIOUS_CHUNK::getDefaultStack).entries(ExclusionZone::TabEntryCollector);
    public static final DataComponentType<List<Identifier>> DATA_COMPONENT_PWEAPON_TRAITS = new DataComponentType.Builder<List<Identifier>>().codec(Codec.list(Codec.stringResolver(Identifier::toString,Identifier::new))).build();
    public static ArrayList<Item> ITEMS = new ArrayList<>();
    public static void register() {
        //Artefacts
            //Dolls
                register(Registries.ITEM,new Identifier("exclusionzone","boy_doll"),BOY_DOLL);
                register(Registries.ITEM,new Identifier("exclusionzone","girl_doll"),GIRL_DOLL);
                register(Registries.ITEM,new Identifier("exclusionzone","villager_doll"),VILLAGER_DOLL);
            //Occult
                register(Registries.ITEM,new Identifier("exclusionzone","mysterious_chunk"),MYSTERIOUS_CHUNK);
                register(Registries.ITEM,new Identifier("exclusionzone","otherworldly_bone"),OTHERWORLDLY_BONE);
                register(Registries.ITEM,new Identifier("exclusionzone","quickmetal"),QUICKMETAL);
                register(Registries.ITEM,new Identifier("exclusionzone","scrap_metal"),SCRAP_METAL);
                register(Registries.ITEM,new Identifier("exclusionzone","warped_meat"),WARPED_MEAT);
            //Moss Loot
                register(Registries.ITEM,new Identifier("exclusionzone","odd_seed"),ODD_SEED);
                register(Registries.ITEM,new Identifier("exclusionzone","moss_sample"),MOSS_SAMPLE);
                register(Registries.ITEM,new Identifier("exclusionzone","chipped_carapace"),CHIPPED_CARAPACE);
                register(Registries.ITEM,new Identifier("exclusionzone","hunk_of_amber"),HUNK_OF_AMBER);
                register(Registries.ITEM,new Identifier("exclusionzone","enormous_tardigrade"),ENORMOUS_TARDIGRADE);
            //Tools
                register(Registries.ITEM,new Identifier("exclusionzone","glasscutter"),GLASSCUTTER);
                register(Registries.ITEM,new Identifier("exclusionzone","persona_monosword"),PERSONA_MONOSWORD);
        //ItemGroup
            register(Registries.ITEM_GROUP,new Identifier("exclusionzone","creativetab"),ITEM_GROUP_BUILDER.build());
        //Data Component Types
            register(Registries.DATA_COMPONENT_TYPE,ExclusionZone.id("persona_weapon_traits"),DATA_COMPONENT_PWEAPON_TRAITS);

        PersonaWeaponTraits.register(ExclusionZone.id("fast_mover"),new PTraitFastMover());
        PersonaWeaponTraits.register(ExclusionZone.id("kill_focused"),new PTraitKillFocused());
        PersonaWeaponTraits.register(ExclusionZone.id("mad_muttering"),new PTraitMadMuttering());
    }

    public static void register(Registry<Item> registry, Identifier identifier, Item item) {
        Registry.register(registry,identifier,item);
        ITEMS.add(item);
    }
    public static void register(Registry<ItemGroup> registry, Identifier identifier, ItemGroup item) {
        Registry.register(registry,identifier,item);
    }

    public static void register(Registry<DataComponentType<?>> registry, Identifier identifier, DataComponentType<?> item) {
        Registry.register(registry,identifier,item);
    }

    public static void CreativeTabSetup(ItemGroup.Entries entries) {
        for (Item item:ITEMS) {
            entries.add(item);
        }
    }

    public static void addTooltip(Artifact item, String str) {
        item.tt.addAll(Text.of(str).getWithStyle(ttStyle));
    }
    public static void addTooltip(BlockItemArtifact item, String str) {
        item.tt.addAll(Text.of(str).getWithStyle(ttStyle));
    }

    public static void addToolTip(Artifact item, String[] strs) {
        for (String str:strs) {
            addTooltip(item,str);
        }
    }


    public static void addToolTip(BlockItemArtifact item, String[] strs) {
        for (String str:strs) {
            addTooltip(item,str);
        }
    }
}
