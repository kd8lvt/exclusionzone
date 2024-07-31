package com.kd8lvt.exclusionzone.init.Blocks.util;

import com.mojang.authlib.GameProfile;
import net.fabricmc.fabric.api.entity.FakePlayer;
import net.minecraft.block.BlockState;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.apache.commons.lang3.tuple.Pair;

import java.util.UUID;

import static net.minecraft.state.property.Properties.FACING;

public class ExclusionZoneFakePlayer extends FakePlayer {

    public Pair<BlockPos,Float> blockBreakingProgress;

    public ExclusionZoneFakePlayer(ServerWorld world) {
        super(world, new GameProfile(UUID.randomUUID(),"ExclusionZone_FP_#%s".formatted(world.random.nextInt(1000))));
    }

    @SuppressWarnings("unused")
    public RaycastContext getRaycastContext(BlockState state) {
        Vec3d rayOrigin = getPos().add(Vec3d.of(state.get(FACING).getVector()).normalize().multiply(2));
        Vec3d rayTarget = rayOrigin.add(Vec3d.of(state.get(FACING).getVector()).normalize());
        return new RaycastContext(rayOrigin,rayTarget, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE,this);
    }

}
