package com.kd8lvt.exclusionzone.api.helpers;

import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public final class WorldHelper {
    private final static ArrayList<ChunkPos> surroundingChunksGenerated = new ArrayList<>();
    public static boolean surroundingChunksGenerated(World world, ChunkPos center) {
        if (surroundingChunksGenerated.contains(center)) return true;
        for (int x=-1;x<=1;x++) for (int z=-1;z<=1;z++) if (world.getChunk(center.x+x,center.z+z) == null) return false;
        surroundingChunksGenerated.add(center);
        return true;
    }
}
