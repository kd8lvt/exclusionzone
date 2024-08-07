package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.init.ModStatusEffects;
import com.kd8lvt.exclusionzone.init.registries.ModItemRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

public class ExclusionZoneEnglishProvider extends FabricLanguageProvider {

    protected ExclusionZoneEnglishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        for (Item item: ModItemRegistry.ITEMS) {
            //For whatever reason this also includes the mod blocks (except Enderweed, which I don't really care about)
            translationBuilder.add(item,toTitleCase(item.getTranslationKey().replace("item.exclusionzone.","").replace("block.exclusionzone.","").replaceAll("_"," ")));
        }
        translationBuilder.add(ModStatusEffects.MILK,"Cleansing");
        translationBuilder.add(ModStatusEffects.KILL_FOCUS,"Focused");
        translationBuilder.add("item.minecraft.potion.effect.milk","Potion of Cleansing"); //Surely this can be done cleaner
        translationBuilder.add("item.minecraft.splash_potion.effect.milk","Splash Potion of Cleansing");
        translationBuilder.add("item.minecraft.lingering_potion.effect.milk","Lingering Potion of Cleansing");
        translationBuilder.add("item.minecraft.tipped_arrow.effect.milk","Tipped Arrow of Cleansing");
    }

    public static String toTitleCase(String input) {
        StringBuilder titleCase = new StringBuilder(input.length());
        boolean nextTitleCase = true;

        for (char c:input.toCharArray()) {
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
