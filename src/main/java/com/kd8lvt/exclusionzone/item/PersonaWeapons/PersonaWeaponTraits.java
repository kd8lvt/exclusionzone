package com.kd8lvt.exclusionzone.item.PersonaWeapons;

import com.google.gson.JsonObject;
import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.item.PersonaWeapons.Traits.PTrait;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;

import java.io.InputStream;
import java.util.HashMap;

public class PersonaWeaponTraits {
    public static final HashMap<Identifier, PTrait> TRAITS = new HashMap<>();
    private static final HashMap<Identifier,PTrait> javaTraits = new HashMap<>();

    public static void register(Identifier id, PTrait pTrait) {
        TRAITS.put(id,pTrait);
        javaTraits.put(id,pTrait);
    }

    public static void reload(ResourceManager manager) {
        TRAITS.clear();
        manager.findResources("persona_weapon_traits",identifier-> true).forEach((id, res) -> {
            try (InputStream stream = manager.getResource(id).get().getInputStream()) {
                JsonObject json = JsonHelper.deserialize(stream.toString());
                TRAITS.put(id,new PTrait(id,json));
            } catch (Exception e) {
                ExclusionZone.LOGGER.error("Error occurred while loading resource json "+id.toString(),e);
            }
        });
        TRAITS.putAll(javaTraits);
    }
}
