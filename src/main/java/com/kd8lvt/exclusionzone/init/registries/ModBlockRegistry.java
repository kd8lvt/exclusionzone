package com.kd8lvt.exclusionzone.init.registries;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Blocks.*;
import com.kd8lvt.exclusionzone.init.Blocks.entity.*;
import com.kd8lvt.exclusionzone.init.RegistryUtil;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.HashMap;

public class ModBlockRegistry {
    public static final String[] VANILLA_COLORS = new String[]{"pink", "red", "brown", "orange", "yellow", "lime", "green", "cyan", "light_blue", "blue", "magenta", "purple", "black", "gray", "light_gray", "white"};
    public static RegistryEntry<Block> SUS_MOSS;
    public static RegistryEntry<Block> AMBER_BLOCK;
    public static RegistryEntry<Block> BLOCK_BREAKER;
    public static RegistryEntry<Block> BLOCK_PLACER;
    public static RegistryEntry<Block> MUFFLER;
    public static RegistryEntry<Block> FLUID_PIPE;

    public static RegistryEntry<Block> ENDERWEED;
    public static final HashMap<String, RegistryEntry<Block>> SUS_CONCRETE_POWDERS = new HashMap<>();
    public static RegistryEntry<Block> EXCLUSION_ZONE_BIOME_MAKER;
    public static RegistryEntry<Block> RIFT;
    public static RegistryEntry<BlockEntityType<?>> EXCLUSION_ZONE_BIOME_MAKER_BE;
    public static RegistryEntry<BlockEntityType<?>> RIFT_BE;
    public static RegistryEntry<BlockEntityType<?>> MUFFLER_BE;
    public static RegistryEntry<BlockEntityType<?>> BLOCK_PLACER_BE;
    public static RegistryEntry<BlockEntityType<?>> BLOCK_BREAKER_BE;
    public static RegistryEntry<BlockEntityType<?>> FLUID_PIPE_BE;

    public static void register() {
        AMBER_BLOCK = registerBlockWithItem("amber_block", new Block(Block.Settings.create().resistance(Blocks.COBBLESTONE.getBlastResistance()).hardness(Blocks.COBBLESTONE.getHardness()).sounds(BlockSoundGroup.TUFF).luminance(state -> 3).nonOpaque()));
        BLOCK_BREAKER = registerBlockWithItem("mining_simulator", new BlockBreaker());
        BLOCK_PLACER = registerBlockWithItem("interaction_simulator", new BlockPlacer());
        MUFFLER = registerBlockWithItem("muffler", new Muffler());
        FLUID_PIPE = registerBlockWithItem("fluid_pipe",new FluidPipeBlock());
        EXCLUSION_ZONE_BIOME_MAKER = RegistryUtil.register("exclusion_zone_biome_maker", new ExclusionZoneBiomeMaker());
        RIFT = RegistryUtil.register("rift", new RiftBlock());
        ENDERWEED = RegistryUtil.register("plant/enderweed", new Enderweed());

        //Archaeology\\
        SUS_MOSS = registerBlockWithItem("archaeology/suspicious_moss", brushableBlockGenerator(Blocks.MOSS_BLOCK));
        for (String color : VANILLA_COLORS) {
            SUS_CONCRETE_POWDERS.put(
                    color,
                    registerBlockWithItem(
                            "archaeology/suspicious_" + color + "_concrete_powder",
                            brushableBlockGenerator(Registries.BLOCK.get(Identifier.ofVanilla(color + "_concrete_powder")))
                    )
            );
        }

        //Block Entities\\
        EXCLUSION_ZONE_BIOME_MAKER_BE = RegistryUtil.register("exclusionzonemaker", FabricBlockEntityTypeBuilder.create(ExclusionZoneBiomeMakerBE::new, EXCLUSION_ZONE_BIOME_MAKER.value()).build());
        RIFT_BE = RegistryUtil.register("rift", FabricBlockEntityTypeBuilder.create(RiftBE::new, RIFT.value()).build());
        MUFFLER_BE = RegistryUtil.register("muffler", FabricBlockEntityTypeBuilder.create(MufflerBE::new, MUFFLER.value()).build());
        BLOCK_PLACER_BE = RegistryUtil.register("interaction_simulator", FabricBlockEntityTypeBuilder.create(BlockPlacerBE::new, BLOCK_PLACER.value()).build());
        BLOCK_BREAKER_BE = RegistryUtil.register("mining_simulator", FabricBlockEntityTypeBuilder.create(BlockBreakerBE::new, BLOCK_BREAKER.value()).build());
        FLUID_PIPE_BE = RegistryUtil.register("fluid_pipe", FabricBlockEntityTypeBuilder.create(FluidPipeBE::new, FLUID_PIPE.value()).build());

    }


    /**
     * Register a Block along with an associated BlockItem
     * <p> Note: Does <b>NOT</b> return a RegistryEntry for the generated BlockItem!
     * It is, however added to ModItems.ITEMS automatically.</p>
     *
     * @param id    Path for the ExclusionZone-namespaced Identifier for both the Block and the generated BlockItem
     * @param block Block to generate a BlockItem for and register
     * @return RegistryEntry&lt;Block&gt; corresponding to the registered Block
     */
    public static RegistryEntry<Block> registerBlockWithItem(String id, Block block) {
        BlockItem GENERATED_BLOCK_ITEM = new BlockItem(block, new Item.Settings());
        RegistryUtil.register(id, GENERATED_BLOCK_ITEM);
        return RegistryUtil.register(id, block);
    }

    /**
     * Create a BrushableBlock for a given Block
     * <p>Note: Does <b>NOT</b> register the generated BrushableBlock!</p>
     *
     * @param baseBlock Block to generate a BrushableBlock for
     * @return BrushableBlock corresponding to the given Block
     */
    public static BrushableBlock brushableBlockGenerator(Block baseBlock) {
        return new BrushableBlock(baseBlock, SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, baseBlock.getDefaultState().getSoundGroup().getPlaceSound(), Block.Settings.create().sounds(baseBlock.getDefaultState().getSoundGroup()).hardness(baseBlock.getHardness()).resistance(baseBlock.getBlastResistance()));
    }


    public static Block get(String id) {
        return Registries.BLOCK.get(ExclusionZone.id(id));
    }
}
