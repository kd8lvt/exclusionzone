package com.kd8lvt.exclusionzone.init.items.Dolls;

import com.kd8lvt.exclusionzone.init.items.Doll;
import com.kd8lvt.exclusionzone.registry.ModSounds;

public class GirlDoll extends Doll {
    public GirlDoll() {
        super(ModSounds.get("item.doll.squeak"),ModSounds.get("item.doll.chicken"),0.2, "rubber chicken");
    }
}
