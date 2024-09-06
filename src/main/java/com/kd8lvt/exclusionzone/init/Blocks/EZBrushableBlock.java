package com.kd8lvt.exclusionzone.init.Blocks;

import com.kd8lvt.exclusionzone.init.Blocks.entity.EZBrushableBlockEntity;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.registry.Registries;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class EZBrushableBlock extends BrushableBlock {
    public static final MapCodec<BrushableBlock> CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(Registries.BLOCK.getCodec().fieldOf("turns_into").forGetter(BrushableBlock::getBaseBlock), Registries.SOUND_EVENT.getCodec().fieldOf("brush_sound").forGetter(BrushableBlock::getBrushingSound), Registries.SOUND_EVENT.getCodec().fieldOf("brush_comleted_sound").forGetter(BrushableBlock::getBrushingCompleteSound), createSettingsCodec()).apply(instance, EZBrushableBlock::new));

    public MapCodec<BrushableBlock> getCodec() {
        return CODEC;
    }

    public EZBrushableBlock(Block baseBlock, SoundEvent brushingSound, SoundEvent brushingCompleteSound, Settings settings) {
        super(baseBlock, brushingSound, brushingCompleteSound, settings);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new EZBrushableBlockEntity(pos,state);
    }
}
