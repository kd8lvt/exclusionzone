package com.kd8lvt.exclusionzone.block.util;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RiftShape {
    public final HashMap<Integer, RiftLeg> legs = new HashMap<>();
    final Random random;
    public RiftShape(Random _random) {
        random = _random;
        int size = random.nextBetween(4,8);
        for (int i=0;i<size;i++) {
            legs.put(i,new RiftLeg(random));
        }
    }

    public void updateEndpoints(ArrayList<Vec3d> updateTo) {
        if (updateTo.size() < legs.size()) throw new Error("New endpoint list is smaller than leg count!");
        for (int idx = 0; legs.containsKey(idx); idx++) {
            RiftLeg leg = legs.get(idx);
            leg.originalEndpoint = updateTo.get(idx);
            leg.endpoint = updateTo.get(idx);
            legs.put(idx,leg);
        }
    }

    public void wiggle() {
        for (Map.Entry<Integer,RiftLeg> entry: legs.entrySet()) {
            if (random.nextFloat() < 0.3) {
                RiftLeg val = entry.getValue();
                Vec3d preEdit = val.endpoint;
                val.endpoint = val.endpoint.addRandom(random,0.1f*(random.nextBoolean()?1:-1));
                if (val.endpoint.distanceTo(val.originalEndpoint) > 1) val.endpoint = preEdit;
                entry.setValue(val);
            }
        }
    }
}
