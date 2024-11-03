package com.kd8lvt.exclusionzone.content.item.PersonaWeapons.Traits;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.content.item.PersonaWeapons.PersonaWeaponFunctionEvents;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.JsonHelper;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SuppressWarnings({"EmptyMethod", "unused"})
public class PTrait {
    public List<Text> tt = new ArrayList<>();
    public final HashMap<PersonaWeaponFunctionEvents,Identifier> functions = new HashMap<>();
    public PTrait(Identifier id, JsonObject json) {
        if (json.has("functions")) {
            List<JsonElement> evts = JsonHelper.getArray(json,"events").asList();
            for (JsonElement evt:evts) {
                JsonObject obj = evt.getAsJsonObject();
                if (!obj.has("event") || !obj.has("function")) {
                    ExclusionZone.LOGGER.error("Error whilst parsing JSON at "+id.toString()+" >> '"+(!obj.has("event")?"event":"function")+"' is not set.");
                    throw new Error("Error whilst parsing JSON at "+ id +" >> '"+(!obj.has("event")?"event":"function")+"' is not set.");
                }
                functions.put(PersonaWeaponFunctionEvents.valueOf(obj.get("event").getAsString()),Identifier.tryParse(obj.get("function").getAsString()));
            }
        }
        if (json.has("tooltip")) {
            tt.add(Text.Serialization.fromJson(JsonHelper.getArray(json,"tooltip").getAsString(), (RegistryWrapper.WrapperLookup) Registries.REGISTRIES.getReadOnlyWrapper()));
        }
    }

    public PTrait() {}

    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {}
    @SuppressWarnings("EmptyMethod")
    public void onDurabilityLost(ItemStack stack, World world, Entity entity, int slot, boolean selected) {}
    @SuppressWarnings("EmptyMethod")
    public void onHeld(ItemStack stack, World world, Entity entity, int slot) {}
    @SuppressWarnings("EmptyMethod")
    public void onUnHeld(ItemStack stack, World world, Entity entity, int slot) {}
    @SuppressWarnings("EmptyMethod")
    public void onUseOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {}
    @SuppressWarnings("EmptyMethod")
    public void onUseOnBlock(ItemUsageContext context) {}
    public void onHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {}
}
