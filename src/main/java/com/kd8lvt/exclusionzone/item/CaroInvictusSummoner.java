package com.kd8lvt.exclusionzone.item;

import com.kd8lvt.exclusionzone.entity.CaroInvictusEntity;
import com.kd8lvt.exclusionzone.registry.ModEntities;
import com.kd8lvt.exclusionzone.registry.ModSounds;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BeaconBlockEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

public class CaroInvictusSummoner extends Artifact {
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        World w = context.getWorld();
        BlockState state = w.getBlockState(context.getBlockPos());
        if (state != Blocks.BEACON.getDefaultState()) return super.useOnBlock(context);
        if (!Objects.requireNonNull(context.getPlayer()).isSneaking()) return super.useOnBlock(context);
        if (((BeaconBlockEntity) Objects.requireNonNull(w.getBlockEntity(context.getBlockPos()))).getBeamSegments().isEmpty()) return super.useOnBlock(context);

        w.playSoundAtBlockCenter(context.getBlockPos().up(), ModSounds.get("mob.caro_invictus.music"), SoundCategory.MASTER,1.0f,1.0f,true);

        if (w.isClient) return super.useOnBlock(context);
        ServerWorld world = (ServerWorld)w;
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                CaroInvictusEntity ent = (CaroInvictusEntity) ModEntities.get("caro_invictus").spawn(world,context.getBlockPos().offset(Direction.UP,1), SpawnReason.MOB_SUMMONED);
                world.createExplosion(ent,context.getBlockPos().toCenterPos().getX(),context.getBlockPos().up().toCenterPos().getY(),context.getBlockPos().toCenterPos().getZ(),10f,World.ExplosionSourceType.TRIGGER);
            }
        }, 12000);

        return ActionResult.success(true);
    }
}
