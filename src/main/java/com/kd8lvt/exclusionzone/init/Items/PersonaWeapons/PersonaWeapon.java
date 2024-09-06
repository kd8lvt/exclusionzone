package com.kd8lvt.exclusionzone.init.Items.PersonaWeapons;

import com.kd8lvt.exclusionzone.ExclusionZone;
import com.kd8lvt.exclusionzone.init.Items.PersonaWeapons.Traits.PTrait;
import com.kd8lvt.exclusionzone.init.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.component.ComponentMap;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.packet.s2c.play.PositionFlag;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.function.Consumer;

public class PersonaWeapon extends SwordItem {
    boolean isHeld = false;
    @Nullable
    final Integer prevDamage = this.getComponents().get(DataComponentTypes.DAMAGE);
    public PersonaWeapon(ToolMaterial toolMaterial, net.minecraft.item.Item.Settings settings) {
        super(toolMaterial, settings);
    }

    public void generateTraits(ItemStack stack) {
        ComponentMap comps = stack.getComponents();
        Random random = Random.create();
        int traitsToGen = random.nextBetween(1,3);
        List<Identifier> comp = comps.get(ModItems.DATA_COMPONENT_PWEAPON_TRAITS);
        if (comp == null) comp = new ArrayList<>();
        for (int i=0;i<traitsToGen;i++) {
            Identifier tryAdd = PersonaWeaponTraits.TRAITS.keySet().toArray(new Identifier[]{})[random.nextBetween(0,PersonaWeaponTraits.TRAITS.size()-1)];
            if (comp.contains(tryAdd)) continue;
            comp.add(tryAdd);
        }
        List<Identifier> finalComp = comp;
        stack.apply(ModItems.DATA_COMPONENT_PWEAPON_TRAITS,comp, edit-> finalComp);
    }

    public static void applyTraits(ItemStack stack, Consumer<PTrait> consumer) {
        List<Identifier> traits = stack.getComponents().get(ModItems.DATA_COMPONENT_PWEAPON_TRAITS);
        if (traits == null) return;
        for (Identifier traitId : traits) {
            consumer.accept(PersonaWeaponTraits.TRAITS.get(traitId));
        }
    }
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        boolean shouldDamageItem = super.postHit(stack, target, attacker);
        applyTraits(stack, trait->{
            trait.onHit(stack,target,attacker);
            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnHitAttacker)) {
                ExclusionZone.runCommand("execute as "+attacker.getUuid()+" at @s run function "+trait.functions.get(PersonaWeaponFunctionEvents.OnHitAttacker).toString());
            }
            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnHitVictim)) {
                ExclusionZone.runCommand("execute as " + target.getUuid() + " at @s run function " + trait.functions.get(PersonaWeaponFunctionEvents.OnHitVictim).toString());
            }
            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnEntityKilled) && target.getHealth() <= 0) {
                ExclusionZone.runCommand("execute as " + target.getUuid() + " at @s run function " + trait.functions.get(PersonaWeaponFunctionEvents.OnEntityKilled).toString());
            }
            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnDurabilityLost) && shouldDamageItem) {
                ExclusionZone.runCommand("execute as " + attacker.getUuid() + " at @s run function " + trait.functions.get(PersonaWeaponFunctionEvents.OnDurabilityLost).toString());
            }
        });
        return shouldDamageItem;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        ActionResult ar = super.useOnBlock(context);
        applyTraits(context.getStack(), trait->{
            trait.onUseOnBlock(context);
            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnUseOnBlock)) {
                ExclusionZone.runCommand("execute positioned "+context.getBlockPos().getX()+" "+context.getBlockPos().getY()+" "+context.getBlockPos().getZ()+" as "+ Objects.requireNonNull(context.getPlayer()).getUuidAsString()+" run function "+trait.functions.get(PersonaWeaponFunctionEvents.OnUseOnBlock).toString());
            }
        });
        return ar;
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        ActionResult ar = super.useOnEntity(stack, user, entity, hand);
        applyTraits(stack, trait->{
            trait.onUseOnEntity(stack,user,entity,hand);
            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnUseOnEntity)) {
                ExclusionZone.runCommand("execute  as "+entity.getUuidAsString()+" at @s run function "+trait.functions.get(PersonaWeaponFunctionEvents.OnUseOnEntity).toString());
            }
        });
        return ar;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (stack.getComponents().get(ModItems.DATA_COMPONENT_PWEAPON_TRAITS) == null) generateTraits(stack);
        applyTraits(stack, trait->{
            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnInventoryTick)) {
                ExclusionZone.runCommand("execute as "+entity.getUuidAsString()+" at @s run function "+trait.functions.get(PersonaWeaponFunctionEvents.OnInventoryTick).toString());
            }

            if (!this.isHeld && selected) trait.onHeld(stack,world,entity,slot);
            if (this.isHeld && !selected) trait.onUnHeld(stack,world,entity,slot);

            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnHeld)) {
                if (!this.isHeld && selected) {
                    ExclusionZone.runCommand("execute as " + entity.getUuidAsString() + " at @s run function " + trait.functions.get(PersonaWeaponFunctionEvents.OnHeld).toString());
                    this.isHeld = true;
                }
            }

            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnUnHeld)) {
                if (this.isHeld && !selected) {
                    ExclusionZone.runCommand("execute as " + entity.getUuidAsString() + " at @s run function " + trait.functions.get(PersonaWeaponFunctionEvents.OnUnHeld).toString());
                    this.isHeld = false;
                }
            }

            if (trait.functions.containsKey(PersonaWeaponFunctionEvents.OnDurabilityLost) && !Objects.equals(this.prevDamage, this.getComponents().get(DataComponentTypes.DAMAGE))) {
                ExclusionZone.runCommand("execute as " + entity.getUuidAsString() + " at @s run function " + trait.functions.get(PersonaWeaponFunctionEvents.OnDurabilityLost).toString());
            }

            trait.inventoryTick(stack,world,entity,slot,selected);

            if (!Objects.equals(this.prevDamage, this.getComponents().get(DataComponentTypes.DAMAGE))) trait.onDurabilityLost(stack,world,entity,slot,selected);

        });
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        applyTraits(stack,trait->{
            if (!trait.tt.isEmpty()) {
                tooltip.addAll(trait.tt);
            }
        });
        super.appendTooltip(stack, context, tooltip, type);
    }

    @Override
    public void onItemEntityDestroyed(ItemEntity entity) { //No despawning/dying for you!
        ItemEntity newEntity = entity.copy();
        newEntity.age = -(Integer.MAX_VALUE-1);
        newEntity.setNeverDespawn();
        entity.getEntityWorld().spawnEntity(newEntity);
        if (entity.getPos().y <= entity.getEntityWorld().getDimension().minY()) {
            float distFell = entity.fallDistance;
            World world = entity.getEntityWorld();
            if (!(world instanceof ServerWorld)) return;
            BlockPos pos = entity.getBlockPos().offset(Direction.UP, (int) distFell);
            for (int x=-2;x<2;x++) {
                for (int y=2;y>-2;y--) {
                    for (int z=-2;z<2;z++) {
                        BlockPos tmpPos = pos.add(x,y,z);
                        BlockState state = world.getBlockState(tmpPos);
                        if (!state.getCollisionShape(world,tmpPos).isEmpty()) {
                            newEntity.teleport((ServerWorld)world,tmpPos.getX(), tmpPos.getY(), tmpPos.getZ(), Set.of(PositionFlag.X,PositionFlag.Y,PositionFlag.Z),0,0);
                            break;
                        }
                    }
                }
            }
        }
        super.onItemEntityDestroyed(entity);
    }
}
