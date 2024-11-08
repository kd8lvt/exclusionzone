package com.kd8lvt.exclusionzone.api;

import net.minecraft.entity.LivingEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.UUID;

public class GusterTracker {
    public static final HashMap<UUID,Integer> tracked = new HashMap<>();
    public static void entityTick(LivingEntity entity) {
        UUID uuid = entity.getUuid();
        if (!tracked.containsKey(uuid)) return;
        tracked.compute(uuid,(uuid2,timer)->timer-1);
        if (tracked.get(uuid) <= 0) {
            tracked.remove(uuid);
            return;
        }
        if (!entity.isFallFlying()) return;
        World world = entity.getWorld();
        Vec3d look = entity.getRotationVector();
        entity.addVelocityInternal(look.normalize().multiply(2).subtract(entity.getVelocity()));
        entity.velocityModified=true;
        Vec3d pos = entity.getPos();
        world.addParticle(ParticleTypes.GUST,pos.x,pos.y,pos.z,0,0,0);
    }
    public static void refreshTimer(LivingEntity entity) {
        tracked.computeIfPresent(entity.getUuid(),(uuid,val)->20);
        tracked.putIfAbsent(entity.getUuid(),20);
    }
}
