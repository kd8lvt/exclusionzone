package com.kd8lvt.exclusionzone.content.entity;

import com.kd8lvt.exclusionzone.content.block.util.transaction.TransactionTypes;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import net.fabricmc.fabric.api.transfer.v1.context.ContainerItemContext;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidConstants;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidStorage;
import net.fabricmc.fabric.api.transfer.v1.fluid.FluidVariant;
import net.fabricmc.fabric.api.transfer.v1.item.InventoryStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemStorage;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.fabricmc.fabric.api.transfer.v1.storage.Storage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleSlotStorage;
import net.fabricmc.fabric.api.transfer.v1.storage.base.SingleVariantStorage;
import net.fabricmc.fabric.api.transfer.v1.transaction.Transaction;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.ArrayList;

public class FluidPipeBE extends BlockEntity {
    public final SingleVariantStorage<FluidVariant> fluid = new SingleVariantStorage<>() {
        @Override
        protected FluidVariant getBlankVariant() {
            return FluidVariant.blank();
        }

        @Override
        protected long getCapacity(FluidVariant variant) {
            if (this.variant != variant) return 0;
            else return FluidConstants.BUCKET/81;
        }

        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    public FluidPipeBE(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @SuppressWarnings("unused")
    public void tryUpdate(World world, BlockPos pos, BlockState state) {
        if (state.get(Properties.POWER) > 0) {
            if (!state.get(Properties.POWERED)) {
                this.tryUpdateFluid(world,pos,state);
                this.tryUpdatePower(world, pos, state);
            }
        }
        world.setBlockState(pos,state.with(Properties.POWERED,world.getBlockState(pos).get(Properties.POWER) > 0));
    }

    public ArrayList<Direction> getValidNeighbors(World world, BlockPos pos) {
        ArrayList<Direction> ret = new ArrayList<>();

        for (Direction dir : Direction.values()) {
            BlockPos pOff = pos.offset(dir);
            if (!(validConnection(world.getBlockEntity(pOff)))) continue;
            ret.add(dir);
        }
        return ret;
    }

    public void tryUpdatePower(World world, BlockPos pos, BlockState state) {
        world.setBlockState(pos,state.with(Properties.POWER,world.getReceivedRedstonePower(pos)-1));
    }

    @SuppressWarnings({"unchecked", "unused"})
    public void tryUpdateFluid(World world, BlockPos pos, BlockState state) {
        if (this.fluid.variant == FluidVariant.of(Fluids.EMPTY)) {
            if (this.fluid.amount != 0) this.fluid.amount = 0;
            return;
        }
        for (Direction dir : getValidNeighbors(world,pos)) {
            if (this.fluid.amount <= 0) return;
            BlockPos pOff = pos.offset(dir);
            BlockEntity be = world.getBlockEntity(pOff);
            Storage<?> other;
            other = ItemStorage.SIDED.find(world,pOff,dir.getOpposite());
            if (other != null) {
                ((Storage<ItemVariant>)other).nonEmptyIterator().forEachRemaining(storage->{
                    ItemStack stack = storage.getResource().toStack();
                    Storage<FluidVariant> itemFluidStorage = FluidStorage.ITEM.find(stack, ContainerItemContext.ofSingleSlot((SingleSlotStorage<ItemVariant>)storage));
                    if (itemFluidStorage == null) return;
                    tryTransferToFluidStorage(itemFluidStorage,be);
                });
                return;
            }

            other = FluidStorage.SIDED.find(world,pOff,dir.getOpposite());
            if (other != null) {
                tryTransferToFluidStorage((Storage<FluidVariant>)other,be);
            }
        }
    }

    public void tryTransferToFluidStorage(Storage<FluidVariant> fluidStorage,BlockEntity be) {
        if (fluidStorage == null || !fluidStorage.supportsInsertion()) return;
        TransactionTypes.INTEGER transferValue = new TransactionTypes.INTEGER(Math.min((int)fluid.amount,1000));
        try (Transaction transaction = Transaction.openOuter()) {
            long insertAmount = fluidStorage.insert(fluid.variant, transferValue.get(), transaction);
            transferValue.increment((int) -insertAmount, transaction);
            transaction.commit();
        }
        if (transferValue.get() != Math.min(fluid.amount, 1000)) {
            fluid.amount = transferValue.get();
            this.markDirty();
            be.markDirty();
        }
    }

    public static boolean validConnection(BlockEntity be) {
        return (be instanceof InventoryStorage || be instanceof FluidPipeBE);
    }

    @Override
    protected void readNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.readNbt(nbt, registryLookup);
        NbtCompound fluidComp = nbt.getCompound("fluid");
        fluid.variant = FluidVariant.of(Registries.FLUID.get(Identifier.of(fluidComp.getString("variant"))));
        fluid.amount = fluidComp.getLong("amount");
    }

    @Override
    protected void writeNbt(NbtCompound nbt, RegistryWrapper.WrapperLookup registryLookup) {
        super.writeNbt(nbt, registryLookup);
        NbtCompound fluidComp = new NbtCompound();
        fluidComp.putString("variant",fluid.variant.getRegistryEntry().getKey().orElse(Registries.FLUID.getKey(Fluids.EMPTY).orElseThrow()).getValue().toString());
        fluidComp.putLong("amount",fluid.amount);
        nbt.put("fluid",fluidComp);
    }

    public FluidPipeBE(BlockPos pos, BlockState state) {
        this(ModBlockEntities.get("fluid_pipe"),pos,state);
    }
}
