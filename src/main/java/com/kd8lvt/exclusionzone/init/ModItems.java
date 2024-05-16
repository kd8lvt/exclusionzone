package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Items.Artifact;
import com.kd8lvt.exclusionzone.init.Items.Dolls.BoyDoll;
import com.kd8lvt.exclusionzone.init.Items.Dolls.GirlDoll;
import com.kd8lvt.exclusionzone.init.Items.Dolls.VillagerDoll;
import com.kd8lvt.exclusionzone.init.Items.ModFoodComponents;
import com.kd8lvt.exclusionzone.init.Items.Tools.Glasscutter;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModItems {
    public static final BoyDoll BOY_DOLL = new BoyDoll();
    public static final GirlDoll GIRL_DOLL = new GirlDoll();
    public static final VillagerDoll VILLAGER_DOLL = new VillagerDoll();
    public static final Item MYSTERIOUS_CHUNK = new Artifact();
    public static final Item OTHERWORLDLY_BONE = new Artifact();
    public static final Item QUICKMETAL = new Artifact();
    public static final Item SCRAP_METAL = new Artifact();
    public static final Item WARPED_MEAT = new Item(new Item.Settings().food(ModFoodComponents.WARPED_MEAT));
    public static final Item ODD_SEED = new AliasedBlockItem(ModBlocks.ENDERWEED, new Item.Settings());
    public static final Item MOSS_SAMPLE = new Artifact();
    public static final Item CHIPPED_CARAPACE = new Artifact();
    public static final Item HUNK_OF_AMBER = new Artifact();
    public static final Item ENORMOUS_TARDIGRADE = new Artifact();
    public static final Glasscutter GLASSCUTTER = new Glasscutter();
    public static final ItemGroup.Builder ITEM_GROUP_BUILDER = ItemGroup.create(ItemGroup.Row.TOP,1).displayName(Text.of("Exclusion Zone")).icon(ModItems.MYSTERIOUS_CHUNK::getDefaultStack).entries(ExclusionZone::TabEntryCollector);
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
        //ItemGroup
            register(Registries.ITEM_GROUP,new Identifier("exclusionzone","creativetab"),ITEM_GROUP_BUILDER.build());
    }

    public static void register(Registry registry, Identifier identifier, Item item) {
        Registry.register(registry,identifier,item);
        ITEMS.add(item);
    }
    public static void register(Registry registry, Identifier identifier, ItemGroup item) {
        Registry.register(registry,identifier,item);
    }

    public static void CreativeTabSetup(ItemGroup.Entries entries) {
        for (Item item:ITEMS) {
            entries.add(item);
        }
    }
}
