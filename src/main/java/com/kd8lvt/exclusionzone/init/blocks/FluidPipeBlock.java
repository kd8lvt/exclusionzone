package com.kd8lvt.exclusionzone.init.blocks;

import com.kd8lvt.exclusionzone.init.blocks.entity.FluidPipeBE;
import com.kd8lvt.exclusionzone.registry.ModBlockEntities;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import org.jetbrains.annotations.Nullable;

public class FluidPipeBlock extends BlockWithEntity {
    public static final BooleanProperty POWERED = BooleanProperty.of("powered");
    public static final IntProperty POWER = IntProperty.of("power",0,15);
    public FluidPipeBlock() {
        this(Settings.copy(Blocks.STONE).sounds(BlockSoundGroup.COPPER).pistonBehavior(PistonBehavior.BLOCK).solid());
    }

    private FluidPipeBlock(Settings settings) {
        super(settings);
        setDefaultState(getDefaultState().with(POWERED,false).with(POWER,0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWERED).add(POWER);
        super.appendProperties(builder);
    }

    @Override
    protected boolean emitsRedstonePower(BlockState state) {
        return state.get(Properties.POWER) > 0;
    }

    @Override
    protected int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(Properties.POWER);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(FluidPipeBlock::new);
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new FluidPipeBE(ModBlockEntities.get("fluid_pipe"),pos,state);
    }
}
