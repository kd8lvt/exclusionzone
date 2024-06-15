package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Entities.CaroInvictusEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class ModEntities {
    public static EntityType<CaroInvictusEntity> CARO_INVICTUS = FabricEntityTypeBuilder.create(SpawnGroup.MONSTER,CaroInvictusEntity::new).dimensions(EntityDimensions.fixed(0.8f,1.8f)).build();
    public static void register() {
        Registry.register(Registries.ENTITY_TYPE,ExclusionZone.id("caro_invictus"),CARO_INVICTUS);
        FabricDefaultAttributeRegistry.register(ModEntities.CARO_INVICTUS, CaroInvictusEntity.createMobAttributes().add(EntityAttributes.GENERIC_ARMOR,10d).add(EntityAttributes.GENERIC_MAX_HEALTH,100d).add(EntityAttributes.GENERIC_ATTACK_DAMAGE,8d).add(EntityAttributes.GENERIC_SCALE,1d).build());
    }

}
