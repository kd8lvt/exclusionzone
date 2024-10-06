package com.kd8lvt.exclusionzone.registry;

import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.component.ComponentType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

import java.util.HashMap;
import java.util.Map;

import static com.kd8lvt.exclusionzone.ExclusionZone.LOGGER;

@SuppressWarnings("Convert2Diamond")
public class ModRegistries {
    public static final ModRegistry<SoundEvent> SOUNDS = new ModRegistry<>(Registries.SOUND_EVENT);
    public static final ModRegistry<Block> BLOCKS = new ModRegistry<>(Registries.BLOCK);
    public static final ModRegistry<BlockEntityType<? extends BlockEntity>> BLOCK_ENTITIES = new ModRegistry<BlockEntityType<?>>(Registries.BLOCK_ENTITY_TYPE);
    public static final ModItemRegistry ITEMS = new ModItemRegistry();
    public static final ModRegistry<ComponentType<?>> COMPONENT_TYPES = new ModRegistry<>(Registries.DATA_COMPONENT_TYPE);
    public static final ModRegistry<StatusEffect> STATUS_EFFECTS = new ModRegistry<>(Registries.STATUS_EFFECT);
    public static final ModRegistry<Potion> POTIONS = new ModRegistry<>(Registries.POTION);
    public static final ModRegistry<EntityType<?>> ENTITIES = new ModRegistry<>(Registries.ENTITY_TYPE);
    public static final ModRegistry<ItemGroup> CREATIVE_TABS = new ModRegistry<>(Registries.ITEM_GROUP);
    public static final HashMap<String,ModRegistry<?>> REGISTRIES_BY_TYPE = new HashMap<>();
    static {
        REGISTRIES_BY_TYPE.put(SoundEvent.class.getName(),SOUNDS);
        REGISTRIES_BY_TYPE.put(Block.class.getName(),BLOCKS);
        REGISTRIES_BY_TYPE.put(BlockEntityType.class.getName(),BLOCK_ENTITIES);
        REGISTRIES_BY_TYPE.put(Item.class.getName(),ITEMS);
        REGISTRIES_BY_TYPE.put(BlockItem.class.getName(),ITEMS);
        REGISTRIES_BY_TYPE.put(ComponentType.class.getName(),COMPONENT_TYPES);
        REGISTRIES_BY_TYPE.put(StatusEffect.class.getName(),STATUS_EFFECTS);
        REGISTRIES_BY_TYPE.put(Potion.class.getName(),POTIONS);
        REGISTRIES_BY_TYPE.put(EntityType.class.getName(),ENTITIES);
        REGISTRIES_BY_TYPE.put(ItemGroup.class.getName(),CREATIVE_TABS);
    }
    public static void registerAll() {
        LOGGER.info("[ExclusionZone] Registering Sounds...");
        ModSounds.register();
        LOGGER.info("[ExclusionZone] Registering Blocks...");
        ModBlocks.register();
        LOGGER.info("[ExclusionZone] Registering Block entities...");
        ModBlockEntities.register();
        LOGGER.info("[ExclusionZone] Registering Not-NBT-Tags...");
        ModDataComponents.register();
        LOGGER.info("[ExclusionZone] Registering Items...");
        ModItems.register();
        LOGGER.info("[ExclusionZone] Registering Status Effects...");
        ModStatusEffects.register();
        LOGGER.info("[ExclusionZone] Registering Potions...");
        ModPotions.register();
        LOGGER.info("[ExclusionZone] Registering Entities...");
        ModEntities.register();
        LOGGER.info("[ExclusionZone] Initialization complete!");
    }

    @SuppressWarnings("unchecked")
    static <T> ModContent<T> register(String id, T value) {
        for (Map.Entry<String,ModRegistry<?>> type_registry : REGISTRIES_BY_TYPE.entrySet()) {
            try {
                if (Class.forName(type_registry.getKey()).isInstance(value)) {
                    RegistryEntry<T> entry = ((ModRegistry<T>)type_registry.getValue()).register(id,value);
                    return new ModContent<T>(value,id,entry);
                }
            } catch (Exception e) {
                LOGGER.error("DANGER WILL ROBINSON! Failed to register \"exclusionzone:%s\" because of a %s!".formatted(id,e.getClass().descriptorString()));
                LOGGER.error("Expected classname: %s".formatted(value.getClass().getName()));
                LOGGER.error(e.getLocalizedMessage());
            }
        }
        return null;
    }
}

