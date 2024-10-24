package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.entity.CaroInvictusEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.entry.RegistryEntry;

@SuppressWarnings("unchecked")
public class ModEntities {
    static void register() {
        ModRegistries.register("caro_invictus", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CaroInvictusEntity::new).dimensions(EntityDimensions.fixed(0.8f, 1.8f)).build());
        FabricDefaultAttributeRegistry.register((EntityType<CaroInvictusEntity>) ModRegistries.ENTITIES.get("caro_invictus").value(), CaroInvictusEntity.createMobAttributes().add(EntityAttributes.GENERIC_ARMOR, 10d).add(EntityAttributes.GENERIC_MAX_HEALTH, 100d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8d).add(EntityAttributes.GENERIC_SCALE, 1d).build());
    }
    public static <E extends Entity> EntityType<? extends E> get(String id) {
        // Yes casting like this is janky as.
        // Yes it is needed.
        // No, I don't know why,
        return (EntityType<? extends E>) getEntry(id).value();
    }

    public static RegistryEntry<EntityType<?>> getEntry(String id) {return ModRegistries.ENTITIES.get(id);}
}
