package com.kd8lvt.exclusionzone.datagen.lang;

import com.kd8lvt.exclusionzone.item.BlockItemArtifact;
import com.kd8lvt.exclusionzone.item.base.IHasResearchNotes;
import com.kd8lvt.exclusionzone.registry.ModRegistries;
import com.kd8lvt.exclusionzone.registry.ModStatusEffects;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class EnglishLangProvider extends FabricLanguageProvider {

    public EnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        translationBuilder.add("tooltips.exclusionzone.research_notes.header","Research Notes:");
        for (Item item: ModRegistries.ITEMS.ENTRIES_BY_VALUE.keySet()) {
            translationBuilder.add(item,toTitleCase(item.getTranslationKey().replaceAll("_"," ")));
            if (item instanceof IHasResearchNotes artifact) {
                for (int i =0;i<artifact.getTooltips().size();i++) {
                    translationBuilder.add(item.getTranslationKey()+".research_notes_"+i,artifact.getTooltips().get(i).getString());
                }
            } else if (item instanceof BlockItemArtifact artifact) {
                for (int i =0;i<artifact.tt.size();i++) {
                    translationBuilder.add(item.getTranslationKey()+".research_notes_"+i,artifact.tt.get(i).getString());
                }
            }
        }
        translationBuilder.add(ModStatusEffects.get("milk"),"Cleansing");
        translationBuilder.add(ModStatusEffects.get("kill_focus"),"Focused");
        translationBuilder.add("item.minecraft.potion.effect.milk","Potion of Cleansing"); //Surely this can be done cleaner
        translationBuilder.add("item.minecraft.splash_potion.effect.milk","Splash Potion of Cleansing");
        translationBuilder.add("item.minecraft.lingering_potion.effect.milk","Lingering Potion of Cleansing");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.milk","Tipped Arrow of Cleansing");
    }

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
