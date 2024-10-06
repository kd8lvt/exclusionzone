package com.kd8lvt.exclusionzone.registry;

import com.kd8lvt.exclusionzone.init.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BrushableBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

@SuppressWarnings("RedundantCast")
public class ModBlocks {
    public static final String[] VANILLA_COLORS = new String[]{"pink", "red", "brown", "orange", "yellow", "lime", "green", "cyan", "light_blue", "blue", "magenta", "purple", "black", "gray", "light_gray", "white"};

    public static RegistryEntry<Block> getEntry(String id) {return ModRegistries.BLOCKS.get(id);}
    public static Block get(String id) {return getEntry(id).value();}

    static void register() {
        AMBER_BLOCK = registerBlockWithItem("amber_block", new Block(Block.Settings.create().resistance(Blocks.COBBLESTONE.getBlastResistance()).hardness(Blocks.COBBLESTONE.getHardness()).sounds(BlockSoundGroup.TUFF).luminance(state -> 3).nonOpaque()));
        BLOCK_BREAKER = registerBlockWithItem("mining_simulator", (Block)new BlockBreaker());
        BLOCK_PLACER = registerBlockWithItem("interaction_simulator", new BlockPlacer());
        MUFFLER = registerBlockWithItem("muffler", new Muffler());
        FLUID_PIPE = registerBlockWithItem("fluid_pipe",new FluidPipeBlock());
        EZ_SPREADER = register("exclusion_zone_biome_maker", new ExclusionZoneBiomeMaker());
        RIFT = register("rift", new RiftBlock());
        ENDERWEED = register("plant/enderweed", new Enderweed());

        //Archaeology\\
        registerBlockWithItem("archaeology/suspicious_moss", brushableBlockGenerator(Blocks.MOSS_BLOCK));
        for (String color : VANILLA_COLORS) {
            registerBlockWithItem("archaeology/suspicious_" + color + "_concrete_powder",brushableBlockGenerator(Registries.BLOCK.get(Identifier.ofVanilla(color + "_concrete_powder"))));
        }
    }

    static ModContent<Block> register(String id, Block block) {
        return ModRegistries.register(id, block);
    }

    /**
     * Register a Block along with an associated BlockItem
     * <p> Note: Does <b>NOT</b> return a RegistryEntry for the generated BlockItem!
     *
     * @param id    Path for the ExclusionZone-namespaced Identifier for both the Block and the generated BlockItem
     * @param block Block to generate a BlockItem for and register
     * @return RegistryEntry&lt;Block&gt; corresponding to the registered Block
     */
    static ModContent<Block> registerBlockWithItem(String id, Block block) {
        BlockItem GENERATED_BLOCK_ITEM = new BlockItem(block, new Item.Settings());
        ModRegistries.register(id, GENERATED_BLOCK_ITEM);
        return ModRegistries.register(id, (Block)block);
    }

    /**
     * Create a BrushableBlock for a given Block
     * <p>Note: Does <b>NOT</b> register the generated BrushableBlock!</p>
     *
     * @param baseBlock Block to generate a BrushableBlock for
     * @return BrushableBlock corresponding to the given Block
     */
    static BrushableBlock brushableBlockGenerator(Block baseBlock) {
        return new BrushableBlock(baseBlock, SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC, baseBlock.getDefaultState().getSoundGroup().getPlaceSound(), Block.Settings.create().sounds(baseBlock.getDefaultState().getSoundGroup()).hardness(baseBlock.getHardness()).resistance(baseBlock.getBlastResistance()));
    }

    public static ModContent<Block> AMBER_BLOCK;
    public static ModContent<Block> BLOCK_BREAKER;
    public static ModContent<Block> BLOCK_PLACER;
    public static ModContent<Block> RIFT;
    public static ModContent<Block> EZ_SPREADER;
    public static ModContent<Block> MUFFLER;
    public static ModContent<Block> FLUID_PIPE;
    public static ModContent<Block> ENDERWEED;
}
