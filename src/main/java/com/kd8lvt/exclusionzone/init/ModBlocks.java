package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Blocks.*;
import com.kd8lvt.exclusionzone.init.Blocks.entity.ExclusionZoneBiomeMakerBE;
import com.kd8lvt.exclusionzone.init.Blocks.entity.MufflerBE;
import com.kd8lvt.exclusionzone.init.Blocks.entity.BlockPlacerBE;
import com.kd8lvt.exclusionzone.init.Blocks.entity.BlockBreakerBE;
import com.kd8lvt.exclusionzone.init.Blocks.entity.RiftBE;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class ModBlocks {
    public static BrushableBlock SUS_MOSS = brushableBlockGenerator(Blocks.MOSS_BLOCK);
    public static Block AMBER_BLOCK = new Block(Block.Settings.create()
            .resistance(Blocks.COBBLESTONE.getBlastResistance())
            .hardness(Blocks.COBBLESTONE.getHardness())
            .sounds(BlockSoundGroup.TUFF)
            .luminance(state->{return 3;})
            .nonOpaque()
    );

    public static BlockBreaker BLOCK_BREAKER = new BlockBreaker();
    public static BlockPlacer BLOCK_PLACER = new BlockPlacer();
    public static Muffler MUFFLER = new Muffler();

    public static Enderweed ENDERWEED = new Enderweed();

    //SUSPICIOUS CONCRETE POWDERS//
    public static BrushableBlock SUS_WHITE_CONCRETE_POWDER = brushableBlockGenerator(Blocks.WHITE_CONCRETE_POWDER);
    public static BrushableBlock SUS_LIGHT_GRAY_CONCRETE_POWDER = brushableBlockGenerator(Blocks.LIGHT_GRAY_CONCRETE_POWDER);
    public static BrushableBlock SUS_GRAY_CONCRETE_POWDER = brushableBlockGenerator(Blocks.GRAY_CONCRETE_POWDER);
    public static BrushableBlock SUS_BLACK_CONCRETE_POWDER = brushableBlockGenerator(Blocks.BLACK_CONCRETE_POWDER);
    public static BrushableBlock SUS_BROWN_CONCRETE_POWDER = brushableBlockGenerator(Blocks.BROWN_CONCRETE_POWDER);
    public static BrushableBlock SUS_RED_CONCRETE_POWDER = brushableBlockGenerator(Blocks.RED_CONCRETE_POWDER);
    public static BrushableBlock SUS_ORANGE_CONCRETE_POWDER = brushableBlockGenerator(Blocks.ORANGE_CONCRETE_POWDER);
    public static BrushableBlock SUS_YELLOW_CONCRETE_POWDER = brushableBlockGenerator(Blocks.YELLOW_CONCRETE_POWDER);
    public static BrushableBlock SUS_LIME_CONCRETE_POWDER = brushableBlockGenerator(Blocks.LIME_CONCRETE_POWDER);
    public static BrushableBlock SUS_GREEN_CONCRETE_POWDER = brushableBlockGenerator(Blocks.GREEN_CONCRETE_POWDER);
    public static BrushableBlock SUS_CYAN_CONCRETE_POWDER = brushableBlockGenerator(Blocks.CYAN_CONCRETE_POWDER);
    public static BrushableBlock SUS_LIGHT_BLUE_CONCRETE_POWDER = brushableBlockGenerator(Blocks.LIGHT_BLUE_CONCRETE_POWDER);
    public static BrushableBlock SUS_BLUE_CONCRETE_POWDER = brushableBlockGenerator(Blocks.BLUE_CONCRETE_POWDER);
    public static BrushableBlock SUS_PURPLE_CONCRETE_POWDER = brushableBlockGenerator(Blocks.PURPLE_CONCRETE_POWDER);
    public static BrushableBlock SUS_MAGENTA_CONCRETE_POWDER = brushableBlockGenerator(Blocks.MAGENTA_CONCRETE_POWDER);
    public static BrushableBlock SUS_PINK_CONCRETE_POWDER = brushableBlockGenerator(Blocks.PINK_CONCRETE_POWDER);
    public static Block EXCLUSION_ZONE_BIOME_MAKER = new ExclusionZoneBiomeMaker();
    public static Block RIFT = new RiftBlock();
    public static BlockEntityType<ExclusionZoneBiomeMakerBE> EXCLUSION_ZONE_BIOME_MAKER_BE;
    public static BlockEntityType<RiftBE> RIFT_BE;
    public static BlockEntityType<MufflerBE> MUFFLER_BE;
    public static BlockEntityType<BlockPlacerBE> BLOCK_PLACER_BE;
    public static BlockEntityType<BlockBreakerBE> BLOCK_BREAKER_BE;
    public static ArrayList<Block> BLOCKS = new ArrayList<>();
    public static void register() {
        registerBlockWithItem(SUS_MOSS,"suspicious_moss");
        registerBlockWithItem(AMBER_BLOCK,"amber_block");
        registerBlockWithItem(BLOCK_BREAKER,"mining_simulator");
        registerBlockWithItem(BLOCK_PLACER,"interaction_simulator");
        registerBlockWithItem(MUFFLER,"muffler");

        register(Registries.BLOCK,ExclusionZone.id("exclusion_zone_biome_maker"),EXCLUSION_ZONE_BIOME_MAKER);
        register(Registries.BLOCK,ExclusionZone.id("rift"),RIFT);

        register(Registries.BLOCK,ExclusionZone.id("enderweed"),ENDERWEED);
        //Sus Concrete Powders\\
        registerBlockWithItem(SUS_WHITE_CONCRETE_POWDER,"suspicious_white_concrete_powder");
        registerBlockWithItem(SUS_LIGHT_GRAY_CONCRETE_POWDER,"suspicious_light_gray_concrete_powder");
        registerBlockWithItem(SUS_GRAY_CONCRETE_POWDER,"suspicious_gray_concrete_powder");
        registerBlockWithItem(SUS_BLACK_CONCRETE_POWDER,"suspicious_black_concrete_powder");
        registerBlockWithItem(SUS_BROWN_CONCRETE_POWDER,"suspicious_brown_concrete_powder");
        registerBlockWithItem(SUS_RED_CONCRETE_POWDER,"suspicious_red_concrete_powder");
        registerBlockWithItem(SUS_ORANGE_CONCRETE_POWDER,"suspicious_orange_concrete_powder");
        registerBlockWithItem(SUS_YELLOW_CONCRETE_POWDER,"suspicious_yellow_concrete_powder");
        registerBlockWithItem(SUS_LIME_CONCRETE_POWDER,"suspicious_lime_concrete_powder");
        registerBlockWithItem(SUS_GREEN_CONCRETE_POWDER,"suspicious_green_concrete_powder");
        registerBlockWithItem(SUS_CYAN_CONCRETE_POWDER,"suspicious_cyan_concrete_powder");
        registerBlockWithItem(SUS_LIGHT_BLUE_CONCRETE_POWDER,"suspicious_light_blue_concrete_powder");
        registerBlockWithItem(SUS_BLUE_CONCRETE_POWDER,"suspicious_blue_concrete_powder");
        registerBlockWithItem(SUS_PURPLE_CONCRETE_POWDER,"suspicious_purple_concrete_powder");
        registerBlockWithItem(SUS_MAGENTA_CONCRETE_POWDER,"suspicious_magenta_concrete_powder");
        registerBlockWithItem(SUS_PINK_CONCRETE_POWDER,"suspicious_pink_concrete_powder");

        //Block Entities
        EXCLUSION_ZONE_BIOME_MAKER_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,ExclusionZone.id("exclusionzonemaker"),FabricBlockEntityTypeBuilder.create(ExclusionZoneBiomeMakerBE::new,EXCLUSION_ZONE_BIOME_MAKER).build());
        RIFT_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,ExclusionZone.id("rift"),FabricBlockEntityTypeBuilder.create(RiftBE::new,RIFT).build());
        MUFFLER_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,ExclusionZone.id("muffler"),FabricBlockEntityTypeBuilder.create(MufflerBE::new,MUFFLER).build());
        BLOCK_PLACER_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,ExclusionZone.id("interaction_simulator"),FabricBlockEntityTypeBuilder.create(BlockPlacerBE::new,BLOCK_PLACER).build());
        BLOCK_BREAKER_BE = Registry.register(Registries.BLOCK_ENTITY_TYPE,ExclusionZone.id("mining_simulator"),FabricBlockEntityTypeBuilder.create(BlockBreakerBE::new,BLOCK_BREAKER).build());
    }

    @SuppressWarnings("rawtypes")
    public static void register(Registry registry, Identifier identifier, Block block) {
        BLOCKS.add(block);
        Registry.register(registry,identifier,block);
    }

    public static void registerBlockWithItem(Block block,String id) {
        BlockItem GENERATED_BLOCK_ITEM = new BlockItem(block, new Item.Settings());
        register(Registries.BLOCK,ExclusionZone.id(id),block);
        ModItems.register(Registries.ITEM,ExclusionZone.id(id),GENERATED_BLOCK_ITEM);
    }

    public static BrushableBlock brushableBlockGenerator(Block baseBlock) {
        return new BrushableBlock(baseBlock,SoundEvents.ITEM_BRUSH_BRUSHING_GENERIC,baseBlock.getDefaultState().getSoundGroup().getPlaceSound(), Block.Settings.create().sounds(baseBlock.getDefaultState().getSoundGroup()).hardness(baseBlock.getHardness()).resistance(baseBlock.getBlastResistance()));
    }
}
