package com.kd8lvt.exclusionzone.datagen.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.trim.ArmorTrimMaterial;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;

import static com.kd8lvt.exclusionzone.registry.ModTags.DISTRACTING_TRIM;

public class ArmorTrimTagProvider extends FabricTagProvider<ArmorTrimMaterial> {
    public ArmorTrimTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output,RegistryKeys.TRIM_MATERIAL,registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(DISTRACTING_TRIM)
                .add(Identifier.ofVanilla("gold"))
                .addOptionalTag(Identifier.of("distractingtrims:distracts_piglins"));
    }
}
