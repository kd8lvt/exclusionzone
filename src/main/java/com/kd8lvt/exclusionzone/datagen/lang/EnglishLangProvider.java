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

import static com.kd8lvt.exclusionzone.api.datagen.lang.TranslationKeys.*;

public class EnglishLangProvider extends AbstractExclusionZoneLangProvider {

    public EnglishLangProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, Locale.US, registryLookup);
    }

    @Override
    public void generateTranslations(RegistryWrapper.WrapperLookup registryLookup, TranslationBuilder builder) {
        super.generateTranslations(registryLookup, builder); //THIS MUST STAY HERE TO PROPERLY CALL THE .onThingFound METHODS

        builder.add(ITEM_GROUPS.MAIN.toTranslationKey(),"Exclusion Zone");
        builder.add(TOOLTIPS.RESEARCH_HEADER.toTranslationKey(),"Research Notes:");
        builder.add(DEATH_MESSAGES.TOXIC_ENVIRONMENT.toTranslationKey(),"%s was magically irradiated");
        builder.add(DEATH_MESSAGES.TOXIC_ENVIRONMENT_PLAYER.toTranslationKey(),"%s succumbed to the Exclusion Zone whilst trying to escape %s");
        builder.add(DEATH_MESSAGES.TOXIC_ENTITY.toTranslationKey(),"%s got turned into a SuperFund project by %s");
        builder.add(DEATH_MESSAGES.TOXIC_ENTITY_ITEM.toTranslationKey(),"%s succumbed to toxic buildup inflicted by %s using %s");
        builder.add(DEATH_MESSAGES.TOXIC_ENTITY_PLAYER.toTranslationKey(),"%s was magically irradiated whilst trying to escape %s");

        builder.add(ATTRIBUTES.TOXIN_RESISTANCE.toTranslationKey(),"Toxin Resistance");
        builder.add(ATTRIBUTES.TOXIC_DAMAGE.toTranslationKey(),"Toxic Damage");

        builder.add(ENCHANTMENTS.TOXIC_RESISTANCE.toTranslationKey(),"Toxicae Praesidium");
        builder.add(ENCHANTMENTS.TOXIC_DAMAGE.toTranslationKey(),"Toxicae Perditio");

        addPotion(builder,"milk","Cleansing");
        addPotion(builder,"kill_focus","Focused","Focus");
    }

    public void onTagFound(TranslationBuilder builder, TagKey<?> tag) {
        builder.add(tag,toTitleCase(tag.id().getPath().replaceAll("_"," ")));
    }

    public void onItemFound(TranslationBuilder builder, Item item) {
        String id = Registries.ITEM.getId(item).getPath();
        builder.add(item,toTitleCase(
            id.replaceAll("_"," ")
                .substring(id.lastIndexOf('/')+1)
        ));
    }

    public <T extends Item> void onTooltipFound(TranslationBuilder builder, T item, String translationKey, int tooltipIdx) {
        builder.add(translationKey,((IHasResearchNotes)item).getTooltips().get(tooltipIdx).getString());
    }
}
