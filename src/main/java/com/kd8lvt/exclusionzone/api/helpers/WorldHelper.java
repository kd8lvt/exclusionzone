package com.kd8lvt.exclusionzone.api.helpers;

import net.minecraft.server.command.ForceLoadCommand;
import net.minecraft.server.world.ChunkTicketType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

import java.util.ArrayList;

public final class WorldHelper {
    private final static ArrayList<ChunkPos> surroundingChunksGenerated = new ArrayList<>();
    public static boolean surroundingChunksGenerated(World world, ChunkPos center) {
        if (surroundingChunksGenerated.contains(center)) return true;
        for (int x=-1;x<=1;x++) for (int z=-1;z<=1;z++) if (world.getChunk(center.x+x,center.z+z) == null) return false;
        surroundingChunksGenerated.add(center);
        return true;
    }

    public static boolean surroundingChunksLoaded(World world, ChunkPos center) {
        for (int x=-1;x<=1;x++) for (int z=-1;z<=1;z++) if (!world.isChunkLoaded(center.x+x,center.z+z)) return false;
        return true;
    }

    public static void loadSurroundingChunks(ServerWorld world, ChunkPos center) {
        world.getChunkManager().addTicket(ChunkTicketType.POST_TELEPORT,center,2,5);
    }
}
