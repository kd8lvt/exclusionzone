package com.kd8lvt.exclusionzone.api.datagen.lang;

import com.kd8lvt.exclusionzone.content.item.base.IHasResearchNotes;
import com.kd8lvt.exclusionzone.registry.ModRegistries;
import com.kd8lvt.exclusionzone.registry.ModStatusEffects;
import com.kd8lvt.exclusionzone.registry.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.PotionItem;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

import static com.kd8lvt.exclusionzone.ExclusionZone.MOD_ID;

public abstract class AbstractExclusionZoneLangProvider extends FabricLanguageProvider {
    public AbstractExclusionZoneLangProvider(FabricDataOutput dataOutput, Locale locale, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, locale.toLanguageTag().toLowerCase().replace("-","_"), registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        addAllItems(builder);
        addAllTags(builder);
    }

    /**
     * @see this#addPotion(TranslationBuilder,String,String,String)
     */
    @SuppressWarnings("SameParameterValue")
    public static void addPotion(TranslationBuilder builder, String id, String name) {
        addPotion(builder,id,name,name);
    }

    /**
     * Adds translations for the various minecraft-generated potion types, all in one go.
     * @param builder The {@link TranslationBuilder} to add translations to
     * @param id The {@link String} that corresponds to an exclusion zone effect type
     * @param effectName The name of the {@link StatusEffect}
     * @param name The {@link String} name of the {@link PotionItem}s, if it should be different from the {@link StatusEffect}'s.
     * @implNote name is optional
     */
    public static void addPotion(TranslationBuilder builder, String id, String effectName, String name) {
        builder.add(ModStatusEffects.get(id),effectName);
        builder.add("item.minecraft.potion.effect."+id,"Potion of "+name);
        builder.add("item.minecraft.splash_potion.effect."+id,"Splash Potion of "+name);
        builder.add("item.minecraft.lingering_potion.effect."+id,"Lingering Potion of "+name);
        builder.add("item.minecraft.tipped_arrow.effect."+id,"Tipped Arrow of "+name);
    }

    public static void addAttribute(TranslationBuilder builder, String id, String translated) {
        addMisc(builder,"attribute",id,translated);
    }

    public static void addEnchantment(TranslationBuilder builder, String id, String translated) {
        addMisc(builder,"enchantment",id,translated);
    }

    private static void addMisc(TranslationBuilder builder, String pfx, String id, String translated) {
        builder.add(pfx+"."+MOD_ID+"."+id,translated);
    }

    /**
     * Loops through the keys in {@link ModRegistries#ITEMS} and fires {@link AbstractExclusionZoneLangProvider#onItemFound} for each.<br />
     * Additionally, if the item implements {@link IHasResearchNotes}, it fires {@link AbstractExclusionZoneLangProvider#onTooltipFound} for each of the its tooltips.
     * @param builder {@link TranslationBuilder} to add generated translations to
     */
    public void addAllItems(TranslationBuilder builder) {
        for (Item item: ModRegistries.ITEMS.ENTRIES_BY_VALUE.keySet()) {
            this.onItemFound(builder, item);
            if (item instanceof IHasResearchNotes artifact) {
                for (int i =0;i<artifact.getTooltips().size();i++) {
                    this.onTooltipFound(builder,item,item.getTranslationKey()+".research_notes_"+i,i);
                }
            }
        }
    }

    public abstract void onItemFound(TranslationBuilder builder, Item item);
    public abstract <T extends Item> void onTooltipFound(TranslationBuilder builder, T item, String translationKey, int tooltipIdx);

    /**
     * Loops through {@link ModTags#LIST} and sends them to {@link this#onTagFound}.<br />
     * Automatically runs on generate.
     * @param builder {@link TranslationBuilder} to add translations to
     * @see AbstractExclusionZoneLangProvider#addAllItems
     * @see AbstractExclusionZoneLangProvider#onTagFound
     */
    public void addAllTags(TranslationBuilder builder) {
        for (TagKey<?> tag : ModTags.LIST) {
            this.onTagFound(builder,tag);
        }
    }

    /**
     * Gets run for each {@link TagKey} in {@link ModTags#LIST}.<br />
     * Must be overridden!
     * @param builder {@link TranslationBuilder} to add translations to
     * @param tag {@link TagKey} to generate a tag for
     * @see AbstractExclusionZoneLangProvider#addAllTags
     */
    public abstract void onTagFound(TranslationBuilder builder, TagKey<?> tag);

    /**
     * Returns the given string, converted to "Title Case" on a per-char basis.<br />
     * For example, the input "squirrels, and why they're so mean" returns "Squirrels, And Why They're So Mean"<br /><br />
     * Does not change any characters that are unrelated to title casing (ie. those that come after a space).<br />
     * For example, the input "I am a dRUnK girAfFe" returns "I Am A DRUnk GirAfFe".<br />
     * @see Character#toTitleCase(char)
     * @see Character#isSpaceChar(char)
     * @param input the {@link String} to convert to title case
     * @return {@link String} the converted string
     */
    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        String target_str = input;
        if (input.indexOf(".") > 0) target_str = input.split("\\.")[input.split("\\.").length-1];

        for (char c:target_str.toCharArray()) {
            if (Character.isSpaceChar(c)) {
                nextTitleCase = true;
            } else if (nextTitleCase) {
                c = Character.toTitleCase(c);
                nextTitleCase = false;
            }
            titleCase.append(c);
        }

        return titleCase.toString();
    }
}
