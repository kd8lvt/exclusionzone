package com.kd8lvt.exclusionzone.api.helpers;

import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.command.FillBiomeCommand;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.source.BiomeSupplier;
import net.minecraft.world.chunk.Chunk;
import org.apache.commons.lang3.mutable.MutableInt;

import java.util.function.Predicate;

//May or may not just be a wrapper on top of /fillbiome
public class BiomeHelper {
    private static int convertCoordinate(int coordinate) {
        return FillBiomeCommand.convertCoordinate(coordinate);
    }

    private static BlockPos convertPos(BlockPos pos) {
        return FillBiomeCommand.convertPos(pos);
    }

    public static BiomeSupplier createBiomeSupplier(
            MutableInt counter, Chunk chunk, BlockBox box, RegistryEntry<Biome> biome, Predicate<RegistryEntry<Biome>> filter
    ) {
        return FillBiomeCommand.createBiomeSupplier(counter,chunk,box,biome,filter);
    }

    public static void fill(
            ServerWorld world, BlockPos from, BlockPos to, RegistryEntry<Biome> biome, Predicate<RegistryEntry<Biome>> filter
    ) {
        FillBiomeCommand.fillBiome(world,from,to,biome);
    }

    public static void fillCenteredRect(ServerWorld world, BlockPos center, int radius, RegistryEntry<Biome> biome, Predicate<RegistryEntry<Biome>> filter) {
        fill(world,center.south(radius).west(radius).down(radius),center.north(radius).east(radius).up(radius),biome,filter);
    }

    public static void fill(ServerWorld world, BlockPos from, BlockPos to, RegistryEntry<Biome> biome) {
        fill(world,from,to,biome,biomeTRoReplace->true);
    }

    public static void fillCenteredRect(ServerWorld world, BlockPos center, int radius, RegistryEntry<Biome> biome) {
        fillCenteredRect(world,center,radius,biome, biomeToReplace->true);
    }
}
