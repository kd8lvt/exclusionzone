package com.kd8lvt.exclusionzone.item.Dolls;

import com.kd8lvt.exclusionzone.item.Doll;
import com.kd8lvt.exclusionzone.registry.ModSounds;

public class BoyDoll extends Doll {
    public BoyDoll() {
        super(ModSounds.get("item.doll.squeak"),ModSounds.get("item.doll.chicken"),0.2, "rubber chicken");
    }
}