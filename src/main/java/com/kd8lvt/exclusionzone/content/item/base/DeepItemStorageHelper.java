package com.kd8lvt.exclusionzone.content.item.base;

import com.kd8lvt.exclusionzone.registry.ModDataComponents;
import net.minecraft.component.ComponentChanges;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.NbtComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

import java.util.Objects;

@SuppressWarnings("unchecked")
public final class DeepItemStorageHelper {
    public static NbtComponent getComponentFromStack(ItemStack stack) {
        NbtCompound def = new NbtCompound();
        def.put("stored",new NbtList());
        NbtComponent defcomp = NbtComponent.of(def);
        return ((NbtComponent)stack.getComponents().getOrDefault(ModDataComponents.get("deep_storage"),defcomp));
    }

    public static Item getStoredItem(ItemStack stack, int idx) {
        return Registries.ITEM.get(Identifier.of(getComponentFromStack(stack).getNbt().getList("stored",NbtList.COMPOUND_TYPE).getCompound(idx).getString("item")));
    }

    public static int getStoredCount(ItemStack stack, int idx) {
        return getComponentFromStack(stack).getNbt().getList("stored",NbtList.COMPOUND_TYPE).getCompound(idx).getInt("count");
    }

    /**
     * @return Remainder in stackToStore after storing. 0 if the stack should be empty.
     */
    public static int addToStorage(ItemStack stack, ItemStack stackToStore, int maxStoredTypes, int maxStorageSize, boolean voidItems) {
        NbtList comp = getComponentFromStack(stack).copyNbt().getList("stored",NbtList.COMPOUND_TYPE);
        boolean found = false;
        int leftover = stackToStore.getCount();
        for (int i=0;i<comp.size();i++) {
            if (getStoredItem(stack,i) != stackToStore.getItem() && !Objects.equals(comp.getCompound(i).getString("item"), "minecraft:air")) continue;
            found=true;
            int storedCount = getStoredCount(stack,i);
            int postStoreCount = storedCount + stackToStore.getCount();
            if (postStoreCount > maxStorageSize) {
                if (voidItems) {
                    NbtCompound a = comp.getCompound(i);
                    a.putInt("count",maxStorageSize);
                    if (a.getString("item").equals("minecraft:air")) a.putString("item",Registries.ITEM.getId(stackToStore.getItem()).toString());
                    comp.set(i,a);
                    leftover=0;
                } else {
                    NbtCompound a = comp.getCompound(i);
                    a.putInt("count",maxStorageSize);
                    if (a.getString("item").equals("minecraft:air")) a.putString("item",Registries.ITEM.getId(stackToStore.getItem()).toString());
                    comp.set(i,a);
                    leftover = (comp.getCompound(i).getInt("count")+stackToStore.getCount())-maxStorageSize;
                }
            } else {
                NbtCompound a = comp.getCompound(i);
                a.putInt("count",postStoreCount);
                if (a.getString("item").equals("minecraft:air")) a.putString("item",Registries.ITEM.getId(stackToStore.getItem()).toString());
                comp.set(i,a);
                leftover=0;
            }
            break;
        }
        if (!found && comp.size() < maxStoredTypes) {
            NbtCompound newCompound = new NbtCompound();
            newCompound.putString("item",Registries.ITEM.getId(stackToStore.getItem()).toString());
            newCompound.putInt("count",stackToStore.getCount());
            comp.add(newCompound);
            leftover=0;
        }
        save(stack,comp);
        return leftover;
    }

    public static void save(ItemStack stack, NbtList comp) {
        NbtCompound newCompound = new NbtCompound();
        newCompound.put("stored",comp);
        stack.applyChanges(ComponentChanges.builder().remove(ModDataComponents.get("deep_storage")).add((ComponentType<? super NbtComponent>) ModDataComponents.get("deep_storage"),NbtComponent.of(newCompound)).build());
    }

    public static ItemStack takeStackFromStorage(ItemStack stack,int idx) {
        NbtList comp = getComponentFromStack(stack).copyNbt().getList("stored",NbtList.COMPOUND_TYPE);
        if (comp.isEmpty()) return ItemStack.EMPTY;
        NbtCompound selected = comp.getCompound(idx);
        Item selectedItem = Registries.ITEM.get(Identifier.of(selected.getString("item")));
        ItemStack ret = new ItemStack(selectedItem,Math.min(selectedItem.getMaxCount(),selected.getInt("count")));
        selected.putInt("count",selected.getInt("count")-ret.getCount());
        comp.set(idx,selected);
        if (comp.getCompound(idx).getInt("count") <= 0) comp.remove(idx);
        save(stack,comp);
        return ret;
    }

    public static ItemStack takeOneFromStorage(ItemStack stack,int idx) {
        NbtList comp = getComponentFromStack(stack).copyNbt().getList("stored",NbtList.COMPOUND_TYPE);
        NbtCompound selected = comp.getCompound(idx);
        Item selectedItem = Registries.ITEM.get(Identifier.of(selected.getString("item")));
        ItemStack ret = new ItemStack(selectedItem,Math.min(1,selected.getInt("count")));
        selected.putInt("count",selected.getInt("count")-ret.getCount());
        comp.set(idx,selected);
        if (comp.getCompound(idx).getInt("count") <= 0) comp.remove(idx);
        save(stack,comp);
        return ret;
    }

    public static int indexOf(ItemStack stack, Identifier id) {
        NbtList comp = getComponentFromStack(stack).copyNbt().getList("stored",NbtList.COMPOUND_TYPE);
        for (int i=0;i<comp.size();i++) {
            if (Identifier.of(comp.getCompound(i).getString("item")) == id) return i;
        }
        return -1;
    }

    public static int countOf(ItemStack stack, Identifier id) {
        int idx = indexOf(stack,id);
        if (idx == -1) return -1;
        return getStoredCount(stack,idx);
    }

    public static void decrementStorage(NbtList comp, int idx, int count) {
        NbtCompound newComp = comp.getCompound(idx);
        comp.remove(newComp);
        int newCount = newComp.getInt("count")-count;
        newComp.remove("count");
        newComp.putInt("count", newCount);
        comp.add(newComp);
        if (comp.getCompound(idx).getInt("count") <= 0) comp.remove(idx);
    }

    public static void decrementStorage(NbtList comp, int idx) {
        decrementStorage(comp,idx,1);
    }
}
