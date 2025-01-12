package com.kd8lvt.exclusionzone.mixin;

import com.kd8lvt.exclusionzone.api.IEntityDataSaver;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public abstract class EntityDataSaverMixin implements IEntityDataSaver {
    @Unique
    private NbtCompound persistentData;

    @Override
    public NbtCompound exclusionzone$read() {
        if (persistentData == null) {
            this.persistentData = new NbtCompound();
        }
        return persistentData;
    }

    @Override
    public void exclusionzone$save(NbtCompound newData) {
        this.persistentData=newData;
    }

    @Inject(method="readNbt", at=@At("HEAD"))
    private void readNbt(NbtCompound nbt, CallbackInfo ci) {
        if (nbt.contains("exclusionzone", NbtElement.COMPOUND_TYPE)) {
            persistentData = nbt.getCompound("exclusionzone");
        }
    }

    @Inject(method="writeNbt", at=@At("HEAD"))
    private void writeNbt(NbtCompound nbt, CallbackInfoReturnable<NbtCompound> cir) {
        if (persistentData != null) {
            nbt.put("exclusionzone",persistentData);
        }
    }
}
