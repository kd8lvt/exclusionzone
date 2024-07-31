package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.init.Entities.CaroInvictusEntity;
import com.kd8lvt.exclusionzone.init.registries.ModEntityRegistry;
import net.minecraft.entity.EntityType;

@SuppressWarnings("unchecked")
public class ModEntities {
    public static EntityType<CaroInvictusEntity> CARO_INVICTUS;

    public static void ready() {
        CARO_INVICTUS = (EntityType<CaroInvictusEntity>) ModEntityRegistry.CARO_INVICTUS.value();
    }

}
