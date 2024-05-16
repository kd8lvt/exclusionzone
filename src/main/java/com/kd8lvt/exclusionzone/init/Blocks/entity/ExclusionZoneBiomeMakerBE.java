package com.kd8lvt.exclusionzone.init.Blocks.entity;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Timer;
import java.util.TimerTask;

public class ExclusionZoneBiomeMakerBE extends BlockEntity {
    public ExclusionZoneBiomeMakerBE(BlockPos pos, BlockState state) {
        super(ModBlocks.EXCLUSION_ZONE_BIOME_MAKER_BE,pos,state);
        World world = ExclusionZone.Server.getOverworld();
        String cmd = ("execute in " + world.getDimensionEntry().getIdAsString() + " positioned " + pos.getX() + " " + pos.getY() + " " + pos.getZ() + " run fillbiome ~10 ~10 ~10 ~-10 ~-10 ~-10 exclusionzone:exclusion_zone");

        //Give the server a couple seconds to generate the rest of the adjacent chunks
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                ExclusionZone.runCommand(cmd); //Run a fillbiome command - because I'm too lazy to figure out how to do it properly
                world.setBlockState(pos, Blocks.AIR.getDefaultState()); //Replace yourself with air
            }
        },2000);
    }
}
