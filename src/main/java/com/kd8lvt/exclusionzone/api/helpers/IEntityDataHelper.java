package com.kd8lvt.exclusionzone.api.helpers;

import com.kd8lvt.exclusionzone.api.IEntityDataSaver;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtTypes;

import static com.kd8lvt.exclusionzone.ExclusionZone.LOGGER;

public interface IEntityDataHelper {
    static <T extends NbtElement> T getOrDefault(LivingEntity entity, String key, T default_) {
        NbtCompound comp = getAll(entity);
        if (!comp.contains(key)) set(entity,key,default_);

        if (comp.getType(key) != default_.getType()) {
            LOGGER.warn(createInvalidEntityDataReport(entity,key,default_));
            set(entity,key,default_);
        }

        //noinspection unchecked //i literally am checking it, you're just blind intellisense.
        return (T) comp.get(key);
    }

    @SuppressWarnings("DataFlowIssue") //In 100% of uses, this is not an issue.
    private static <T extends NbtElement> String createInvalidEntityDataReport(LivingEntity entity, String key, T default_) {
        NbtCompound comp = getAll(entity);

        String ret = "Found wrong NBT type \"" + typeName(comp.getType(key)) + "\" ";
              ret += "(expected \"" + typeName(default_.getType()) + "\") ";
              ret += "for NBT key \"exclusionzone." + key + "\", ";
              ret += "on " + entity.getDisplayName().getString() + " ";
              ret += "(UUID:" + entity.getUuidAsString() + ") ";
              ret += "-- destructively replacing it with the default value " + default_ + "!";

        return ret;
    }

    private static String typeName(byte type) {
        return NbtTypes.byId(type).getCrashReportName();
    }

    static <T extends NbtElement> void set(LivingEntity entity,String key,T value) {
       NbtCompound comp = getAll(entity);
       comp.put(key,value);
       ((IEntityDataSaver)entity).exclusionzone$save(comp);
    }

    static NbtCompound getAll(LivingEntity entity) {
        return ((IEntityDataSaver)entity).exclusionzone$read();
    }
}
