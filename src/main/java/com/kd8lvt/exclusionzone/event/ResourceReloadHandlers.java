package com.kd8lvt.exclusionzone.event;

import com.kd8lvt.exclusionzone.datagen.tag.ItemTagProvider;
import com.kd8lvt.exclusionzone.content.item.PersonaWeapons.PersonaWeaponTraits;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import static com.kd8lvt.exclusionzone.ExclusionZone.*;

public class ResourceReloadHandlers {
    private ResourceReloadHandlers() {}
    public static void registerHandlers() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return id("persona_weapon_traits");
            }

            @Override
            public void reload(ResourceManager manager) {
                PersonaWeaponTraits.reload(manager);
            }
        });

        ResourceManagerHelper.get(ResourceType.SERVER_DATA).registerReloadListener(new SimpleSynchronousResourceReloadListener() {
            @Override
            public Identifier getFabricId() {
                return RegistryKeys.RECIPE.getValue();
            }

            @Override
            public void reload(ResourceManager manager) {
                if (Server == null) {
                    return;
                }
                for (RecipeEntry<?> entry : Server.getRecipeManager().values()) {
                    if (!entry.value().getIngredients().contains(Ingredient.ofItems(Items.RABBIT_HIDE))) continue;
                    LOGGER.info("Patching %s".formatted(entry.id().toString()));
                    entry.value().getIngredients().replaceAll(ingredient->{
                        if (ingredient.test(new ItemStack(Items.RABBIT_HIDE))) return Ingredient.fromTag(ItemTagProvider.SMALL_HIDES);
                        return ingredient;
                    });
                }
            }
        });
    }
}
