package com.kd8lvt.exclusionzone.datagen.tag;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends FabricTagProvider<Item> {
    public static final TagKey<Item> SMALL_HIDES = TagKey.of(RegistryKeys.ITEM,Identifier.of("c","small_hide"));

    public ItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output,RegistryKeys.ITEM,registriesFuture);
        getOrCreateTagBuilder(SMALL_HIDES)
            .add(Identifier.ofVanilla("rabbit_hide"), ExclusionZone.id("leather_scraps"));
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {

    }
}
