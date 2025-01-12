package com.kd8lvt.exclusionzone.api;

import net.minecraft.nbt.NbtCompound;

public interface IEntityDataSaver {
    NbtCompound exclusionzone$read();
    void exclusionzone$save(NbtCompound newData);
}
