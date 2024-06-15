package com.kd8lvt.exclusionzone.init.Entities;

import com.kd8lvt.exclusionzone.ExclusionZone;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.ai.goal.LookAroundGoal;
import net.minecraft.entity.ai.goal.LookAtEntityGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Arm;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Objects;

public class CaroInvictusEntity extends HostileEntity {
    public boolean hasDied = false;
    public boolean regenning = false;

    public CaroInvictusEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected boolean isDisallowedInPeaceful() {return false;}

    @Override
    protected void updateDespawnCounter() {}

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {return false;}

    @Override
    public boolean isAttackable() {
        return !regenning;
    }

    public void updateScale() {
        EntityAttributeInstance inst = this.getAttributeInstance(EntityAttributes.GENERIC_SCALE);
        Objects.requireNonNull(inst).clearModifiers();
        inst.addTemporaryModifier(new EntityAttributeModifier(EntityAttributes.GENERIC_SCALE.getIdAsString(), this.getHealth()/this.getMaxHealth(), EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
    }

    public void initCustomGoals() {
        this.goalSelector.add(2, new MeleeAttackGoal(this,1.0f,false));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0f));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.initCustomGoals();
    }

    @Override
    public boolean damage(DamageSource source, float amount) {
        if (amount >= this.getHealth() && !hasDied) {
            this.hasDied = true;
            this.regenning = true;
            return super.damage(source,this.getHealth()-1);
        }
        return super.damage(source, amount);
    }

    @Override
    public Iterable<ItemStack> getArmorItems() {
        ArrayList<ItemStack> items = new ArrayList<>();
        items.add(ItemStack.EMPTY);
        items.add(ItemStack.EMPTY);
        items.add(ItemStack.EMPTY);
        items.add(ItemStack.EMPTY);
        return items;
    }

    @Override
    public ItemStack getEquippedStack(EquipmentSlot slot) {
        return ItemStack.EMPTY;
    }

    @Override
    public void equipStack(EquipmentSlot slot, ItemStack stack) {

    }
    @Override
    public void tick() {
        if (this.regenning && this.getHealth() >= this.getMaxHealth()) {
            this.setHealth(this.getMaxHealth());
            this.getEntityWorld().createExplosion(this,this.getX(),this.getY(),this.getZ(),10f, World.ExplosionSourceType.BLOW);
            this.regenning=false;
        }
        if (this.regenning) this.setHealth((float)Math.ceil(this.getMaxHealth()/100)+this.getHealth());
        if (this.regenning) this.updateScale();
        super.tick();
    }

    @Override
    public void onDeath(DamageSource damageSource) {
        ExclusionZone.runCommand("stopsound @a master exclusionzone:mob.caro_invictus.music");
        super.onDeath(damageSource);
    }

    @Override
    public Arm getMainArm() {
        return null;
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("regenning",regenning);
        nbt.putBoolean("deathDefied",hasDied);
    }
    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        regenning = nbt.getBoolean("regenning");
        hasDied = nbt.getBoolean("deathDefied");
    }
}
