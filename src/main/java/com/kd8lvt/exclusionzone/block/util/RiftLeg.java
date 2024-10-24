package com.kd8lvt.exclusionzone.block.util;

import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;

public class RiftLeg {
    public Vec3d endpoint;
    public Vec3d originalEndpoint;
    public final Random random;

    public RiftLeg(Random _random) {
        random = _random;
        endpoint = new Vec3d(genCoordBetween(-4,4),genCoordBetween(-4,4),genCoordBetween(-4,4));
        originalEndpoint = endpoint;
    }

    @SuppressWarnings("SameParameterValue")
    private float genCoordBetween(int start, int end) {
        if (start < end) return random.nextFloat()*random.nextBetween(start,end);
        if (end < start) return random.nextFloat()*random.nextBetween(end,start);
        return 0;
    }
}
