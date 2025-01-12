package com.kd8lvt.exclusionzone.api.helpers;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeCoords;
import net.minecraft.world.biome.source.BiomeSupplier;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkStatus;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static com.kd8lvt.exclusionzone.api.CommonConstants.SERVER;

//May or may not just be the majority of /fillbiome co-opted for my own use
public class BiomeHelper {
    private static int convertCoordinate(int coordinate) {
        return BiomeCoords.toBlock(BiomeCoords.fromBlock(coordinate));
    }

    private static BlockPos convertPos(BlockPos pos) {
        return new BlockPos(convertCoordinate(pos.getX()), convertCoordinate(pos.getY()), convertCoordinate(pos.getZ()));
    }

    public static BiomeSupplier createBiomeSupplier(
            MutableInt counter, Chunk chunk, BlockBox box, RegistryEntry<Biome> biome, Predicate<RegistryEntry<Biome>> filter
    ) {
        Biome defaultBiome = SERVER.getRegistryManager().get(RegistryKeys.BIOME).get(Identifier.ofVanilla("plains"));
        return (x, y, z, noise) -> {
            int i = BiomeCoords.toBlock(x);
            int j = BiomeCoords.toBlock(y);
            int k = BiomeCoords.toBlock(z);
            RegistryEntry<Biome> registryEntry2 = chunk.getBiomeForNoiseGen(x, y, z);
            if (box.contains(i, j, k) && filter.test(registryEntry2)) {
                counter.increment();
                return biome;
            } else if (registryEntry2 != null) {
                return registryEntry2;
            } else {
                return RegistryEntry.of(defaultBiome);
            }
        };
    }

    public static void fill(
            ServerWorld world, BlockPos from, BlockPos to, RegistryEntry<Biome> biome, Predicate<RegistryEntry<Biome>> filter
    ) {
        BlockPos blockPos = convertPos(from);
        BlockPos blockPos2 = convertPos(to);
        BlockBox blockBox = BlockBox.create(blockPos, blockPos2);
        List<Chunk> list = new ArrayList<>();

        for (int k = ChunkSectionPos.getSectionCoord(blockBox.getMinZ()); k <= ChunkSectionPos.getSectionCoord(blockBox.getMaxZ()); k++) {
            for (int l = ChunkSectionPos.getSectionCoord(blockBox.getMinX()); l <= ChunkSectionPos.getSectionCoord(blockBox.getMaxX()); l++) {
                Chunk chunk = world.getChunk(l, k, ChunkStatus.FULL, false);
                if (chunk == null) {
                    continue;
                }

                list.add(chunk);
            }
        }

        MutableInt mutableInt = new MutableInt(0);

        for (Chunk chunk : list) {
            chunk.populateBiomes(createBiomeSupplier(mutableInt, chunk, blockBox, biome, filter), world.getChunkManager().getNoiseConfig().getMultiNoiseSampler());
            chunk.setNeedsSaving(true);
        }
        //TODO: investigate crash with polymc
        //https://github.com/kd8lvt/exclusionzone/issues/6
        world.getChunkManager().chunkLoadingManager.sendChunkBiomePackets(list);
    }

    public static void fillRadius(ServerWorld world, BlockPos center, int radius, RegistryEntry<Biome> biome, Predicate<RegistryEntry<Biome>> filter) {
        fill(world,center.south(radius).west(radius).down(radius),center.north(radius).east(radius).up(radius),biome,filter);
    }

    public static void fill(ServerWorld world, BlockPos from, BlockPos to, RegistryEntry<Biome> biome) {
        fill(world,from,to,biome,biomeTRoReplace->true);
    }

    public static void fillRadius(ServerWorld world, BlockPos center, int radius, RegistryEntry<Biome> biome) {
        fillRadius(world,center,radius,biome,biomeToReplace->true);
    }
}
