package com.kd8lvt.exclusionzone.content.item.Dolls;

import com.kd8lvt.exclusionzone.content.item.Doll;
import com.kd8lvt.exclusionzone.registry.ModSounds;

public class VillagerDoll extends Doll {
    public VillagerDoll() {
        super(ModSounds.get("item.doll.squeak"),ModSounds.get("item.doll.chicken"),0.2, "rubber chicken");
    }
}