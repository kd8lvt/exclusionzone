package com.kd8lvt.exclusionzone.content.block.GradientGlass;

import com.kd8lvt.exclusionzone.content.block.bases.EZBlockEntityBlock;
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

public class GradientGlassBlock extends EZBlockEntityBlock<GradientGlassEntity> {
    @SuppressWarnings("unchecked")
    protected GradientGlassBlock(Settings settings) {
        super(settings, ()-> (BlockEntityType<GradientGlassEntity>)ModBlockEntities.get("gradient_glass"),true);
    }

    public GradientGlassBlock() {
        this(Settings.copy(Blocks.WHITE_STAINED_GLASS));
    }

    @Override
    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        player.sendMessage(Text.of("Use the /setcolors command to set the start and end colors of the gradient!"));
        return super.onUse(state, world, pos, player, hit);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return GradientGlassBlock.createCodec(GradientGlassBlock::new);
    }
}
