package com.kd8lvt.exclusionzone.content.entity;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.content.block.util.RiftLeg;
import com.kd8lvt.exclusionzone.content.block.util.RiftShape;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

public class RiftBE extends BlockEntity {
    private static final Random random = Random.create();
    private final RiftShape shape;

    public RiftBE(BlockPos pos, BlockState state) {
        super(ModBlockEntities.get("rift"), pos, state);
        random.setSeed(pos.asLong());
        random.skip((int) (pos.getX()*pos.getY()/(pos.getZ()+1)+Math.round(pos.toCenterPos().distanceTo(new Vec3d(0,0,0)))));
        shape = new RiftShape(random);
    }

    public static <T extends BlockEntity> BlockEntityTicker<T> tick() {
        return (world1, pos, state1, be) -> {
            ((RiftBE)be).shape.wiggle();
            if (world1.isClient()) return;
            ServerWorld world = (ServerWorld)world1;
            summonParticles(world,pos);
            world.iterateEntities().forEach((Entity entity)->{
                if (pos.isWithinDistance(entity.getPos(),5)) {
                    pullInEntities(pos,entity,(RiftBE)be);
                    damageEntities(pos,entity);
                }
            });
        };
    }

    private static void pullInEntities(BlockPos pos, Entity entity, RiftBE ignoredBe) {
        if (entity instanceof ItemEntity) return;
        Vec3d ePos = entity.getPos();
        Vec3d bPos = pos.toCenterPos();
        Vec3d dir = ePos.subtract(bPos).normalize();
        entity.addVelocityInternal(dir.negate().multiply(0.05*(Math.sqrt(5-ePos.distanceTo(bPos)))));
        entity.velocityModified=true;
    }
    private static void damageEntities(BlockPos pos, Entity entity) {
        if (pos.isWithinDistance(entity.getPos(),1) && !entity.isInvulnerableTo(entity.getDamageSources().magic()) && !(entity instanceof ItemEntity)) {
            entity.damage(entity.getDamageSources().magic(),1f);
        }
    }

    private static void summonParticles(World world, BlockPos pos) {
        for (int legIdx = 0; ((RiftBE) Objects.requireNonNull(world.getBlockEntity(pos))).shape.legs.containsKey(legIdx); legIdx++) {
            RiftLeg leg = ((RiftBE) Objects.requireNonNull(world.getBlockEntity(pos))).shape.legs.get(legIdx);
            for (double i=0d;i<1d;i+=0.05d) {
                ExclusionZone.runCommand("execute in "+world.getDimensionEntry().getIdAsString()+" positioned "+pos.toCenterPos().x+" "+pos.toCenterPos().y+" "+pos.toCenterPos().z+" run particle dust{color:[0f,"+(1-(i))/2+"f,"+(1-(i))/2+"f],scale:1.0} ~"+leg.endpoint.x*i+" ~"+leg.endpoint.y*i+" ~"+leg.endpoint.z*i+" 0 0 0 0 1 force");
            }
        }
    }

    @Override
    public void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtCompound shapeNBT = nbt.getCompound("shape");
        NbtList nbtEndpoints = shapeNBT.getList("endpoints",NbtElement.COMPOUND_TYPE);
        ArrayList<Vec3d> endpoints = new ArrayList<>();
        for (int i = 0; i< nbtEndpoints.size(); i+=3) {
            NbtCompound endpoint = (NbtCompound)nbtEndpoints.get(i);
            endpoints.add(new Vec3d(endpoint.getDouble("x"), endpoint.getDouble("y"), endpoint.getDouble("z")));
        }
        if (endpoints.size() >= shape.legs.size()) shape.updateEndpoints(endpoints);
        super.readNbt(nbt, registryLookup);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        NbtCompound shapeNBT = new NbtCompound();
        NbtList endpointNBT = new NbtList();
        for (int legIdx = 0; shape.legs.containsKey(legIdx); legIdx++) {
            NbtCompound vec = new NbtCompound();
            vec.putDouble("x",shape.legs.get(legIdx).endpoint.x);
            vec.putDouble("y",shape.legs.get(legIdx).endpoint.y);
            vec.putDouble("z",shape.legs.get(legIdx).endpoint.z);
            endpointNBT.add(vec);
        }
        shapeNBT.put("endpoints",endpointNBT);
        nbt.put("shape",shapeNBT);
        super.writeNbt(nbt,registryLookup);
    }
}
