package com.kd8lvt.exclusionzone.datagen;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.ModItems;
import com.kd8lvt.exclusionzone.init.ModPotions;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.entry.RegistryEntry;

import java.nio.file.Path;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class ExclusionZoneEnglishProvider extends FabricLanguageProvider {

    protected ExclusionZoneEnglishProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder translationBuilder) {
        for (Item item: ModItems.ITEMS) {
            //For whatever reason this also includes the mod blocks (except Enderweed, which I don't really care about)
            translationBuilder.add(item,toTitleCase(item.getTranslationKey().replace("item.exclusionzone.","").replace("block.exclusionzone.","").replaceAll("_"," ")));
        }
        translationBuilder.add(ModPotions.MILK,"Milk");
        translationBuilder.add(Potion.finishTranslationKey(Optional.of(RegistryEntry.of(ModPotions.MILK_POTION)),"Milk"),"Milk");
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
