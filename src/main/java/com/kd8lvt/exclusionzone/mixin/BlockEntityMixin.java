package com.kd8lvt.exclusionzone.mixin;

import me.fallenbreath.conditionalmixin.api.annotation.Condition;
import me.fallenbreath.conditionalmixin.api.annotation.Restriction;
import net.minecraft.block.BlockState;
import net.minecraft.block.BrushableBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BrushableBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockEntity.class)
@Restriction(conflict={@Condition(type= Condition.Type.MOD,value="minecraft",versionPredicates = {"1.21"})})
public abstract class BlockEntityMixin {
    @Inject(at=@At("HEAD"),method="supports",cancellable = true)
    private void exclusionzone$supports(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (((BlockEntity)(Object)this) instanceof BrushableBlockEntity && state.getBlock() instanceof BrushableBlock) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }
}
