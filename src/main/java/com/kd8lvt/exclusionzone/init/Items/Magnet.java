package com.kd8lvt.exclusionzone.init.Items;

import com.kd8lvt.exclusionzone.init.ModItems;
import net.minecraft.component.ComponentChanges;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.List;

public class Magnet extends Artifact {
    int range;
    public Magnet(int range) {
        super(new Settings().rarity(Rarity.UNCOMMON).component(ModItems.DATA_COMPONENT_MAGNET_ENABLED,false).maxCount(1));
        this.range = range;
    }

    public boolean isAttracting(ItemStack stack) {
        return Boolean.TRUE.equals(stack.get(ModItems.DATA_COMPONENT_MAGNET_ENABLED));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (user.isSneaking()) stack.applyChanges(ComponentChanges.builder().add(ModItems.DATA_COMPONENT_MAGNET_ENABLED, !isAttracting(stack)).build());
        return super.use(world, user, hand);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return isAttracting(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (isAttracting(stack)) {
            List<ItemEntity> entities = world.getEntitiesByClass(ItemEntity.class, Box.enclosing(entity.getBlockPos().offset(Direction.DOWN,this.range).offset(Direction.EAST,this.range).offset(Direction.NORTH,this.range),entity.getBlockPos().offset(Direction.UP,this.range).offset(Direction.WEST,this.range).offset(Direction.SOUTH,this.range)),(item)-> !(item.cannotPickup() || item.age < 60));
            for (ItemEntity item : entities) {
                Vec3d v = entity.getPos().offset(Direction.UP,1).subtract(item.getPos()).normalize().multiply(Math.log(item.getPos().distanceTo(entity.getPos())));
                item.setVelocity(v);
                item.velocityModified=true;
                item.velocityDirty=true;
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}
