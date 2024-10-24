package com.kd8lvt.exclusionzone.block;

import com.kd8lvt.exclusionzone.block.bases.EZBlockEntityBlock;
import com.kd8lvt.exclusionzone.block.entity.GradientGlassBE;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GradientGlass extends EZBlockEntityBlock<GradientGlassBE> {
    @SuppressWarnings("unchecked")
    protected GradientGlass(Settings settings) {
        super(settings, ()-> (BlockEntityType<GradientGlassBE>)ModBlockEntities.get("gradient_glass"),true);
    }

    public GradientGlass() {
        this(Settings.copy(Blocks.WHITE_STAINED_GLASS));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        player.sendMessage(Text.of("Use the /setcolors command to set the start and end colors of the gradient!"));
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return GradientGlass.createCodec(GradientGlass::new);
    }
}
