package com.kd8lvt.exclusionzone.init.registries;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Entities.CaroInvictusEntity;
import com.kd8lvt.exclusionzone.init.RegistryUtil;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

@SuppressWarnings("unchecked")
public class ModEntityRegistry {
    public static RegistryEntry<EntityType<?>> CARO_INVICTUS;

    public static void register() {
        CARO_INVICTUS = RegistryUtil.register("caro_invictus", FabricEntityTypeBuilder.create(SpawnGroup.MONSTER, CaroInvictusEntity::new).dimensions(EntityDimensions.fixed(0.8f, 1.8f)).build());
        FabricDefaultAttributeRegistry.register((EntityType<CaroInvictusEntity>) CARO_INVICTUS.value(), CaroInvictusEntity.createMobAttributes().add(EntityAttributes.GENERIC_ARMOR, 10d).add(EntityAttributes.GENERIC_MAX_HEALTH, 100d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 8d).add(EntityAttributes.GENERIC_SCALE, 1d).build());
    }

    public static EntityType<?> get(String id) {
        return Registries.ENTITY_TYPE.get(ExclusionZone.id(id));
    }
}
