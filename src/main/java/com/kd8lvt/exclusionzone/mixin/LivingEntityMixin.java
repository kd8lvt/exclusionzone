package com.kd8lvt.exclusionzone.mixin;

import com.kd8lvt.exclusionzone.api.ToxicBuildupTracker;
import com.kd8lvt.exclusionzone.registry.ModAttributes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.kd8lvt.exclusionzone.registry.ModAttributes.TOXIN_DAMAGE;
import static com.kd8lvt.exclusionzone.registry.ModAttributes.TOXIN_RESISTANCE;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
    @Inject(at=@At("HEAD"), method="tick")
    private void tick(CallbackInfo ci) {
        ToxicBuildupTracker.tickFor(asLivingEntity());
    }

    @Inject(at=@At("HEAD"),method="onRemoval")
    private void onRemove(Entity.RemovalReason reason, CallbackInfo ci) {
        ToxicBuildupTracker.remove(asLivingEntity());
    }

    @Inject(at=@At("HEAD"),method="onAttacking")
    private void onAttacking(Entity target, CallbackInfo ci) {
        if (target instanceof LivingEntity entity) {
            ToxicBuildupTracker.incrementBuildup(
                entity,
                (float) asLivingEntity().getAttributeValue(ModAttributes.getEntry(ModAttributes.TOXIN_DAMAGE_ID.toString()))
            );
        }
    }

    @Inject(method = "createLivingAttributes", require = 1, allow = 1, at = @At("RETURN"))
    private static void createLivingAttributes(final CallbackInfoReturnable<DefaultAttributeContainer.Builder> info) {
        info.getReturnValue().add(TOXIN_RESISTANCE);
        info.getReturnValue().add(TOXIN_DAMAGE);
    }

    @Unique
    private LivingEntity asLivingEntity() {
        return (LivingEntity)(Object)(this);
    }
}
