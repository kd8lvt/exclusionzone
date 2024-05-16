package com.kd8lvt.exclusionzone.init.Blocks.util;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityPose;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.util.math.BlockPos;
import org.apache.commons.lang3.tuple.Pair;

import java.util.UUID;

import static net.minecraft.state.property.Properties.FACING;

public class ExclusionZoneFakePlayer extends FakePlayer {

    public Pair<BlockPos,Float> blockBreakingProgress;

    public ExclusionZoneFakePlayer(ServerWorld world) {
        super(world, new GameProfile(UUID.randomUUID(),"ExclusionZone_FP_#%s".formatted(world.random.nextInt(1000))));
    }

    public RaycastContext getRaycastContext(BlockState state) {
        Vec3d rayOrigin = getPos().add(Vec3d.of(state.get(FACING).getVector()).normalize().multiply(2));
        Vec3d rayTarget = rayOrigin.add(Vec3d.of(state.get(FACING).getVector()).normalize());
        return new RaycastContext(rayOrigin,rayTarget, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE,this);
    }

   public static float yawFromVector(Vec3d vec) {
        return (float) ((3*Math.PI/2+Math.atan2(vec.z,vec.x))/Math.PI*100);
   }

   public static float pitchFromVector(Vec3d vec) {
        return (float) ((Math.acos(vec.y))/Math.PI*100);
   }
}
