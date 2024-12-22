package com.kd8lvt.exclusionzone.content.item;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;

public class FuelItem extends Item {
    public FuelItem(float numItemsSmelted) {
        super(new Settings());
        FuelRegistry.INSTANCE.add(this,(int)Math.floor(numItemsSmelted*200));
    }
}
