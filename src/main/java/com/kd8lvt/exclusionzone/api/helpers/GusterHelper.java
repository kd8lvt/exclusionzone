package com.kd8lvt.exclusionzone.api.helpers;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtFloat;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public final class GusterHelper {
    private static final String KEY = "guster";
    public static void entityTick(LivingEntity entity) {
        if (!entity.isFallFlying()) return;
        if (getOrDefault(entity,0f) <= 0) return;
        World world = entity.getWorld();
        Vec3d look = entity.getRotationVector();
        entity.addVelocityInternal(look.normalize().multiply(2).subtract(entity.getVelocity()));
        entity.velocityModified=true;
        Vec3d pos = entity.getPos();
        world.addParticle(ParticleTypes.GUST,pos.x,pos.y,pos.z,0,0,0);
        IEntityDataHelper.set(entity,KEY,NbtFloat.of(IEntityDataHelper.getOrDefault(entity,KEY,NbtFloat.of(1)).floatValue()-1));
    }
    public static float getOrDefault(LivingEntity entity, float default_) {
        return IEntityDataHelper.getOrDefault(entity,KEY, NbtFloat.of(default_)).floatValue();
    }
    public static void refreshTimer(LivingEntity entity) {
        IEntityDataHelper.set(entity,KEY,NbtFloat.of(20));
    }
}
