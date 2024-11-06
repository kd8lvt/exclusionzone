package com.kd8lvt.exclusionzone.datagen.lang;

import com.kd8lvt.exclusionzone.api.datagen.lang.AbstractExclusionZoneLangProvider;
import com.kd8lvt.exclusionzone.content.item.base.IHasResearchNotes;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

public class EnglishLangProvider extends AbstractExclusionZoneLangProvider {

    public EnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, Locale.US, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        super.generateTranslations(registryLookup, builder); //THIS MUST STAY HERE TO PROPERLY CALL THE .onThingFound METHODS

        builder.add("tooltips.exclusionzone.research_notes.header","Research Notes:");
        addAttribute(builder,"generic.toxin_resistance","Toxin Resistance");
        addAttribute(builder,"generic.toxin_damage","Toxic Damage");

        addEnchantment(builder,"toxicae_praesidium","Toxicae Praesidium");
        addEnchantment(builder,"toxicus_perditio","Toxicae Perditio");

        addPotion(builder,"milk","Cleansing");
        addPotion(builder,"kill_focus","Focused","Focus");
    }

    public void onTagFound(TranslationBuilder builder, TagKey<?> tag) {
        builder.add(tag,toTitleCase(tag.id().getPath().replaceAll("_"," ")));
    }

    public void onItemFound(TranslationBuilder builder, Item item) {
        builder.add(item,toTitleCase(Registries.ITEM.getId(item).getPath().replaceAll("_"," ")));
    }

    public <T extends Item> void onTooltipFound(TranslationBuilder builder, T item, String translationKey, int tooltipIdx) {
        builder.add(translationKey,((IHasResearchNotes)item).getTooltips().get(tooltipIdx).getString());
    }
}
