package com.kd8lvt.exclusionzone.init;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.registries.ModItemRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.potion.Potion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;

public class RegistryUtil {
    /**
     * Register a SoundEvent
     * @param id Path for the ExclusionZone-namespaced Identifier
     * @return RegistryEntry&lt;SoundEvent&gt; corresponding to the registered SoundEvent
     */
    public static RegistryEntry<SoundEvent> register(String id) {
        return Registries.SOUND_EVENT.getEntry(Registry.register(Registries.SOUND_EVENT,ExclusionZone.id(id),SoundEvent.of(ExclusionZone.id(id))));
    }

    /**
     * Register an Item
     * @param id Path for the ExclusionZone-namespaced Identifier
     * @param item Item to register
     * @return RegistryEntry&lt;Item&gt; corresponding to the registered Item
     */
    public static RegistryEntry<Item> register(String id, Item item) {
        ModItemRegistry.ITEMS.add(item);
        return Registries.ITEM.getEntry(Registry.register(Registries.ITEM,ExclusionZone.id(id),item));
    }
    /**
     * Register a StatusEffect
     * @param id Path for the ExclusionZone-namespaced Identifier
     * @param item StatusEffect to register
     * @return RegistryEntry&lt;StatusEffect&gt; corresponding to the registered StatusEffect
     */
    public static RegistryEntry<StatusEffect> register(String id, StatusEffect item) {
        return Registries.STATUS_EFFECT.getEntry(Registry.register(Registries.STATUS_EFFECT, ExclusionZone.id(id),item));
    }
    /**
     * Register a Potion
     * @param id Path for the ExclusionZone-namespaced Identifier
     * @param item Potion to register
     * @return RegistryEntry&lt;Potion&gt; corresponding to the registered Potion
     */
    public static RegistryEntry<Potion> register(String id, Potion item) {
        return Registries.POTION.getEntry(Registry.register(Registries.POTION,ExclusionZone.id(id),item));
    }
    /**
     * Register an ItemGroup
     * @param id Path for the ExclusionZone-namespaced Identifier
     * @param item ItemGroup to register
     * @return RegistryEntry&lt;ItemGroup&gt; corresponding to the registered ItemGroup
     */
    public static RegistryEntry<ItemGroup> register(String id, ItemGroup item) {
        return Registries.ITEM_GROUP.getEntry(Registry.register(Registries.ITEM_GROUP,ExclusionZone.id(id),item));
    }
    /**
     * Register an EntityType
     * @param id Path for the ExclusionZone-namespaced Identifier
     * @param item EntityType to register
     * @return RegistryEntry&lt;EntityType&lt;?&gt;&gt; corresponding to the registered EntityType
     */
    public static RegistryEntry<EntityType<?>> register(String id, EntityType<?> item) {
        return Registries.ENTITY_TYPE.getEntry(Registry.register(Registries.ENTITY_TYPE,ExclusionZone.id(id),item));
    }
    /**
     * Register a Block
     * @param id Path for the ExclusionZone-namespaced Identifier
     * @param item Block to register
     * @return RegistryEntry&lt;Block&gt; corresponding to the registered Block
     */
    public static RegistryEntry<Block> register(String id, Block item) {
        return Registries.BLOCK.getEntry(Registry.register(Registries.BLOCK,ExclusionZone.id(id),item));
    }

    /**
     * Register a BlockEntityType
     * @param id Path for the ExclusionZone-namespaced Identifier
     * @param item BlockEntityType to register
     * @return RegistryEntry&lt;BlockEntityType&lt;?&gt;&gt; corresponding to the registered BlockEntityType
     */
    public static RegistryEntry<BlockEntityType<?>> register(String id, BlockEntityType<?> item) {
        return Registries.BLOCK_ENTITY_TYPE.getEntry(Registry.register(Registries.BLOCK_ENTITY_TYPE,ExclusionZone.id(id),item));
    }
}
